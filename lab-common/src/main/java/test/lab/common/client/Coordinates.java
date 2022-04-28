package test.lab.common.client;

public class Coordinates {
    private float x;
    private long y;

    public Coordinates() {

    }

    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
