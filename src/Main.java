import javax.swing.*;
import java.io.IOException;

public class Main extends JPanel{

    public static void main (String[] args) throws IOException {
        JFrame f = new JFrame("Street racing");  // Создаём объект типа фрейм
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Прри закрытии фрейма должна остановится программа
        f.setSize(1100,600); // Задаём размер фрейма
        f.setLocationRelativeTo(null); // Ставим фрейм в середине экрана
        f.add(new Road()); // Добавляем дорогу и события на ней
        f.setVisible(true); // Делаем фрейм видимым


    }
}