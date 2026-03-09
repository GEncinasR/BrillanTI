package mx.desarrollo.delegate;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.integration.ServiceLocator;

public class DelegateProfesor {

    // Para guardar un profesor
    public void saveProfesor(Profesor profe){
        ServiceLocator.getInstanceProfesorDAO().save(profe);
    }

    // Para buscar profesor por rfc
    public Profesor buscarPorRFC(String rfc){
        return ServiceLocator.getInstanceProfesorDAO().buscarPorRFC(rfc);
    }

    public java.util.List<Profesor> findAll() {
        return ServiceLocator.getInstanceProfesorDAO().findAll();
    }
}
