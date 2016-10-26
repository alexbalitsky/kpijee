package jsfbeans;

import entity.Car;
import entity.Driver;
import service.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.*;

/**
 * Created by obalitskyi on 10/25/16.
 */
@ManagedBean
@RequestScoped
public class GetCarBean {
    @EJB
    private CarService carService;

    private List<Car> cars = new ArrayList<>();
    private Map<Long, List<Driver>> map = new HashMap<>();

    @PostConstruct
    public void init(){
        cars.addAll(carService.getAll());
        for (Car car : cars){
            map.put(car.getId(), new ArrayList<Driver>(car.getDrivers()));
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Map<Long, List<Driver>> getMap() {
        return map;
    }

    public void setMap(Map<Long, List<Driver>> map) {
        this.map = map;
    }
}
