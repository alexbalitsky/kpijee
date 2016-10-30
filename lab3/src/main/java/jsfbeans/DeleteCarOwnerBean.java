package jsfbeans;

import service.CarOwnerService;
import service.CarService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by alex on 30.10.16.
 */
@ManagedBean
@RequestScoped
public class DeleteCarOwnerBean {
    @EJB
    private CarOwnerService carOwnerService;

    public String delete(String id){
        carOwnerService.delete(id);
        return "getCarOwners?faces-redirect=true&includeViewParams=true";
    }
}
