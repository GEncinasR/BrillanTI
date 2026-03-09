package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUsuario;
import mx.desarrollo.entity.Usuario;

public class FacadeUsuario {

    private final DelegateUsuario delegateUsuario;

    public FacadeUsuario() {
        this.delegateUsuario = new DelegateUsuario();
    }

    public Usuario login(String nombreUsuario, String contrasena){
        return delegateUsuario.login(nombreUsuario, contrasena);
    }

    public Usuario findByNombre(String nombreUsuario) {
        return delegateUsuario.findByNombre(nombreUsuario);
    }
}
