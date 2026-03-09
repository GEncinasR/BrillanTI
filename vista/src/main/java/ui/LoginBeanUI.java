package ui;

import helper.LoginHelper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.entity.Usuario;

import java.io.IOException;
import java.io.Serializable;

@Named("loginUI")
@SessionScoped
public class LoginBeanUI implements Serializable {
    private LoginHelper loginHelper;
    private Usuario usuario;

    public LoginBeanUI() {
        loginHelper = new LoginHelper();
    }

    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public void login() throws IOException {
        String appURL = "/index.xhtml";
        
        Usuario existingUser = loginHelper.findByNombre(usuario.getNombreUsuario());
        
        if (existingUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error de acceso",
                        "El usuario '" + usuario.getNombreUsuario() + "' no existe"));
            return;
        }

        Usuario loggedIn = loginHelper.login(usuario.getNombreUsuario(), usuario.getContrasenaUsuario());
        
        if (loggedIn != null && loggedIn.getId() != null) {
            this.usuario = loggedIn;
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Sesión iniciada correctamente como " + loggedIn.getNombreUsuario()));
            
            // Usamos KeepMessages para que el growl se vea despues de redireccionar si es necesario, 
            // aunque usualmente el redirect limpia el contexto. 
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + appURL
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña incorrecta", "La contraseña ingresada para '" + usuario.getNombreUsuario() + "' es inválida"));
        }
    }

    /* getters y setters */
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
