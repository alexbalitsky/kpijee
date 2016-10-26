package entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by obalitskyi on 10/3/16.
 */

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "number")
    private Integer number;
    @Column(name = "colour")
    private String colour;
    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_owner_id")
    private CarOwner carOwner;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "cars", cascade = CascadeType.ALL)
    private Set<Driver> drivers;

    public Car() {
    }

    public Car(String brand, Integer number, String colour, Integer price) {
        this.brand = brand;
        this.number = number;
        this.colour = colour;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        for (Driver driver : drivers){
            driver.addCar(this);
        }
        this.drivers = drivers;
    }

    public void addDriver(Driver driver){
        this.drivers.add(driver);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (getId() != null ? !getId().equals(car.getId()) : car.getId() != null) return false;
        if (getBrand() != null ? !getBrand().equals(car.getBrand()) : car.getBrand() != null) return false;
        if (getNumber() != null ? !getNumber().equals(car.getNumber()) : car.getNumber() != null) return false;
        if (getColour() != null ? !getColour().equals(car.getColour()) : car.getColour() != null) return false;
        return getPrice() != null ? getPrice().equals(car.getPrice()) : car.getPrice() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getColour() != null ? getColour().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return brand + " " + number + " " + colour + " " + price;
    }
}
