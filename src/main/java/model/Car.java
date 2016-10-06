package model;

/**
 * Created by obalitskyi on 10/3/16.
 */
public class Car {
    private Long id;
    private String brand;
    private Integer number;
    private String colour;
    private Integer price;

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
}
