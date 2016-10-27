package entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by alex on 17.10.16.
 */

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "drivers_cars", joinColumns = {
            @JoinColumn(name = "driver_id") },
            inverseJoinColumns = { @JoinColumn(name = "car_id") })
    private Set<Car> cars;

    public Driver() {
    }

    public Driver(String name, String surname, Integer salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (getId() != null ? !getId().equals(driver.getId()) : driver.getId() != null) return false;
        if (getName() != null ? !getName().equals(driver.getName()) : driver.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(driver.getSurname()) : driver.getSurname() != null)
            return false;
        return getSalary() != null ? getSalary().equals(driver.getSalary()) : driver.getSalary() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getSalary() != null ? getSalary().hashCode() : 0);
        return result;
    }
}
