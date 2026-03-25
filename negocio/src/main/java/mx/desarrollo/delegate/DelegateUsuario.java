package mx.desarrollo.delegate;

import mx.desarrollo.entity.Usuario;
import mx.desarrollo.integration.ServiceLocator;

import java.util.List;

public class DelegateUsuario {
    public Usuario login(String nombreUsuario, String contrasena){
        Usuario us = ServiceLocator.getInstanceUsuarioDAO().findByOneParameterUnique(nombreUsuario, "nombreUsuario");
        if (us != null && us.getContrasenaUsuario().equals(contrasena)) {
            return us;
        }
        return null;
    }

    public Usuario findByNombre(String nombreUsuario) {
        return ServiceLocator.getInstanceUsuarioDAO().findByOneParameterUnique(nombreUsuario.trim(), "nombreUsuario");
    }
}
