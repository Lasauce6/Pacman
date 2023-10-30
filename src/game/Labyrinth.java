package game;

import game.elements.*;
import game.utils.CollisionDetector;
import game.utils.KeyHandler;

import java.awt.*;
import java.util.ArrayList;

public class Labyrinth {
    public static final String[] PLATEAU =
            {"----------------------------------------------------",
                    "-............--..........--..........--............-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-..................................................-",
                    "-.----.--.--------.--.--------.--.--------.--.----.-",
                    "-.----.--.--------.--.--------.--.--------.--.----.-",
                    "-......--....--....--....--....--....--....--......-",
                    "------.--.--.--.--------.--.--------.--.--.--.------",
                    "     -.--.--.--.--------.--.--------.--.--.--.-     ",
                    "     -.--.--.......--          --.......--.--.-     ",
                    "     -.--.--.-----.--          --.-----.--.--.-     ",
                    "------.--.--.-----.--          --.-----.--.--.------",
                    "      .......--......          ......--.......      ",
                    "------.--.-----.-----          -----.-----.--.------",
                    "     -.--.-----.-----          -----.-----.--.-     ",
                    "     -.--..........--          --..........--.-     ",
                    "     -.--.--------.--.--------.--.--------.--.-     ",
                    "------.--.--------.--.--------.--.--------.--.------",
                    "-............--..........--..........--............-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-...--................... ....................--...-",
                    "---.--.--.--------.--.--------.--.--------.--.--.---",
                    "---.--.--.--------.--.--------.--.--------.--.--.---",
                    "-......--....--....--....--....--....--....--......-",
                    "-.-.--.--.--------.--.--------.--.--------.--.--.-.-",
                    "-..................................................-",
                    "----------------------------------------------------"};
    public static final int WIDTH_CASE = 24;
    public static final int NB_ROWS = PLATEAU.length;
    public static final int NB_COLUMNS = PLATEAU[0].length();

    public static final int HEIGHT = NB_ROWS * WIDTH_CASE;
    public static final int WIDTH = NB_COLUMNS * WIDTH_CASE;

    private boolean victory = false;

    private static final ArrayList<Element> listElements = new ArrayList<>();
    private static boolean firstInput = false;

    public Labyrinth() {
        CollisionDetector collisionDetector = new CollisionDetector(this);

        for (int i = 0; i < NB_ROWS; i++) {
            for (int j = 0; j < NB_COLUMNS; j++) {
                switch (PLATEAU[i].charAt(j)) {
                    // Case vide
                    case ' ':
                        listElements.add(new EmptyCase(WIDTH_CASE, j * WIDTH_CASE, i * WIDTH_CASE));
                        break;
                    // PacGum
                    case '.':
                        listElements.add(new PacGum(WIDTH_CASE, j * WIDTH_CASE, i * WIDTH_CASE));
                        break;
                    // Mur
                    case '-':
                        listElements.add(new Wall(WIDTH_CASE, j * WIDTH_CASE, i * WIDTH_CASE));
                        break;
                }
            }
        }
        // PacGum spécial
        listElements.add(new SuperPacGum(WIDTH_CASE, 11 * WIDTH_CASE, 11 * WIDTH_CASE));
        listElements.add(new SuperPacGum(WIDTH_CASE, 11 * WIDTH_CASE, 11 * WIDTH_CASE));
        listElements.add(new SuperPacGum(WIDTH_CASE, 11 * WIDTH_CASE, 11 * WIDTH_CASE));
        // Ghosts
        listElements.add(new Ghost(WIDTH_CASE, 24 * WIDTH_CASE, 14 * WIDTH_CASE, 2,-2, 0,  Color.RED));
        listElements.add(new Ghost(WIDTH_CASE, 25 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.CYAN));
        listElements.add(new Ghost(WIDTH_CASE, 26 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, -2, Color.MAGENTA));
        listElements.add(new Ghost(WIDTH_CASE, 27 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 2, 0, Color.ORANGE));
        // Pacman
        Pacman pacman = new Pacman(25 * WIDTH_CASE, 23 * WIDTH_CASE, 2, WIDTH_CASE);
        listElements.add(pacman);
        pacman.setCollisionDetector(collisionDetector);
    }

    public ArrayList<Element> getListElements() {
        return listElements;
    }

    public static ArrayList<Wall> getWalls() {
        ArrayList<Wall> walls = new ArrayList<>();
        for (Element element : listElements) {
            if (element instanceof Wall) {
                walls.add((Wall) element);
            }
        }
        return walls;
    }

    public void render(Graphics2D g) {
        for (Element element : listElements) {
            if (!element.isDestroyed()) element.render(g);
        }
    }

    private Pacman getPacman() {
        return (Pacman) listElements.get(listElements.size() - 1);
    }

    public void update() {
        for (Element element : listElements) {
            if (!element.isDestroyed()) element.update();
        }
    }

    public void input(KeyHandler keyHandler) {
        getPacman().input(keyHandler);
    }

    public static void setFirstInput(boolean b) {
        firstInput = b;
    }

    public static boolean getFirstInput() {
        return firstInput;
    }

}
