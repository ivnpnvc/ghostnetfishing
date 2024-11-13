package com.ghostnetfishing.Controller;


import com.ghostnetfishing.Bean.App;
import com.ghostnetfishing.Bean.DB.UserDAO;
import com.ghostnetfishing.Bean.DB.UserObj.Detector;
import com.ghostnetfishing.Bean.DB.UserObj.Salvor;
import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("UserDetailsController")
@RequestScoped
public class UserDetailsController {


    private String userID;
    private User user;

    private Salvor salvor;

    private Detector detector;

    private void LoadUser() {

        UserDAO userDAO = App.getApp().getUserDAO();
        int id = Integer.parseInt(userID);
        User u = userDAO.FindByID(id);
        this.user = u;

        if (u instanceof Salvor)
        {
            salvor = (Salvor)u;
        }

        if (u instanceof Detector)
        {
            detector = (Detector)u;
        }
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String netID) {
        this.userID = netID;
        LoadUser();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Salvor getSalvor() {
        return salvor;
    }

    public void setSalvor(Salvor salvor) {
        this.salvor = salvor;
    }

    public Detector getDetector() {
        return detector;
    }

    public void setDetector(Detector detector) {
        this.detector = detector;
    }
}
