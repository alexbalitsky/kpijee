package jsfbeans;

import entity.Car;
import service.CarOwnerService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obalitskyi on 10/26/16.
 */
@ManagedBean
@ViewScoped
public class UpdateCarOwnerBean {
    @EJB
    private CarOwnerService carOwnerService;

    private String id;

    private String name;
    private String surname;
    private String address;
    private String userName;
    private String password;

    private List<Car> cars = new ArrayList<>();
    private List<String> ownerCarsIds = new ArrayList<>();

    @PostConstruct
    public void init(){
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("carOwnerId");
    }

    public String update() {
        carOwnerService.update(id, name, surname, address, userName, password, ownerCarsIds);
        return "info?faces-redirect=true&includeViewParams=true&message=successfully updated car owner owner";
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
        cars.addAll(carOwnerService.getCarsWithoutOwners(id));
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getOwnerCarsIds() {
        return ownerCarsIds;
    }

    public void setOwnerCarsIds(List<String> ownerCarsIds) {
        this.ownerCarsIds = ownerCarsIds;
    }
}
