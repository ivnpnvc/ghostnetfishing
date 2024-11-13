package com.ghostnetfishing.Bean.DB;



import com.ghostnetfishing.Bean.DB.UserObj.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;


@Named
@ApplicationScoped
public class UserDAO {
    private final  static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Default");

    public List<User> GetAll ()
    {

        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT U FROM User U");
        List<User> users =   query.getResultList();
        entityManager.close();
        return users;
    }

    public void CreateUser(User user)
    {
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction t = entityManager.getTransaction();
        t.begin();

        entityManager.persist(user);

        t.commit();
        entityManager.close();
    }

    public void UpdateUser(User user)
    {
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction t = entityManager.getTransaction();
        t.begin();

        entityManager.merge(user);

        t.commit();
        entityManager.close();
    }

    public User FindByID (int id)
    {
        EntityManager entityManager = emf.createEntityManager();
        User u = entityManager.find(User.class,id);
        entityManager.close();
        return u;
    }




}
