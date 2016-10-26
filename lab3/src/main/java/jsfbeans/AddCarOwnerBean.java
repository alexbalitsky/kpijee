package jsfbeans;

import entity.Car;
import entity.CarOwner;
import service.CarOwnerService;
import service.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obalitskyi on 10/25/16.
 */
@ManagedBean
@RequestScoped
public class AddCarOwnerBean {
    @EJB
    private CarOwnerService carOwnerService;

    private String name;
    private String surname;
    private String address;
    private String userName;
    private String password;

    private List<Car> cars = new ArrayList<>();
    private List<String> ownerCarsIds = new ArrayList<>();

    @PostConstruct
    public void init(){

    }

    public String save() {
        carOwnerService.save(name, surname, address, userName, password, ownerCarsIds);
        return "info?faces-redirect=true&includeViewParams=true&message=successfully saved car owner";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Car> getCars() {
        cars.clear();
        cars.addAll(carOwnerService.getCarsWithoutOwners());
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<String> getOwnerCars() {
        return ownerCarsIds;
    }

    public void setOwnerCars(List<String> ids) {
        ownerCarsIds.clear();
        ownerCarsIds.addAll(ids);
    }
}
