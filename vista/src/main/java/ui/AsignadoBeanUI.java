package ui;

import helper.AsignadoHelper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.entity.Asignado;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.Unidadaprendizaje;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;

@Named("asignadoUI")
@SessionScoped
public class AsignadoBeanUI implements Serializable {

    private AsignadoHelper asignadoHelper;
    private Asignado asignado;

    private Integer idProfesor;
    private Integer idUA;

    private Date hrInicio;
    private Date hrFin;
    private Date minTime;
    private Date maxTime;

    private List<Profesor> listaProfesores;
    private List<Unidadaprendizaje> listaUA;
    private List<String> listaDias;
    private List<Asignado> horarioProfesor;
    private String horarioTexto;

    public AsignadoBeanUI() {
        asignadoHelper = new AsignadoHelper();
    }

    @PostConstruct
    public void init() {
        asignado = new Asignado();
        listaDias = Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
        
        //límites de tiempo
        // (7:00 AM a 9:00 PM)
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 7);
        cal.set(java.util.Calendar.MINUTE, 0);
        minTime = cal.getTime();
        
        cal.set(java.util.Calendar.HOUR_OF_DAY, 21);
        cal.set(java.util.Calendar.MINUTE, 0);
        maxTime = cal.getTime();

        //  listas vacías por mientras,
        // TODO: Integrar con otras entidades
        listaProfesores = new ArrayList<>();
        listaUA = new ArrayList<>();

    }

    public void asignar() {
        try {
            if (idProfesor == null || idUA == null || asignado.getDia() == null || hrInicio == null || hrFin == null) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Todos los campos son obligatorios."));
                return;
            }


            Profesor p = new Profesor();
            p.setId(idProfesor);
            asignado.setIdProfesor(p);

            Unidadaprendizaje ua = new Unidadaprendizaje();
            ua.setId(idUA);
            asignado.setIdUA(ua);

            asignado.setHrInicio(hrInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
            asignado.setHrFin(hrFin.toInstant().atZone(ZoneId.systemDefault()).toLocalTime());

            boolean exito = asignadoHelper.guardarAsignado(asignado);

            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito:", "Asignación guardada correctamente."));

                limpiarFormulario();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia:", "Esta asignación ya existe para los mismos horarios y día."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error fatal:", "Ocurrió un error al procesar la asignación."));
            e.printStackTrace();
        }
    }

    public String regresar() {
        return "index.xhtml?faces-redirect=true";
    }

    private void limpiarFormulario() {
        asignado = new Asignado();
        idProfesor = null;
        idUA = null;
        hrInicio = null;
        hrFin = null;
    }

    public void consultarHorarioProfesor() {

        horarioProfesor = asignadoHelper.obtenerHorarioProfesor(idProfesor);

        if (horarioProfesor == null || horarioProfesor.isEmpty()) {
            horarioTexto = "Este profesor no tiene horario asignado.";
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (Asignado a : horarioProfesor) {

            sb.append(a.getDia())
                    .append(" - ")
                    .append(a.getIdUA().getNombre())
                    .append(" ")
                    .append(a.getHrInicio())
                    .append(" a ")
                    .append(a.getHrFin())
                    .append("\n");

        }

        horarioTexto = sb.toString();
    }


    public Asignado getAsignado() { return asignado; }
    public void setAsignado(Asignado asignado) { this.asignado = asignado; }

    public Integer getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Integer idProfesor) { this.idProfesor = idProfesor; }

    public Integer getIdUA() { return idUA; }
    public void setIdUA(Integer idUA) { this.idUA = idUA; }

    public Date getHrInicio() { return hrInicio; }
    public void setHrInicio(Date hrInicio) { this.hrInicio = hrInicio; }

    public Date getHrFin() { return hrFin; }
    public void setHrFin(Date hrFin) { this.hrFin = hrFin; }

    public Date getMinTime() { return minTime; }
    public void setMinTime(Date minTime) { this.minTime = minTime; }

    public Date getMaxTime() { return maxTime; }
    public void setMaxTime(Date maxTime) { this.maxTime = maxTime; }

    public List<Profesor> getListaProfesores() { return listaProfesores; }
    public void setListaProfesores(List<Profesor> listaProfesores) { this.listaProfesores = listaProfesores; }

    public List<Unidadaprendizaje> getListaUA() { return listaUA; }
    public void setListaUA(List<Unidadaprendizaje> listaUA) { this.listaUA = listaUA; }

    public List<String> getListaDias() { return listaDias; }
    public void setListaDias(List<String> listaDias) { this.listaDias = listaDias; }

    public String getHorarioTexto() {
        return horarioTexto;
    }

    public List<Asignado> getHorarioProfesor() {
        return horarioProfesor;
    }
}
