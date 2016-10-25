package jsfbeans;

import service.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by obalitskyi on 10/25/16.
 */
@ManagedBean
@SessionScoped
public class AddCar {
    @EJB
    private CarService carService;

    private String brand;
    private String number;
    private String colour;
    private String price;

    @PostConstruct
    public void init(){

    }

    public void save() {
        carService.save(brand, number, colour, price);
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
}
