package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeAlumno;
import mx.desarrollo.facade.FacadeProfesor;
import mx.desarrollo.facade.FacadeUsuario;

public class ServiceFacadeLocator {

    private static FacadeAlumno facadeAlumno;
    private static FacadeUsuario facadeUsuario;
    private static FacadeProfesor facadeProfesor;

    public static FacadeProfesor getInstanceFacadeProfesor(){
        if(facadeProfesor == null){
            facadeProfesor = new FacadeProfesor();
            return facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }


    public static FacadeAlumno getInstanceFacadeAlumno() {
        if (facadeAlumno == null) {
            facadeAlumno = new FacadeAlumno();
            return facadeAlumno;
        } else {
            return facadeAlumno;
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
}
