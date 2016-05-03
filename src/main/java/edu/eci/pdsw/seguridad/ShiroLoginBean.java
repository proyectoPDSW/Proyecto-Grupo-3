/**
 * Very simple bean that authenticates the user via Apache Shiro, using JSF
 * @author Daniel Mascarenhas
 */
package edu.eci.pdsw.seguridad;

import edu.eci.pdsw.log.Registro;
import java.io.File;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "loginBean")
@ViewScoped
public class ShiroLoginBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ShiroLoginBean.class);

    private String username;
    private String password;
    private Boolean rememberMe;

    public ShiroLoginBean() {
        
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    
    /**
     * Try and authenticate the user
     */
    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getRememberMe());

        try {
            subject.login(token);

            if (subject.hasRole("admin")) {               
                FacesContext.getCurrentInstance().getExternalContext().redirect("restricted/index.xhtml");
            }
            else if (subject.hasRole("laboratorista")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("laboratorista/index.xhtml");
            }  
            else if(subject.hasRole("estudiante")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("estudiante/consultaequipos.xhtml");
            }
            else if(subject.hasRole("profesor")){
                 FacesContext.getCurrentInstance().getExternalContext().redirect("profesor/profesor.xhtml");
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("open/disponibilidad.xhtml");
            }
        }
        catch (UnknownAccountException ex) {
            facesError("El usuario no se encuentra registrado. Por favor, verifique los datos");
            Registro.anotar(ex);
        }
        catch (IncorrectCredentialsException ex) {
            facesError("Datos erróneos. Por favor, inténtelo otra vez.");
             Registro.anotar(ex);
        }
        catch (LockedAccountException ex) {
            facesError("Su cuenta ha sido bloqueada. Por favor, inténtelo más tarde");
             Registro.anotar(ex);
        }
        catch (AuthenticationException | IOException ex) {
            facesError("Error inesperado: " + ex.getMessage());
            Registro.anotar(ex);
        }
        finally {
            token.clear();
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean lembrar) {
        this.rememberMe = lembrar;
    }
}
