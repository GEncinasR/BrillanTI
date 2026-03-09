package helper;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;

public class ProfesorHelper implements Serializable {

    public void altaProfesor(Profesor nuevoProfe){
        ServiceFacadeLocator.getInstanceFacadeProfesor().guardarProfesor(nuevoProfe);
    }

    public boolean existeRFC(String rfc){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().buscarPorRFC(rfc) != null;
    }
}
