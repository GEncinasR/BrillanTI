package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeUnidadAprendizaje;
import mx.desarrollo.facade.FacadeAlumno;
import mx.desarrollo.facade.FacadeProfesor;
import mx.desarrollo.facade.FacadeUsuario;
import mx.desarrollo.facade.FacadeAsignado;

public class ServiceFacadeLocator {

    private static FacadeAlumno facadeAlumno;
    private static FacadeUsuario facadeUsuario;
    private static FacadeAsignado facadeAsignado;
    private static FacadeProfesor facadeProfesor;

    public static FacadeProfesor getInstanceFacadeProfesor(){
        if(facadeProfesor == null){
            facadeProfesor = new FacadeProfesor();
            return facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }


    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;

    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje;
        }
    }

    public static FacadeUsuario getInstanceFacadeUsuario() {
        if (facadeUsuario == null) {
            facadeUsuario = new FacadeUsuario();
            return facadeUsuario;
        } else {
            return facadeUsuario;
        }
    }

    public static FacadeAsignado getInstanceFacadeAsignado() {
        if (facadeAsignado == null) {
            facadeAsignado = new FacadeAsignado();
            return facadeAsignado;
        } else {
            return facadeAsignado;
        }
    }
}
