import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Пользователь on 04.11.2016.
 */
public class Player implements KeyListener {  // Класс игрока

    PCar pCar=new PCarNorm();

    public Image getPlayerImage() {

        return pCar.PlayerImage;
    }


    Image img =getPlayerImage(); // Текущее изображение машины

    public Rectangle getRect() { // Конструктор
        return new Rectangle(x,y,200,100);// Возвращаем прямоугольник размером с игрока
    }

    public static final int MAX_V= 70; // Максимальная скорость машинки
    public static final int MAX_TOP=-55; // Максимальная высота на которую может подняться машинка
    public static final int MAX_BOTTOM=420; // Максимальная высота на которою может опуститься машинка
    int vp=10; // Начальная скорость
    int dv =0; // Ускорение
    int s=0; // Пройденый путь
    int dy=0;

    int x=5; //  Координата машины по оси х
    int y=100;  // Координаты машины по оси у
    int layer1=0; // Координата первого слоя
    int layer2=1200; // координаты второго слоя

    public void move(){ // Метод который справляет машиной
        s+=vp; // Увеличиваем пройденый путь
        vp+=dv; // Увеличиваем скорость
        if(vp<=0) vp=0; // Проверка на то чтобы скорость не была отрицательной
        if (vp>=MAX_V) vp=MAX_V; // Проверка на то чтобы не привышалась максимальная скорость
        y+=dy; // Изменяем координаты машинки по оси у
        if (y<=MAX_TOP) y=MAX_TOP; // Проверка на то чтобы машинка не поднималась выше предела
        if (y>=MAX_BOTTOM) y=MAX_BOTTOM; // Проверка на то чтобы машинка не опускалась ниже предела
        if (layer2-vp<=0){ // если координата второго слоя меньше либо равна 0 , возвращаем начальные значения
            layer1=0;
            layer2=1200;
        }
        else {  // В обратном случае продолжаем уменьшать координаты
            layer1 -= vp;
            layer2 -= vp;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {  // Обработчик событий нажатия на клавишу
        int key =e.getKeyCode(); // Хранит код нажатой кнопки
        if(key==KeyEvent.VK_RIGHT){  // Если нажата клавиша вправо
            dv=1; // Увеличиваем ускорение
        }
        if(key==KeyEvent.VK_LEFT){ // Если нажата кнопка влево
            dv= -1; // Уменьшаем ускорение
        }

        if (key==KeyEvent.VK_DOWN){ // Если нажата кнопка вниз
            pCar = new PCarRight();
            img=getPlayerImage(); // Изображение машины влево
            dy=5; // Опускаем машинку
        }
        if(key==KeyEvent.VK_UP){ // Если нажата кнопка вверх
            dy=-5; // Поднимаем машинку
            pCar = new PCarLeft();
            img=getPlayerImage(); //Изображение машины влево
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {  // Обработчик события когда отпустили кнопку
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_LEFT) {  // Если отпустили клавишу вправо или влево
            dv=0; // Обнуляем ускорение

        }

        if (key==KeyEvent.VK_DOWN || key==KeyEvent.VK_UP){ // Если отпустли клавишу вверх или вниз
            dy=0; // Не изменяем координаты машинки по оси e
            pCar=new PCarNorm();
            img=getPlayerImage(); // Изображение машины вперёд
        }
    }
}
