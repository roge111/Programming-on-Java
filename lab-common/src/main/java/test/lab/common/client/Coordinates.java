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

    /**
     * Возвращает x производства
     *
     * @param x объект, который будем сравнивать
     * @return
     */
    public void setX(float x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    /**
     * Возвращает y производства
     *
     * @param y объект, который будем сравнивать
     * @return
     */
    public void setY(long y) {
        this.y = y;
    }
}
