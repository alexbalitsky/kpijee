package jsfbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 * Created by obalitskyi on 10/26/16.
 */
@ManagedBean
@RequestScoped
public class IndexBean {
    public String addCar(){
        return "addCar?faces-redirect=true";
    }

    public String addCarOwner(){
        return "addCarOwner?faces-redirect=true";
    }

    public String addDriver(){
        return "addDriver?faces-redirect=true";
    }

    public String getCars(){
        return "getCars?faces-redirect=true";
    }

    public String getCarOwners(){
        return "getCarOwners?faces-redirect=true";
    }

    public String getDrivers(){
        return "getDrivers?faces-redirect=true";
    }

}
