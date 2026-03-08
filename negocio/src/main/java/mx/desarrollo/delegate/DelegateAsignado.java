package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignado;
import mx.desarrollo.integration.ServiceLocator;

import java.util.List;

public class DelegateAsignado {

    public boolean saveAsignado(Asignado asignado) {
        if (ServiceLocator.getInstanceAsignadoDAO().existe(asignado)) {
            return false;
        } else {
            ServiceLocator.getInstanceAsignadoDAO().save(asignado);
            return true;
        }
    }

    public List<Asignado> findAll() {
        return ServiceLocator.getInstanceAsignadoDAO().findAll();
    }
}
