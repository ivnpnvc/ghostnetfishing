package com.ghostnetfishing.Bean;

import com.ghostnetfishing.Bean.DB.GhostNetDAO;
import com.ghostnetfishing.Bean.DB.UserDAO;


import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class App {
    private UserDAO userDAO;
    private GhostNetDAO ghostNetDAO;



    private static App app;


    public static App getApp ()
    {
        if (app == null){
            app = new App();
        }
        return app;
    }

    public App() {
        userDAO = new UserDAO();
        ghostNetDAO = new GhostNetDAO();

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public GhostNetDAO getGhostNetDAO() {
        return ghostNetDAO;
    }


}
