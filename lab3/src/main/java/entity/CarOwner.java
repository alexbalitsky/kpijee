package entity;

import javax.persistence.*;

/**
 * Created by alex on 17.10.16.
 */

@Entity
@Table(name = "car_owners")
public class CarOwner {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.ALL)
    private SecureData secureData;

    public CarOwner() {
    }

    public CarOwner(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SecureData getSecureData() {
        return secureData;
    }

    public void setSecureData(SecureData secureData) {
        this.secureData = secureData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOwner carOwner = (CarOwner) o;

        if (getId() != null ? !getId().equals(carOwner.getId()) : carOwner.getId() != null) return false;
        if (getName() != null ? !getName().equals(carOwner.getName()) : carOwner.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(carOwner.getSurname()) : carOwner.getSurname() != null)
            return false;
        return getAddress() != null ? getAddress().equals(carOwner.getAddress()) : carOwner.getAddress() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
