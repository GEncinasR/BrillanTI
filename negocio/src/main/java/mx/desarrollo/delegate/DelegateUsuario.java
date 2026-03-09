package mx.desarrollo.delegate;

import mx.desarrollo.entity.Usuario;
import mx.desarrollo.integration.ServiceLocator;

import java.util.List;

public class DelegateUsuario {
    public Usuario login(String nombreUsuario, String contrasena){
        Usuario usuario = null;
        List<Usuario> usuarios = ServiceLocator.getInstanceUsuarioDAO().findAll();
        
        for(Usuario us : usuarios){
            if(us.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && 
               us.getContrasenaUsuario().equals(contrasena)){
                usuario = us;
                break;
            }
        }
        return usuario;
    }

    public Usuario findByNombre(String nombreUsuario) {
        List<Usuario> usuarios = ServiceLocator.getInstanceUsuarioDAO().findAll();
        if (usuarios != null) {
            for(Usuario us : usuarios){
                if(us.getNombreUsuario().equalsIgnoreCase(nombreUsuario.trim())){
                    return us;
                }
            }
        }
        return null;
    }
}
