/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.OwnerDao;
import domain.Owner;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author aristide
 */
@ManagedBean
@RequestScoped
public class OwnerModel {

    /**
     * Creates a new instance of OwnerModel
     */

    private Owner owner;
    private List<Owner> owners;

    public OwnerModel() {
        this.owners = OwnerDao.findAll();
        this.owner = new Owner();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public String registerOwner() {
        try {
            OwnerDao.create(owner);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null, 
                            new FacesMessage( owner.getName()+" Successfully saved", owner.getName()+" Successfully saved"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(
                            null, 
                            new FacesMessage("Something went wrong", "Something went wrong"));
        }
        owner = new Owner();
        owners = OwnerDao.findAll();
        return "owner.xhtml?faces-redirect=true";
    }

}
