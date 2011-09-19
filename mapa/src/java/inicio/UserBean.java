/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

//import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import vo.UsUsuariosVO;

//import javax.faces.bean.SessionScoped;

/**
 *
 * @author MADD
 */
@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean implements Serializable {
    private UsUsuariosVO userVO = new UsUsuariosVO();
    private Boolean login = false;

    public UserBean() {
    }

    public UsUsuariosVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UsUsuariosVO userVO) {
        this.userVO = userVO;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
    
    
}
