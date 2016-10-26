package jsfbeans;

import entity.Car;
import entity.Driver;
import service.CarService;
import service.DriverService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by obalitskyi on 10/25/16.
 */
@ManagedBean
@RequestScoped
public class GetDriverBean {
    @EJB
    private DriverService driverService;

    private List<Driver> drivers = new ArrayList<>();
    private Map<Long, List<Car>> map = new HashMap<>();

    @PostConstruct
    public void init(){
        drivers.addAll(driverService.getAll());
        for (Driver driver : drivers){
            map.put(driver.getId(), new ArrayList<Car>(driver.getCars()));
        }
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Map<Long, List<Car>> getMap() {
        return map;
    }

    public void setMap(Map<Long, List<Car>> map) {
        this.map = map;
    }
}
