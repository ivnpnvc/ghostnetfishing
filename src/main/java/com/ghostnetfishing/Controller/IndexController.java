package com.ghostnetfishing.Controller;

import com.ghostnetfishing.Bean.App;
import com.ghostnetfishing.Bean.ControllerRequests.UserSession;
import com.ghostnetfishing.Bean.DB.GhostNet;
import com.ghostnetfishing.Bean.DB.GhostNetDAO;
import com.ghostnetfishing.Bean.DB.GhostNetState;
import com.ghostnetfishing.Bean.DB.UserDAO;
import com.ghostnetfishing.Bean.DB.UserObj.Salvor;
import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("IndexController")
@RequestScoped
public class IndexController implements Serializable {

    private List<GhostNet> ghostNets;
    private GhostNet selectedNet;

    @Inject
    private UserSession userSession;

    public IndexController() {
        ghostNets = App.getApp().getGhostNetDAO().GetAll();
    }

    public String Register() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (userSession == null || userSession.getCurrentUser() == null) {
            return "Register.xhtml";
        }
        context.addMessage(null, new FacesMessage("You are already logged in"));
        return null;
    }

    public void AcceptSalvage(GhostNet net) {
        if (userSession == null || userSession.getCurrentUser() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User session is not available or user is not logged in", null));
            return;
        }

        UserDAO userDAO = App.getApp().getUserDAO();
        GhostNetDAO ghostNetDAO = App.getApp().getGhostNetDAO();

        Salvor s = (Salvor) userSession.getCurrentUser();
        s.AddNet(net);
        net.setState(GhostNetState.SALVAGE_IMMINENT);
        userDAO.UpdateUser(s);
        ghostNetDAO.UpdateNet(net);
    }

    public void FinishSalvage(GhostNet net) {
        if (net == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selected net is not available", null));
            return;
        }
        GhostNetDAO ghostNetDAO = App.getApp().getGhostNetDAO();
        net.setState(GhostNetState.SECURE);
        ghostNetDAO.UpdateNet(net);
    }

    public void MissingSalvage(GhostNet net) {
        if (net == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selected net is not available", null));
            return;
        }
        GhostNetDAO ghostNetDAO = App.getApp().getGhostNetDAO();
        net.setState(GhostNetState.MISSING);
        ghostNetDAO.UpdateNet(net);
    }

    public String NavigateUserDetails() {
        if (userSession == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User session is not available", null));
            return null;
        }

        User u = userSession.getCurrentUser();
        if (u == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No user is logged in", null));
            return null;
        }

        return "UserDetails?faces-redirect=true&userID=" + u.getId();
    }

    public List<GhostNet> getGhostNets() {
        return ghostNets;
    }

    public void setGhostNets(List<GhostNet> ghostNets) {
        this.ghostNets = ghostNets;
    }

    public GhostNet getSelectedNet() {
        return selectedNet;
    }

    public void setSelectedNet(GhostNet selectedNet) {
        this.selectedNet = selectedNet;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
