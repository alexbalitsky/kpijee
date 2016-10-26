package jsfbeans;

import entity.Car;
import service.DriverService;

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
public class UpdateDriverBean {
    @EJB
    private DriverService driverService;

    private String id;

    private String name;
    private String surname;
    private String salary;

    private List<Car> cars = new ArrayList<>();
    private List<String> carsOfDriver = new ArrayList<>();

    @PostConstruct
    public void init(){
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("driverId");
    }

    public String update() {
        driverService.update(id, name, surname, salary, carsOfDriver);
        return "info?faces-redirect=true&includeViewParams=true&message=successfully updated driver";
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public List<Car> getCars() {
        cars.clear();
        cars.addAll(driverService.getAllCars());
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<String> getCarsOfDriver() {
        return carsOfDriver;
    }

    public void setCarsOfDriver(List<String> ids) {
        carsOfDriver.clear();
        carsOfDriver.addAll(ids);
    }
}
