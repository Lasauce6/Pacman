package graphics;

import game.Labyrinth;

import javax.swing.*;
import java.awt.*;

public class Client {
    private final int WIDTH = 1250;
    private final int HEIGHT = 725;
    private Labyrinth labyrinth;
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
        labyrinth = new Labyrinth();
        GamePanel gamePanel = new GamePanel(this);
        gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setContentPane(gamePanel);
        frame.repaint();
    }

    public void drawLabyrinth(Graphics g) {
        labyrinth.draw(g);
    }

    public void close() {
        frame.dispose();
    }

}
