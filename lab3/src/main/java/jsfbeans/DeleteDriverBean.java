package jsfbeans;

import service.CarOwnerService;
import service.DriverService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by alex on 30.10.16.
 */
@ManagedBean
@RequestScoped
public class DeleteDriverBean {
    @EJB
    private DriverService driverService;

    public String delete(String id){
        driverService.delete(id);
        return "getDrivers?faces-redirect=true&includeViewParams=true";
    }
}
