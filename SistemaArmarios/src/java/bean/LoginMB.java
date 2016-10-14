
package bean;

import java.io.Serializable;
import model.Usuario;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginMB implements Serializable{

    @Inject
    UsuarioMB usuarioMB;
    





}
