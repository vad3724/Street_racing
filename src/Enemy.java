import java.awt.*;
import java.util.Random;

/**
 * Created by Пользователь on 05.11.2016.
 */
public class Enemy { // Класс врага
    int x; // Координата по оси х
    int y; // Координата по оси у
    private int v; // Скорость машины врага
    Car car;

    public Rectangle getRect() { // Конструктор

        return new Rectangle(x, y, 120, 20);// Возвращаем прямоугольник размером с врага
    }

    public Image getCarImage() {
        return car.carImage;
    }


    public int getSpeed() {
        return car.carSpeed;
    }

    public Enemy(int x, int y) { // Конструктор класса
        this.x = x;
        this.y = y;
        Random rand = new Random();
        int i = rand.nextInt(3) + 1;
        if (i == 1) {
            car = new Car_blue();
            v=getSpeed();
        }
        if (i == 2) {
            car = new Car_red();
            v=getSpeed();
        }
        if (i == 3) {
            car = new CarWhite();
            v=getSpeed();
        }

    }

    public void move() { // Метод движения врага
        x = x - v;
    }

}
