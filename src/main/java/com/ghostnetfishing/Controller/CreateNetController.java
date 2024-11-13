package com.ghostnetfishing.Controller;

import com.ghostnetfishing.Bean.App;
import com.ghostnetfishing.Bean.ControllerRequests.CreateNetRequest;
import com.ghostnetfishing.Bean.ControllerRequests.UserSession;
import com.ghostnetfishing.Bean.DB.GhostNet;
import com.ghostnetfishing.Bean.DB.GhostNetDAO;
import com.ghostnetfishing.Bean.DB.UserDAO;
import com.ghostnetfishing.Bean.DB.UserObj.Detector;
import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.List;


@Named("CreateNetController")
@RequestScoped
public class CreateNetController {



    private CreateNetRequest createNetRequest;

    private UserSession userSession;

    @Inject
    public CreateNetController(UserSession userSession) {
        this.userSession = userSession;
        this.createNetRequest = new CreateNetRequest((Detector) userSession.getCurrentUser());
    }


    public CreateNetController() {

    }


    public CreateNetRequest getCreateNetRequest() {
        return createNetRequest;
    }

    public void setCreateNetRequest(CreateNetRequest createNetRequest) {
        this.createNetRequest = createNetRequest;
    }



    public String CreateNet () {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.isValidationFailed()) {
            return null;
        }

        if (createNetRequest.getDetector() == null)
        {
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Bitte melden Sie sich als Meldende Person an", "Please Login in or Register"));
            context.isValidationFailed();
            return  null;
        }



        UserDAO userDAO = App.getApp().getUserDAO();
        GhostNetDAO ghostNetDAO = App.getApp().getGhostNetDAO();


        GhostNet ghostNet = new GhostNet(createNetRequest.getLatitude(), createNetRequest.getLongitude(), createNetRequest.getEstimatedSizeinm2());
        ghostNetDAO.CreateNet(ghostNet);

        Detector d = createNetRequest.getDetector();
        d.AddNet(ghostNet);
        userDAO.UpdateUser(d);
        ghostNetDAO.UpdateNet(ghostNet);

        return "Index.xhtml";
    }


    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
