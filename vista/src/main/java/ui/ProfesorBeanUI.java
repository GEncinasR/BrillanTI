package ui;

import helper.ProfesorHelper;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.desarrollo.entity.Profesor;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named("profesorBeanUI")
@SessionScoped
public class ProfesorBeanUI implements Serializable {
    private ProfesorHelper profesorHelper;

    @Inject
    private AsignadoBeanUI asignadoUI;

    // datos de los campos
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rfc;

    // solo para validar el formato del rfc
    private String anio;
    private String mes;
    private String dia;
    private LocalDate fechaNacimiento;

    public ProfesorBeanUI(){

        profesorHelper = new ProfesorHelper();
    }


    public void altaProfesor(){
        try {
            // Primero validar los datos introducidos
            if(!validarCampos() || !validarRFC()){
                return;
            }

            // Validar que el rfc no existe en la BD
            if(profesorHelper.existeRFC(rfc)) {
                System.out.println("validadando rfc repetido");
                mostrarError("RFC duplicado","El RFC introducido ya se encuentra registrado en el sistema.");
                return;
            }

            // Crear la entidad
            Profesor nuevoProfesor = new Profesor();
            nuevoProfesor.setNombreProfesor(nombre);
            nuevoProfesor.setApellidoP(apellidoPaterno);
            nuevoProfesor.setApellidoM(apellidoMaterno);
            nuevoProfesor.setRfc(rfc.toUpperCase()); // En mayúsculas por estándar


            System.out.println("guardar desde el bean");
            profesorHelper.altaProfesor(nuevoProfesor);

            if (asignadoUI != null) {
                asignadoUI.refreshData();
            }

            // Mensaje de exito
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Profesor registrado correctamente."));

            limpiarCampos();

        } catch (Exception e) {
            // Si la base de datos se cae o hay un error inesperado
            mostrarError("Error Crítico", "Ocurrió un problema al guardar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Valida los datos introducidos por el usuario
    public boolean validarCampos() {
        System.out.println("-- validando campos:");
        // Verificar que ningún campo esté vacío o sea solo espacios en blanco
        if (nombre == null || nombre.trim().isEmpty() ||
                apellidoPaterno == null || apellidoPaterno.trim().isEmpty() ||
                apellidoMaterno == null || apellidoMaterno.trim().isEmpty() ||
                rfc == null || rfc.trim().isEmpty() ||
                dia == null || dia.trim().isEmpty() ||
                mes == null || mes.trim().isEmpty() ||
                anio == null || anio.trim().isEmpty()) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Todos los campos son obligatorios."));
            return false;
        }

        // Validar fecha de nacimiento
        try {
            // Convertimos los textos a números enteros
            int d = Integer.parseInt(dia);
            int m = Integer.parseInt(mes);
            int a = Integer.parseInt(anio);

            // Intentamos crear la fecha.
            // Si escribieron algo ilógico (ej. mes 13, o 31 de febrero), saltará al 'catch'
            fechaNacimiento = LocalDate.of(a, m, d);

            // Si es fecha posterior a l actual
            if (fechaNacimiento.isAfter(LocalDate.now())) {
                mostrarError( "Fecha no valida", "La fecha no puede ser en el futuro.");
                return false;
            }

        } catch (NumberFormatException e) {
            mostrarError("Fecha no valida", "La fecha debe contener solo números válidos.");
            return false;
        } catch (DateTimeException e) {
            mostrarError( "Fecha no valida", "La fecha ingresada no existe en el calendario.");
            return false;
        }

        // Verificar que nombre y apellidos NO contengan números
        // Solo permite letras (mayúsculas, minúsculas), acentos, la ñ y espacios
        String stringSoloLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

        if (!nombre.matches(stringSoloLetras)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error de Validación", "El nombre no debe contener números ni símbolos."));
            return false;
        }

        if (!apellidoPaterno.matches(stringSoloLetras)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error de Validación", "El apellido paterno no debe contener números."));
            return false;
        }

        if (!apellidoMaterno.matches(stringSoloLetras)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error de Validación", "El apellido materno no debe contener números."));
            return false;
        }

        // Hasta aquí los campos son válidos
        System.out.println("campos validos");
        return true;
    }

    // Valida el rfc introducido con el formato establecido
    public boolean validarRFC() {
        System.out.println("-- validando rfc");
        String rfcUp = rfc.trim().toUpperCase();

        //  Validar formato general
        if (!rfcUp.matches("^[A-ZÑ&]{4}\\d{6}[A-Z0-9]{3}$")) {
            mostrarError("RFC no válido", "El formato debe ser ABCD123456XYZ.");
            return false;
        }

        // Validar las primeras 4 letras (con seguro para apellidos de 1 letra)
        String vocales = "";
        if (apellidoPaterno.trim().length() > 1) {
            vocales = apellidoPaterno.trim().toUpperCase().substring(1).replaceAll("[^AEIOU]", "");
        }
        char vocalInterna = vocales.isEmpty() ? 'X' : vocales.charAt(0);

        String primerasLetrasEsperadas = "" + apellidoPaterno.trim().toUpperCase().charAt(0)
                + vocalInterna
                + apellidoMaterno.trim().toUpperCase().charAt(0)
                + nombre.trim().toUpperCase().charAt(0);

        // Comparar
        if (!rfcUp.startsWith(primerasLetrasEsperadas)) {
            mostrarError("RFC Incorrecto", "Las primeras 4 letras no corresponden a los datos introducidos.");
            return false;
        }

        // Validar fecha de nacimiento
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String fechaEsperada = fechaNacimiento.format(formatter);

        // Extraemos los 6 números del RFC que escribió el usuario
        String fechaEnRFC = rfcUp.substring(4, 10);

        // Comparar
        if (!fechaEsperada.equals(fechaEnRFC)) {
            mostrarError("RFC Incorrecto", "La fecha en el RFC (" + fechaEnRFC +
                    ") no coincide con su fecha de nacimiento (" + fechaEsperada + ").");
            return false;
        }

        System.out.println("rfc valido");
        return true;
    }

    // Metodo para mostrar error en la pantalla
    private void mostrarError(String titulo, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalle));
    }

    // Metodo para limpiar los campos
    private void limpiarCampos(){
        this.nombre = null;
        this.apellidoPaterno = null;
        this.apellidoMaterno = null;
        this.rfc = null;
        this.fechaNacimiento = null;
        this.dia = null;
        this.mes = null;
        this.anio = null;
        System.out.println("campos limpios");
    }

    // Getters
    public String getNombre() {  return nombre;    }
    public String getApellidoPaterno() { return apellidoPaterno;    }
    public String getApellidoMaterno() { return apellidoMaterno;    }
    public String getRfc() { return rfc;    }
    public LocalDate getFechaNacimiento() { return fechaNacimiento;    }
    public String getAnio() { return anio; }
    public String getMes() { return mes; }
    public String getDia() { return dia; }

    // Setters
    public void setNombre(String nombre) {    this.nombre = nombre;    }
    public void setApellidoPaterno(String apellidoPaterno) {    this.apellidoPaterno = apellidoPaterno;    }
    public void setApellidoMaterno(String apellidoMaterno) {    this.apellidoMaterno = apellidoMaterno;    }
    public void setRfc(String rfc) {    this.rfc = rfc;    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {    this.fechaNacimiento = fechaNacimiento;    }
    public void setAnio(String anio) { this.anio = anio; }
    public void setMes(String mes) { this.mes = mes; }
    public void setDia(String dia) { this.dia = dia; }
}