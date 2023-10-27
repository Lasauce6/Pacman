package game;

import java.awt.*;

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
                    "     -.--.--.......-- @        --.......--.--.-     ",
                    "     -.--.--.-----.--      @   --.-----.--.--.-     ",
                    "------.--.--.-----.--          --.-----.--.--.------",
                    "-     .......--......     @    ......--.......     -",
                    "------.--.-----.-----          -----.-----.--.------",
                    "     -.--.-----.----- @        -----.-----.--.-     ",
                    "     -.--..........--          --..........--.-     ",
                    "     -.--.--------.--.--------.--.--------.--.-     ",
                    "------.--.--------.--.--------.--.--------.--.------",
                    "-............--..........--..........--............-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-.----.-----.--.--------.--.--------.--.-----.----.-",
                    "-...--...................#....................--...-",
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
    public static final int NB_CASES = NB_ROWS * NB_COLUMNS;

    public static final int NB_GHOST = 4;
    public static final int NB_ELEMENTS = NB_CASES;

    private Element[] listElements;

    public Labyrinth () {
        listElements = new Element[NB_ELEMENTS];
        for (int i = 0; i < NB_ROWS; i++) {
            for (int j = 0; j < NB_COLUMNS; j++) {
                switch (PLATEAU[i].charAt(j)) {
                    // Case vide
                    case ' ':
                        listElements[i * NB_COLUMNS + j] = new Case(j * WIDTH_CASE, i * WIDTH_CASE, 0, WIDTH_CASE, Id.EMPTY, Color.BLACK);
                        break;
                    // Case avec point
                    case '.':
                        listElements[i * NB_COLUMNS + j] = new Case(j * WIDTH_CASE, i * WIDTH_CASE, 0, WIDTH_CASE, Id.POINT, Color.WHITE);
                        break;
                    // Mur
                    case '-':
                        listElements[i * NB_COLUMNS + j] = new Case(j * WIDTH_CASE, i * WIDTH_CASE, 0, WIDTH_CASE, Id.WALL, Color.BLUE);
                        break;
                    // Pacman
                    case '#':
                        listElements[i * NB_COLUMNS + j] = new Pacman(j * WIDTH_CASE, i * WIDTH_CASE, 0, WIDTH_CASE, Color.YELLOW);
                        break;
                    // Fantôme
                    case '@':
                        listElements[i * NB_COLUMNS + j] = new Ghost(j * WIDTH_CASE, i * WIDTH_CASE, 0, WIDTH_CASE, Color.RED);
                        break;
                    // Pacgomme
                    case '!':
                        listElements[i * NB_COLUMNS + j] = new Pacgomme(j * WIDTH_CASE, i * WIDTH_CASE, 0, WIDTH_CASE, Color.WHITE);
                        break;
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (Element element : listElements) {
            if (element != null) element.draw(g);
        }
    }


}
