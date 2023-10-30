package graphics;

import game.Labyrinth;

import javax.swing.*;
import java.awt.*;

public class Client {
    private final int WIDTH = Labyrinth.WIDTH;
    private final int HEIGHT = Labyrinth.HEIGHT;
    private final JFrame frame = new JFrame("PacMan");

    public Client() {
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.setVisible(true);
    }

    public void menu() {
        MenuPanel menuPanel = new MenuPanel(this);
        menuPanel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setContentPane(menuPanel);
        frame.repaint();
    }

    public void game() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setContentPane(gamePanel);
        frame.repaint();
    }

    public void close() {
        frame.dispose();
    }

}
