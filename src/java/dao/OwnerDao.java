/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Owner;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author aristide
 */
public class OwnerDao {
    private static Session session;
    
    public static Owner create(Owner owner){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(owner);
        session.getTransaction().commit();
        session.close();
        return owner;
    }
    
    public static Owner update(Owner owner){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(owner);
        session.getTransaction().commit();
        session.close();
        return owner;
    }
    
    public static Owner delete(Owner owner){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(owner);
        session.getTransaction().commit();
        session.close();
        return owner;
    }
    
    public static List<Owner> findAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Owner> owners = session.createQuery("From Owner").list();
        session.close();
        return owners;
    }
    
}
