package com.ghostnetfishing.Bean.DB.UserObj;

import com.ghostnetfishing.Bean.DB.GhostNet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Detector extends User{


    @OneToMany(mappedBy = "detector", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GhostNet> createdGhostNets = new ArrayList<>();

    public Detector() {

    }

    public List<GhostNet> getCreatedGhostNets() {
        return createdGhostNets;
    }

    public void setCreatedGhostNets(List<GhostNet> createdGhostNets) {
        this.createdGhostNets = createdGhostNets;
    }

    public  void AddNet (GhostNet ghostNet)
    {
        this.createdGhostNets.add(ghostNet);
        ghostNet.setDetector(this);
    }


    public Detector(String firstName, String lastName, String eMail, int passwordHash, String phoneNumber) {
        super(firstName, lastName, eMail, passwordHash, phoneNumber);
    }
}
