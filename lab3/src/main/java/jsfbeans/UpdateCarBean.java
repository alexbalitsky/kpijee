package jsfbeans;

import entity.Driver;
import service.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obalitskyi on 10/26/16.
 */
@ManagedBean
@SessionScoped
public class UpdateCarBean {
    @EJB
    private CarService carService;

    private String id;

    private String brand;
    private String number;
    private String colour;
    private String price;

    private List<Driver> drivers = new ArrayList<>();
    private List<String> driversOfCar = new ArrayList<>();

    @PostConstruct
    public void init(){
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("carId");
    }

    public String update(){
        carService.update(id, brand, number, colour, price, driversOfCar);
        return "info?faces-redirect=true&includeViewParams=true&message=successfully updated car";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Driver> getDrivers() {
        drivers.clear();
        drivers.addAll(carService.getAllDrivers());
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<String> getDriversOfCar() {
        return driversOfCar;
    }

    public void setDriversOfCar(List<String> ids) {
        driversOfCar.clear();
        driversOfCar.addAll(ids);
    }
}
