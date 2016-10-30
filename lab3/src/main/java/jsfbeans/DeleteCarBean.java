package jsfbeans;

import service.CarService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by alex on 30.10.16.
 */
@ManagedBean
@RequestScoped
public class DeleteCarBean {
    @EJB
    private CarService carService;

    public String delete(String id){
        carService.delete(id);
        return "getCars?faces-redirect=true&includeViewParams=true";
    }
}
