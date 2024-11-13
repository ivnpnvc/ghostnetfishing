package com.ghostnetfishing.Bean.DB;


import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Named
@ApplicationScoped
public class GhostNetDAO {

    private final  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Default");

    public List<GhostNet> GetAll ()
    {

        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT G FROM GhostNet G");
        List<GhostNet> nets =   query.getResultList();
        entityManager.close();
        return nets;
    }

    public void CreateNet(GhostNet ghostNet)
    {
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction t = entityManager.getTransaction();
        t.begin();


        entityManager.persist(ghostNet);



        t.commit();
        entityManager.close();
    }


    public void UpdateNet(GhostNet net)
    {
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction t = entityManager.getTransaction();
        t.begin();

        entityManager.merge(net);

        t.commit();
        entityManager.close();
    }

}
