package ui;

import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarrollo.entity.UnidadAprendizaje;
import helper.UnidadAprendizajeHelper;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;


@Named("unidadAprendizajeBeanUI")
@ViewScoped
public class UnidadAprendizajeBeanUI implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AsignadoBeanUI asignadoUI;

    private List<UnidadAprendizaje> listaUnidades;
    private DelegateUnidadAprendizaje delegate;
    private UnidadAprendizajeHelper helper;

    /**
     * instancia que se utilizará para el formulario de alta
     */
    private UnidadAprendizaje unidad = new UnidadAprendizaje();

    @PostConstruct
    public void init() {
        System.out.println("\n███ [UnidadAprendizajeBeanUI.init()] INICIALIZANDO ███");
        delegate = new DelegateUnidadAprendizaje();
        helper = new UnidadAprendizajeHelper();
        this.listaUnidades = delegate.getUA();
        System.out.println("✓ Unidades obtenidas: " + (listaUnidades != null ? listaUnidades.size() : "NULL"));
        if (listaUnidades != null && !listaUnidades.isEmpty()) {
            for (UnidadAprendizaje u : listaUnidades) {
                System.out.println("  → ID: " + u.getId() + ", Nombre: " + u.getNombre());
            }
        }
        System.out.println("███ [FIN init()] ███\n");
    }

    public List<UnidadAprendizaje> getListaUnidades() {
        return listaUnidades;
    }

    public UnidadAprendizaje getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadAprendizaje unidad) {
        this.unidad = unidad;
    }

    /**
     * Guarda la unidad en la base de datos si pasa las validaciones.
     * Muestra mensajes emergentes (FacesContext) para informar al usuario.
     * @return null para permanecer en la misma página
     */
    public String guardar() {
        if (helper.validar(unidad)) {
            // Verificar si el nombre ya existe
            if (nombreYaExiste(unidad.getNombre())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Nombre duplicado",
                        "El nombre '" + unidad.getNombre() + "' ya existe. Por favor, use un nombre diferente.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                delegate.saveUnidadAprendizaje(unidad);
                // refrescar la lista después del alta
                listaUnidades = delegate.getUA();
                
                if (asignadoUI != null) {
                    asignadoUI.refreshData();
                }

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Unidad guardada correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                // limpiar el objeto para un posible nuevo registro
                unidad = new UnidadAprendizaje();
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Datos no válidos",
                    "Asegúrese de que el nombre no esté vacío, las horas estén entre 0 y 4, y al menos una hora sea mayor que 0.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }

    /**
     * Verifica si un nombre de UA ya existe en la lista
     * @param nombre el nombre a verificar
     * @return true si el nombre ya existe, false en caso contrario
     */
    private boolean nombreYaExiste(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || listaUnidades == null) {
            return false;
        }
        return listaUnidades.stream()
                .anyMatch(ua -> ua.getNombre().equalsIgnoreCase(nombre.trim()));
    }
}
