/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Car;
import domain.Owner;
import domain.PassengerCar;
import domain.Truck;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author aristide
 */
public class CarDao {
    
    private static Session session;
    
    
    public static Car create(Car car){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(car);
        session.getTransaction().commit();
        session.close();
        return car;
    }
    
    public static Car update(Car car){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(car);
        session.getTransaction().commit();
        session.close();
        return car;
    }
    
    public static Car delete(Car car){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
        return car;
    }
    
    public static List<Car> findAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Car> cars = session.createQuery("From Car").list();
        session.close();
        return cars;
    }
    
    public static List<PassengerCar> findAllPassengerCar(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<PassengerCar> cars = session.createQuery("From PassengerCar").list();
        session.close();
        return cars;
    }
    
    public static List<Truck> findAllTruck(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Truck> cars = session.createQuery("From Truck").list();
        session.close();
        return cars;
    }

    
    
}
