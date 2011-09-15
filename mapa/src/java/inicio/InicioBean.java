/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import facade.facade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import view.ConfiguracionBean;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "inicioBean")
@RequestScoped
public class InicioBean {
    private String user;
    private String password;
    private String Mensaje = "";
    private String rutaContexto;
    /** Creates a new instance of InicioBean */
    public InicioBean() {
        // ----- CARGAR CONFIGURACION -----
        ConfiguracionBean configuracion = (ConfiguracionBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("ConfiguracionBean");
        rutaContexto = configuracion.getRutaContexto();
        // --------------------------------
    }

    public String validarUsuario() {
        facade fachada = new facade();
        UsUsuariosVO userVO = null;
        userVO = fachada.iniciarSesion(user, password);

        if (userVO != null){
            userVO.getCodigoSIUST().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                //Usuario logueado
                UserBean usuario = new UserBean();
                usuario.setUserVO(userVO);
                usuario.setLogin(true);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                facesContext.getExternalContext().getSessionMap().put("UserBean", usuario);
                Mensaje = "<br>Sesión iniciada<br><br>";
                return null;
            } else {
                //Usuario está deshabilitado
                Mensaje = "<br>Usuario " + userVO.getCodigoSIUST().getLogin() + " está deshabilitado<br><br>";
                return null;
            }
        } else {
            //Usuario o contraseña incorrectos
            Mensaje = "<br>Nombre de usuario o contraseña incorrectos<br><br>";
            return null;
        }
        
    }

    public String cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate(); 
        
        return rutaContexto+"cerrar";
    }
    
    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
