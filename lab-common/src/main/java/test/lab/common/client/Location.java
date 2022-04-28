package test.lab.common.client;


public class Location {
    private Integer x; //Поле не может быть null
    private Integer y; //Поле не может быть null
    private Float z; //Поле не может быть null
    private String name;

    public Location() {

    }


    public Location(Integer x, Integer y, Float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;

    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public String toString() {
        return ("Координаты x, y, z:" + getX() + ", " + getY() + ", " + getZ());
    }
}
