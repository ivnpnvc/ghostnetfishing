package com.ghostnetfishing.Controller;

import com.ghostnetfishing.Bean.App;
import com.ghostnetfishing.Bean.ControllerRequests.LoginRequest;
import com.ghostnetfishing.Bean.ControllerRequests.UserSession;
import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("LoginController")
@RequestScoped
public class LoginController implements Serializable {

    @Inject
    private UserSession userSession;

    private LoginRequest loginRequest;

    public LoginController() {
        loginRequest = new LoginRequest();
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public String Login() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Überprüfen, ob bereits ein Benutzer eingeloggt ist
        if (userSession != null && userSession.getCurrentUser() != null) {
            context.addMessage(null, new FacesMessage("Sie sind bereits eingeloggt"));
            return null;
        }

        if (loginRequest == null || loginRequest.geteMail() == null || loginRequest.getPassword() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-Mail und Passwort werden benötigt", null));
            return null;
        }

        // Suche nach dem Benutzer in der Datenbank
        User foundUser = null;
        for (User u : App.getApp().getUserDAO().GetAll()) {
            if (u.geteMail().equals(loginRequest.geteMail())) {
                foundUser = u;
                break;
            }
        }

        // Überprüfung, ob der Benutzer gefunden wurde
        if (foundUser == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Benutzername oder Passwort falsch", null));
            return null;
        }

        // Überprüfung des Passworts
        int passwordHash = loginRequest.getPassword().hashCode();
        if (passwordHash != foundUser.getPasswordHash()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Benutzername oder Passwort falsch", null));
            return null;
        }

        // Benutzer zur Session hinzufügen
        userSession.setCurrentUser(foundUser);
        System.out.println("User " + foundUser.getId() + " erfolgreich eingeloggt.");

        return "Index.xhtml?faces-redirect=true"; // Navigiere zur Startseite nach erfolgreicher Anmeldung
    }
}
