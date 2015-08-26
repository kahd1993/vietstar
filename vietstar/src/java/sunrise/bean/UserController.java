/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sunrise.dao.UserDao;
import sunrise.entity.Users;

/**
 *
 * @author Do Quoc Hoa
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserController {
    private Users lg = null;
    private UserDao userDao = null;
    private List<Users> lstUsers = null;
    private boolean loggedIn;

    public UserController() {
        lg = new Users();
        userDao = new UserDao();
        lstUsers = new ArrayList<>();
    }

    public Users getLg() {
        return lg;
    }

    public void setLg(Users lg) {
        this.lg = lg;
    }

    public List<Users> getLstUsers() {
        return lstUsers;
    }

    public void setLstUsers(List<Users> lstUsers) {
        this.lstUsers = lstUsers;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String login(){
        lg = userDao.login(lg.getUsn(), lg.getPwd());
        FacesMessage message = null;
        if(lg == null) {
            loggedIn = false;
            lg = new Users();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Đăng nhập thất bại.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }else{
            loggedIn = true;
            return "/admin/index?faces-redirect=true";
        } 
    }
    
    public String logout() {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }
}
