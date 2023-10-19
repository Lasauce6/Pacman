package graphics;

import javax.swing.*;
import java.awt.*;

public class Client {
    private final JFrame frame = new JFrame("PacMan");

    public Client() {
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1155, 730));
        frame.pack();
        frame.setVisible(true);
    }

    public void menu() {
        MenuPanel menuPanel = new MenuPanel(this);
        menuPanel.setBounds(0, 0, 1155, 730);
        frame.setContentPane(menuPanel);
        frame.repaint();
    }

    public void game() {
        GamePanel gamePanel = new GamePanel(this);
        gamePanel.setBounds(0, 0, 1155, 730);
        frame.setContentPane(gamePanel);
        frame.repaint();
    }

    public void close() {
        frame.dispose();
    }

}
