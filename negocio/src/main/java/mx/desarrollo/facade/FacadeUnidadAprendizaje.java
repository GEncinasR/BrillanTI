package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.List;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegateUnidadAprendizaje;

    public FacadeUnidadAprendizaje() {
        this.delegateUnidadAprendizaje = new DelegateUnidadAprendizaje();
    }

    public List<UnidadAprendizaje> listaCompletaUA() {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();
    }

    public void guardarUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        delegateUnidadAprendizaje.saveUnidadAprendizaje(unidadAprendizaje);
    }
}
