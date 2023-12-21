package graphics;

import game.Labyrinth;

import javax.swing.*;
import java.awt.*;

/**
 * Class Client
 */
public class Client {
    public static final int WIDTH = Labyrinth.WIDTH; // Largeur de la fenêtre
    public static final int HEIGHT = Labyrinth.HEIGHT + 24 * 2; // Hauteur de la fenêtre
    private static final JFrame frame = new JFrame("PacMan"); // Fenêtre

    /**
     * Constructeur
     */
    public Client() {
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Menu principal
     */
    public void menu() {
        MenuPanel menuPanel = new MenuPanel(this);
        menuPanel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setContentPane(menuPanel);
        frame.repaint();
    }

    /**
     * Fenêtre de jeu
     */
    public void game() {
        GamePanel gamePanel = new GamePanel(this);
        gamePanel.setBounds(0, 0, WIDTH, HEIGHT);
        frame.setContentPane(gamePanel);
        frame.repaint();
    }

    /**
     * Fenêtre de défaite
     */
    public void gameOver() {
        EndPanel gameOverPanel = new EndPanel(this, false);
        gameOverPanel.setBounds(0, 0, Labyrinth.WIDTH, Labyrinth.HEIGHT + 24 * 2);
        frame.setContentPane(gameOverPanel);
        frame.repaint();
    }

    /**
     * Fenêtre de victoire
     */
    public void victory() {
        EndPanel victoryPanel = new EndPanel(this, true);
        victoryPanel.setBounds(0, 0, Labyrinth.WIDTH, Labyrinth.HEIGHT + 24 * 2);
        frame.setContentPane(victoryPanel);
        frame.repaint();
    }

    /**
     * Ferme la fenêtre
     */
    public void close() {
        frame.dispose();
    }

}
