package com.ghostnetfishing.Bean.DB.UserObj;

import com.ghostnetfishing.Bean.DB.GhostNet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Salvor  extends User{


    @OneToMany(mappedBy = "salvor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GhostNet> acceptedGhostNets = new ArrayList<>();

    public Salvor() {

    }




    public  void AddNet (GhostNet ghostNet)
    {
        this.acceptedGhostNets.add(ghostNet);
        ghostNet.setSalvor(this);
    }


    public List<GhostNet> getAcceptedGhostNets() {
        return acceptedGhostNets;
    }

    public void setAcceptedGhostNets(List<GhostNet> acceptedGhostNets) {
        this.acceptedGhostNets = acceptedGhostNets;
    }

    public Salvor(String firstName, String lastName, String eMail, int passwordHash, String phoneNumber) {
        super(firstName, lastName, eMail, passwordHash, phoneNumber);
    }
}
