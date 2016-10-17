package dao;

import entity.Driver;
import interceptor.TxInterceptorBinding;

/**
 * Created by alex on 17.10.16.
 */

@TxInterceptorBinding
public class DriverDAO extends AbstractDAO<Driver>{
    public DriverDAO() {
        super(Driver.class);
    }

    //TODO
}
