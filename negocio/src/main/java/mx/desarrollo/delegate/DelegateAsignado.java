package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignado;
import mx.desarrollo.integration.ServiceLocator;

import java.util.List;

public class DelegateAsignado {

    public boolean saveAsignado(Asignado asignado) {
        boolean existe = ServiceLocator.getInstanceAsignadoDAO().existe(asignado);
        System.out.println("DEBUG: Asignación existe? " + existe);
        if (existe) {
            return false;
        } else {
            ServiceLocator.getInstanceAsignadoDAO().save(asignado);
            return true;
        }
    }

    public List<Asignado> findAll() {
        return ServiceLocator.getInstanceAsignadoDAO().findAll();
    }

    public List<Asignado> obtenerPorProfesor(Integer idProfesor){
        return ServiceLocator.getInstanceAsignadoDAO().obtenerPorProfesor(idProfesor);
    }
}
