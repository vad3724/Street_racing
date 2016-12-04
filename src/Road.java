
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Пользователь on 04.11.2016.
 */
public class Road extends JPanel implements ActionListener,Runnable  { // Класс дороги
    Player p= new Player(); // Объект типа плеер
    Thread enemiesFactory = new Thread(this); // Создаём поток
    List<Enemy> enemies = new ArrayList<Enemy>(); // Создаём лист который будет хранить врагов
    Timer timer= new Timer(1,this); // Создаём объект типа таймер
    Image vzriv = new ImageIcon("res\\взрыв.png").getImage(); // Объект который хранит изображение взрыва

    Random rand =  new Random();
    int i = rand.nextInt(2)+1;
    BackgroundRoad backgroundRoad;


    public Image getroadImage() {
       if (i==1) backgroundRoad=new RoadStreet();
        else backgroundRoad=new RoadKub();
        return backgroundRoad.roadImage;
    }
    Image img = getroadImage();

    public Road() { // Конструктор

        timer.start(); // Запускаем таймер
        JOptionPane.showMessageDialog(null,"Нажмите на клавишу 'OK' или 'Enter'  для начала игры");
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true); // Делаем фрейм в фокусе
    }


    @Override
    public void run() { // Метод создания врагов
        while (true){ // Безконечный цикл
            Random rand = new Random(); // Генератор случайных чисел
            try {
                Thread.sleep(rand.nextInt(1800)); // Останавливаем создание врагов на некоторое время
                if (enemies.size()> 10) {// Если в коллекции больше 10 элеметов
                    enemies.clear(); // Очищаем коллекцию
                }
                    enemies.add(new Enemy(2000, rand.nextInt(600))); // Создаём врага


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private class MyKeyAdapter extends KeyAdapter{  // Обработчик событий клавиатуры
        public void keyPressed(KeyEvent e){ // Клавиша нажата
            p.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){  // Отпустили клавишу
            p.keyReleased(e);
        }
    }


    public void paint(Graphics g){
        g= (Graphics2D) g; // Приведение типов для зарисовки
        g.drawImage(img, p.layer1,0,null); // Зарисовываем фрейм первым изображением
        g.drawImage(img, p.layer2,0,null); // Зарисовываем фрейм вторым изображением
        g.drawImage(p.img,p.x,p.y,null); // Зарисовываем машину на дороге

        Iterator<Enemy> i = enemies.iterator(); // Объект для работы с коллекцией
        Random r1= new Random(); // Оъект рандом
        while (i.hasNext()){ // Пока не последний элемент
            Enemy e = i.next(); // Используем его


            if (e.x>=2400||e.x<=-2400){ //Если враг ушёл за границу экрана
               i.remove(); // Удаляем его
           }else { // Если нет
                e.move(); // Вызываем метод движения врага
                g.drawImage(e.getCarImage(), e.x, e.y, null); // Рисуем его


            }
        }

    } // прорисовка

    @Override
    public void actionPerformed(ActionEvent e) { // Обработчик событий таймера
        p.move(); // Запускаем движение
        repaint(); // Перересовываем фрейм
        testCollision(); // Метод проверкм на столкновение


    }

    private void testCollision(){
        Iterator<Enemy> ii = enemies.iterator(); // Объект для работы с коллекцией
        while (ii.hasNext()){  // Идём по коллекции
            Enemy e = ii.next(); // Берём элемент
            if (p.getRect().intersects(e.getRect())){ // Если прямоугольники пересекаются
                getGraphics().drawImage(vzriv,0,0,null); // Рисуем взрыв
                JOptionPane.showMessageDialog(null,"Вы проехали : "+p.s/500+" метров ");
                System.exit(1);

            }
        }
    } // проверка столкновения

}
