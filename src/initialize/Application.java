package initialize;

import java.awt.EventQueue;
import javax.swing.JFrame;

import canvas.Board;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {
      // adds a new Board with is a JPanel

        add(new Board());

        setSize(500, 500);

        setTitle("Hopper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}