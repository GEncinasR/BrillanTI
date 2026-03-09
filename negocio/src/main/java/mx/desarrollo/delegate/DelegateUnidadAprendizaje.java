package mx.desarrollo.delegate;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.facade.FacadeUnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.integration.ServiceLocator;

import java.util.List;

public class DelegateUnidadAprendizaje {

    public void saveUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().save(unidadAprendizaje);
    }

    public List<UnidadAprendizaje> getUA() {
        // 1. Usamos el localizador para obtener la instancia de la fachada.
        FacadeUnidadAprendizaje facade = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje();

        // 2. Delegamos la petición a la fachada de negocio.
        return facade.listaCompletaUA();
    }

}
