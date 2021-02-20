/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.CarDao;
import dao.OwnerDao;
import domain.Car;
import domain.Owner;
import domain.PassengerCar;
import domain.Truck;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aristide
 */
@ManagedBean
@SessionScoped
public class CarModel {
    /**
     * Creates a new instance of CarModel
     */ 

    private PassengerCar passengerCar;
    private Truck truck;
    
    private List<PassengerCar> passengerCars;
    private List<Truck> trucks;
    
    private boolean isUpdateTruck = false;
    private boolean isUpdatePassengerCar = false;
    
    private Map<Integer, String> map;
    
    public CarModel() {
        this.map = new HashMap<>();
        this.trucks = CarDao.findAllTruck();
        this.passengerCars = CarDao.findAllPassengerCar();
        this.truck = new Truck();
        this.passengerCar = new PassengerCar();
        
        for (Owner owner : OwnerDao.findAll()) {
            map.put(owner.getId(), owner.getName()+" ("+owner.getNationalId()+")");
        }
    }

    public PassengerCar getPassengerCar() {
        return passengerCar;
    }

    public void setPassengerCar(PassengerCar passengerCar) {
        this.passengerCar = passengerCar;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<PassengerCar> getPassengerCars() {
        return passengerCars;
    }

    public void setPassengerCars(List<PassengerCar> passengerCars) {
        this.passengerCars = passengerCars;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }
    
    public boolean isIsUpdateTruck() {
        return isUpdateTruck;
    }

    public void setIsUpdateTruck(boolean isUpdateTruck) {
        this.isUpdateTruck = isUpdateTruck;
    }
    
    public boolean isIsUpdatePassengerCar() {
        return isUpdatePassengerCar;
    }

    public void setIsUpdatePassengerCar(boolean isUpdatePassengerCar) {
        this.isUpdatePassengerCar = isUpdatePassengerCar;
    }
    
    public String registerPassengerCars(){
        try {
            CarDao.create(passengerCar);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null, 
                            new FacesMessage( passengerCar.getPlateNumber()+" Successfully saved", passengerCar.getPlateNumber()+" Successfully saved"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null, 
                            new FacesMessage("Something went wrong", "Something went wrong"));
        }
        passengerCar = new PassengerCar();
        passengerCars = CarDao.findAllPassengerCar();
        return "passenger-car.xhtml?faces-redirect=true";
    }
    public String registerTruck(){
        try {
            CarDao.create(truck);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null, 
                            new FacesMessage( truck.getPlateNumber()+" Successfully saved", truck.getPlateNumber()+" Successfully saved"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null, 
                            new FacesMessage("Something went wrong", "Something went wrong"));
        }
        truck = new Truck();
        trucks = CarDao.findAllTruck();
        return "truck.xhtml?faces-redirect=true";
    }
    
    public String truckUpdateSwitch(Truck t){
        truck = t;
        isUpdateTruck = true;
        return "truck.xhtml?faces-redirect=true";
    }
    
    public String canceTruckUpdate(){
        isUpdateTruck = false;
        truck = new Truck();
        return "truck.xhtml?faces-redirect=true";
    }
    
    public String passengerCarUpdateSwitch(PassengerCar p){
        passengerCar = p;
        isUpdatePassengerCar = true;
        return "passenger-car.xhtml?faces-redirect=true";
    }
    
    public String cancelpassengerCarUpdate(){
        isUpdatePassengerCar = false;
        passengerCar = new PassengerCar();
        return "passenger-car.xhtml?faces-redirect=true";
    }

    public String deletePassengerCar(Car car){
        CarDao.delete(car);
        passengerCars = CarDao.findAllPassengerCar();
        return "passenger-car.xhtml?faces-redirect=true";
    }
    
    public String deleteTruck(Car car){
        CarDao.delete(car);
        trucks = CarDao.findAllTruck();
        return "truck.xhtml?faces-redirect=true";
    }
}
