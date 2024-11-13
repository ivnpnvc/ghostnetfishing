package com.ghostnetfishing.Controller;


import com.ghostnetfishing.Bean.ControllerRequests.UserSession;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("LogoutController")
@RequestScoped
public class LogoutController {

    @Inject
    UserSession userSession;


    public String Logout ()
    {
        userSession.setCurrentUser(null);

        return "Index.xhtml?faces-redirect=true";
    }
}
