package game;

import game.elements.*;
import game.elements.superpacgum.*;
import game.utils.CollisionDetector;
import game.utils.KeyHandler;

import java.awt.*;
import java.util.ArrayList;

public class Labyrinth implements Observer {
    public static final String[] PLATEAU =
            {"----------------------------------------------------",
                    "-... ........--..........--..........--........ ...-",
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
                    "-........................--........................-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-...--.......--....--.... ...........--.......--...-",
                    "---.--.--.--------.--.--------.--.--------.--.--.---",
                    "---.--.--.--------.--.--------.--.--------.--.--.---",
                    "-......--..........--..........--..........--.. ...-",
                    "-.-.--.--.--------------------.--.-----------.----.-",
                    "-.. ...--..........................................-",
                    "----------------------------------------------------"};
    public static final int WIDTH_CASE = 24;
    public static final int NB_ROWS = PLATEAU.length;
    public static final int NB_COLUMNS = PLATEAU[0].length();

    public static final int HEIGHT = NB_ROWS * WIDTH_CASE;
    public static final int WIDTH = NB_COLUMNS * WIDTH_CASE;

    private boolean victory = false;
    private static boolean gameOver = false;

    private static final ArrayList<Element> listElements = new ArrayList<>();
    private static boolean firstInput = false;
    private static int score = 0;
    private static int lives = 3;

    public Labyrinth() {
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
        listElements.add(new BluePacGum(WIDTH_CASE, 47 * WIDTH_CASE, WIDTH_CASE));
        listElements.add(new GreenPacGum(WIDTH_CASE, 3 * WIDTH_CASE, 28 * WIDTH_CASE));
        listElements.add(new OrangePacGum(WIDTH_CASE, 4 * WIDTH_CASE, WIDTH_CASE));
        listElements.add(new PurplePacGum(WIDTH_CASE, 47 * WIDTH_CASE, 26 * WIDTH_CASE));
        // Ghosts
        listElements.add(new Ghost(WIDTH_CASE, 24 * WIDTH_CASE, 14 * WIDTH_CASE, 2,-2, 0,  Color.RED));
        listElements.add(new Ghost(WIDTH_CASE, 25 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.CYAN));
        listElements.add(new Ghost(WIDTH_CASE, 26 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, -2, Color.MAGENTA));
        listElements.add(new Ghost(WIDTH_CASE, 27 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 2, 0, Color.ORANGE));
        // Pacman
        Pacman pacman = new Pacman(25 * WIDTH_CASE, 23 * WIDTH_CASE, 2, WIDTH_CASE);
        listElements.add(pacman);
        CollisionDetector collisionDetector = new CollisionDetector(this);
        pacman.setCollisionDetector(collisionDetector);
        pacman.registerObserver(this);
    }

    public static void addScore(int i) {
        score = score + i;
    }

    public void gameOver() {
        lives--;
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
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
        boolean allPacGumsEaten = true;
        if (score >= 5000) {
            lives++;
            score -= 5000;
        }
        for (Element element : listElements) {
            if (!element.isDestroyed()) element.update();
            if (element instanceof PacGum || element instanceof SuperPacGum) {
                allPacGumsEaten = false;
            }
        }
        if (allPacGumsEaten) victory = true;
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

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public void updatePacGumEaten(PacGum pg) {
        pg.setEaten(true);
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        spg.setEaten(true);
    }

    @Override
    public void updateGhostCollision(Ghost gh) {
        if (gh.isVulnerable()) {
            gh.setEaten(true);
        } else {
            gameOver();
        }
    }

    public void resetGame() {
        // Ghosts
        listElements.set(listElements.size() - 5, new Ghost(WIDTH_CASE, 24 * WIDTH_CASE, 14 * WIDTH_CASE, 2,-2, 0,  Color.RED));
        listElements.set(listElements.size() - 4, new Ghost(WIDTH_CASE, 25 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.CYAN));
        listElements.set(listElements.size() - 3, new Ghost(WIDTH_CASE, 26 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, -2, Color.MAGENTA));
        listElements.set(listElements.size() - 2, new Ghost(WIDTH_CASE, 27 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 2, 0, Color.ORANGE));
        // Pacman
        Pacman pacman = new Pacman(25 * WIDTH_CASE, 23 * WIDTH_CASE, 2, WIDTH_CASE);
        listElements.set(listElements.size() - 1, pacman);
        CollisionDetector collisionDetector = new CollisionDetector(this);
        pacman.setCollisionDetector(collisionDetector);
        pacman.registerObserver(this);
        firstInput = false;
        gameOver = false;
    }

    public boolean isVictory() {
        return victory;
    }
}
