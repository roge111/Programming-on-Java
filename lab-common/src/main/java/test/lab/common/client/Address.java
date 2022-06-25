package test.lab.common.client;

import java.util.Objects;

public class Address implements Comparable<Address> {
    private String street;
    private Location town;

    public Address() {

    }

    public Address(String street, Location location) {
        this.street = street;
        this.town = location;
    }

    public Address(Address postalAddress) {
        this.street = postalAddress.street;
        this.town = new Location(postalAddress.town);
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

    @Override
    public int compareTo(Address o) {
        int result;
        if (this.street == null) {
            result = o.street == null ? 0 : -1;
        } else {
            result = street.compareTo(o.street);
        }
        if (result != 0) {
            return result;
        }
        if (this.town == null) {
            return o.town == null ? 0 : -1;
        } else {
            return town.compareTo(o.town);
        }
    }
}
