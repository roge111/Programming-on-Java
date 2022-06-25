package test.lab.common.client;
//Проверка

public class Location implements Comparable<Location> {
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

    public Location(Location town) {
        this.x = town.x;
        this.y = town.y;
        this.z = town.z;
        this.name = town.name;
    }

    public Integer getX() {
        return x;
    }

    /**
     * Возвращает координату x
     *
     * @param x координата улицы
     */
    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    /**
     * Возвращает координату y
     *
     * @param y координата улицы
     */
    public void setY(Integer y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    /**
     * Возвращает координату z
     *
     * @param z координата улицы
     */

    public void setZ(Float z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return ("Координаты x, y, z:" + getX() + ", " + getY() + ", " + getZ()) + ". " + "Город: " + getName();
    }

    /**
     * Возвращает 0 если объекты равны, иначе -1 - объект первый меньше втоорого, иначе 1
     *
     * @param o объект, который будем сравнивать
     * @return
     */
    @Override
    public int compareTo(Location o) {
        int result;
        if (this.x == null) {
            result = o.x == null ? 0 : -1;
        } else {
            result = x.compareTo(o.x);
        }
        if (result != 0) {
            return result;
        }
        if (this.y == null) {
            result = o.y == null ? 0 : -1;
        } else {
            result = y.compareTo(o.y);
        }
        if (result != 0) {
            return result;
        }
        if (this.z == null) {
            return o.z == null ? 0 : -1;
        } else {
            return z.compareTo(o.z);
        }
    }
}
