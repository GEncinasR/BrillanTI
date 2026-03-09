package helper;

import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Usuario;

import java.io.Serializable;

public class LoginHelper implements Serializable {

    /**
     * Metodo para hacer login llamara a la instancia de usuarioFacade
     * @param nombreUsuario
     * @param password
     * @return 
     */
    public Usuario login(String nombreUsuario, String password){
        return ServiceFacadeLocator.getInstanceFacadeUsuario().login(nombreUsuario, password);
    }

    public Usuario findByNombre(String nombreUsuario) {
        return ServiceFacadeLocator.getInstanceFacadeUsuario().findByNombre(nombreUsuario);
    }
}
