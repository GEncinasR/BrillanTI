package helper;

import mx.desarrollo.entity.Asignado;
import mx.desarrollo.integration.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

public class AsignadoHelper implements Serializable {

    public boolean guardarAsignado(Asignado asignado) {
        return ServiceFacadeLocator.getInstanceFacadeAsignado().guardarAsignado(asignado);
    }

    public List<Asignado> obtenerTodos() {
        return ServiceFacadeLocator.getInstanceFacadeAsignado().findAll();
    }

    public List<Asignado> obtenerHorarioProfesor(Integer idProfesor){
        return ServiceFacadeLocator
                .getInstanceFacadeAsignado()
                .obtenerPorProfesor(idProfesor);
    }
}
