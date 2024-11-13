package com.ghostnetfishing.Bean.ControllerRequests;



import com.ghostnetfishing.Bean.DB.UserObj.Detector;
import com.ghostnetfishing.Bean.DB.UserObj.Salvor;
import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named("UserSession")
public class UserSession  implements Serializable {

    public UserSession() {
        currentUser = null;
    }
    private User currentUser;

    public boolean isLoggedIn ()
    {
        if (currentUser != null)
        {
            return true;
        }
        return false;
    }

    public  boolean isSalvor ()
    {
        if (!isLoggedIn())
        {
            return false;
        }
        if (currentUser instanceof Salvor)
        {
            return true;
        }
        return false;
    }
    public  boolean isDetector()
    {
        if (!isLoggedIn())
        {
            return false;
        }
        if (currentUser instanceof Detector)
        {
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}