package graphics;

import game.Labyrinth;

import javax.swing.*;
import java.awt.*;

public class Client {
    private static final int WIDTH = Labyrinth.WIDTH;
    private static final int HEIGHT = Labyrinth.HEIGHT + 24 * 2;
    private static final JFrame frame = new JFrame("PacMan");

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
        GamePanel gamePanel = new GamePanel(this);
        gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setContentPane(gamePanel);
        frame.repaint();
    }

    public void gameOver() {
        EndPanel gameOverPanel = new EndPanel(this, false);
        gameOverPanel.setBounds(0, 0, Labyrinth.WIDTH, Labyrinth.HEIGHT + 24 * 2);
        frame.setContentPane(gameOverPanel);
        frame.repaint();
    }

    public void victory() {
        EndPanel victoryPanel = new EndPanel(this, true);
        victoryPanel.setBounds(0, 0, Labyrinth.WIDTH, Labyrinth.HEIGHT + 24 * 2);
        frame.setContentPane(victoryPanel);
        frame.repaint();
    }



    public void close() {
        frame.dispose();
    }

}
