package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeUnidadAprendizaje;

public class ServiceFacadeLocator {


    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;

    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje;
        }
    }

}
