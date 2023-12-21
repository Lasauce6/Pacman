package game.utils;

import graphics.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class KeyHandler
 */
public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<>(); // La liste des touches

    /**
     * Class Key
     */
    public static class Key {
            public boolean isPressed; // Si la touche est pressée

            /**
             * Constructeur de Key
             */
            public Key() {
                keys.add(this);
            }

            /**
             * Méthode qui permet de changer si la touche est pressée ou non
             * @param pressed vrai si la touche est pressée, faux sinon
             */
            public void toggle(boolean pressed) {
                if (pressed != isPressed) {
                    isPressed = pressed;
                }
            }
        }

    public Key k_up = new Key(); // La touche pour aller en haut

    public Key k_down = new Key(); // La touche pour aller en bas

    public Key k_left = new Key(); // La touche pour aller à gauche

    public Key k_right = new Key(); // La touche pour aller à droite

    /**
     * Constructeur de KeyHandler
     * @param game le panel du jeu
     */
    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    /**
     * Méthode qui permet de changer si une touche est pressée ou non
     * @param e l'évènement de la touche
     * @param pressed vrai si la touche est pressée, faux sinon
     */
    public void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q) {
            k_left.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            k_right.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_Z) {
            k_up.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            k_down.toggle(pressed);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
