package jsfbeans;

import entity.Car;
import entity.CarOwner;
import entity.Driver;
import service.CarOwnerService;
import service.CarService;

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
public class GetCarOwnerBean {
    @EJB
    private CarOwnerService carOwnerService;

    private List<CarOwner> carOwners = new ArrayList<>();
    private Map<Long, List<Car>> map = new HashMap<>();

    @PostConstruct
    public void init(){
        carOwners.addAll(carOwnerService.getAll());
        for (CarOwner carOwner : carOwners){
            map.put(carOwner.getId(), new ArrayList<Car>(carOwner.getCars()));
        }
    }

    public List<CarOwner> getCarOwners() {
        return carOwners;
    }

    public void setCarOwners(List<CarOwner> carOwners) {
        this.carOwners = carOwners;
    }

    public Map<Long, List<Car>> getMap() {
        return map;
    }

    public void setMap(Map<Long, List<Car>> map) {
        this.map = map;
    }
}
