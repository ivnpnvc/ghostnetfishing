package com.ghostnetfishing.Controller;



import com.ghostnetfishing.Bean.App;
import com.ghostnetfishing.Bean.ControllerRequests.RegisterRequest;
import com.ghostnetfishing.Bean.ControllerRequests.Role;
import com.ghostnetfishing.Bean.ControllerRequests.UserSession;
import com.ghostnetfishing.Bean.DB.UserObj.Detector;
import com.ghostnetfishing.Bean.DB.UserObj.Salvor;
import com.ghostnetfishing.Bean.DB.UserObj.User;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("RegisterController")
@RequestScoped
public class RegisterController implements Serializable {




    public RegisterController() {
        registerRequest = new RegisterRequest();
        selectedRole = Role.DETECTOR;
    }

    @Inject
    UserSession userSession;

    private RegisterRequest registerRequest;
    private Role selectedRole;

    public String Register ()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!registerRequest.getPassword().equals(registerRequest.getRepeatPassword()))
        {
            context.addMessage( "pwd", new FacesMessage("Passwort stimmt nicht überein"));
            return null;
        }

        int passwordHash = registerRequest.getPassword().hashCode();



        for (User u : App.getApp().getUserDAO().GetAll()) {
            if (u.geteMail().equals(registerRequest.geteMail())) {
                context.addMessage(null,new FacesMessage("User already exists"));
                return null;
            }
        }

        switch (selectedRole)
        {
            case SALVOR:
                Salvor s = new Salvor(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.geteMail(),passwordHash, registerRequest.getPhoneNumber());
                App.getApp().getUserDAO().CreateUser(s);
                userSession.setCurrentUser(s);
                break;
            case DETECTOR:
                User d = new Detector(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.geteMail(),passwordHash, registerRequest.getPhoneNumber());
                App.getApp().getUserDAO().CreateUser(d);
                userSession.setCurrentUser(d);
                break;
        }


        return "Index.xhtml?faces-redirect=true";
    }



    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role role) {
        this.selectedRole = role;
    }


    public Role[] getRoles()
    {
        return Role.values();
    }
}
