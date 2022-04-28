package test.lab.common.client;

import java.util.Objects;

public class Address {
    private String street;
    private test.lab.common.client.Location town;

    public Address() {

    }

    public Address(String street, Location location) {
        this.street = street;
        this.town = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Location getTown() {
        return town;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address adress = (Address) o;
        return street.equals(adress.street) && town.equals(adress.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, town);
    }
}
