package game;

import game.elements.*;
import game.elements.superpacgum.*;
import game.utils.CollisionDetector;
import game.utils.KeyHandler;
import graphics.GamePanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe représentant le labyrinthe
 */
public class Labyrinth implements Observer {
    public static final String[] PLATEAU = // Le labyrinthe est représenté par un tableau de String
            {"----------------------------",
                    "-............--............-",
                    "-.----.-----.--.-----.----.-",
                    "- ----.-----.--.-----.---- -",
                    "-.----.-----.--.-----.----.-",
                    "-..........................-",
                    "-.----.--.--------.--.----.-",
                    "-.----.--.--------.--.----.-",
                    "-......--....--....--......-",
                    "------.----- -- -----.------",
                    "     -.----- -- -----.-     ",
                    "     -.--          --.-     ",
                    "     -.--          --.-     ",
                    "------.--          --.------",
                    "      ...            .      ",
                    "------.--          --.------",
                    "     -.--          --.-     ",
                    "     -.--          --.-     ",
                    "     -.-- -------- --.-     ",
                    "------.-- -------- --.------",
                    "-............--............-",
                    "-.----.-----.--.-----.----.-",
                    "-.----.-----.--.-----.----.-",
                    "- ..--.......  .......--.. -",
                    "---.--.--.--------.--.--.---",
                    "---.--.--.--------.--.--.---",
                    "-......--....--....--......-",
                    "-.----------.--.----------.-",
                    "-.----------.--.----------.-",
                    "-..........................-",
                    "----------------------------"};
//    public static final String[] PLATEAU2 = // Le labyrinthe lors du changement de labyrinthe
//            {"------------ -- ------------",
//                    "-............--............-",
//                    "-.----.-----.--.-----.----.-",
//                    "- ----.-----.--.-----.---- -",
//                    "-.----.-----.--.-----.----.-",
//                    "-..........................-",
//                    "-.----.--.--------.--.----.-",
//                    "-.----.--.--------.--.----.-",
//                    "-......--....--....--......-",
//                    "------.----- -- -----.------",
//                    "     -.----- -- -----.-     ",
//                    "     -.--          --.-     ",
//                    "     -.--          --.-     ",
//                    "------.--          --.------",
//                    "      ...            .      ",
//                    "------.--          --.------",
//                    "     -.--          --.-     ",
//                    "     -.--          --.-     ",
//                    "     -.-- -------- --.-     ",
//                    "------.-- -------- --.------",
//                    "-............--............-",
//                    "-.----.-----.--.-----.----.-",
//                    "-.----.-----.--.-----.----.-",
//                    "- ..--.......  .......--.. -",
//                    "---.--.--.--------.--.--.---",
//                    "---.--.--.--------.--.--.---",
//                    "-......--....--....--......-",
//                    "-.----------.--.----------.-",
//                    "-.----------.--.----------.-",
//                    "-..........................-",
//                    "------------ -- ------------"};
    public static final int WIDTH_CASE = 24; // La taille d'une case
    public static final int NB_ROWS = PLATEAU.length; // Le nombre de lignes
    public static final int NB_COLUMNS = PLATEAU[0].length(); // Le nombre de colonnes

    public static final int HEIGHT = NB_ROWS * WIDTH_CASE; // La hauteur du labyrinthe
    public static final int WIDTH = NB_COLUMNS * WIDTH_CASE; // La largeur du labyrinthe

    private boolean victory = false; // Si le joueur a gagné
    private static boolean gameOver = false; // Si le joueur a perdu

    private static final ArrayList<Element> listElements = new ArrayList<>(); // La liste des éléments du labyrinthe
    private static boolean firstInput = false; // Si le joueur a fait son premier input
    private static int score = 0; // Le score du joueur
    private static int lives = 3; // Le nombre de vies du joueur
    private final GamePanel gamePanel; // Le GamePanel

    /**
     * Constructeur de la classe Labyrinth
     * @param gamePanel Le GamePanel
     */
    public Labyrinth(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
        listElements.add(new BluePacGum(WIDTH_CASE, 26 * WIDTH_CASE, 3 * WIDTH_CASE));
        GreenPacGum greenPacGum = new GreenPacGum(WIDTH_CASE, WIDTH_CASE, 23 * WIDTH_CASE);
        listElements.add(greenPacGum);
        greenPacGum.registerObserver(this);
        greenPacGum.registerObserver(gamePanel);
        OrangePacGum orangePacGum = new OrangePacGum(WIDTH_CASE, WIDTH_CASE, 3 * WIDTH_CASE);
        listElements.add(orangePacGum);
        orangePacGum.registerObserver(this);
        orangePacGum.registerObserver(gamePanel);
        PurplePacGum purplePacGum = new PurplePacGum(WIDTH_CASE, 26 * WIDTH_CASE, 23 * WIDTH_CASE);
        listElements.add(purplePacGum);
        purplePacGum.registerObserver(this);
        purplePacGum.registerObserver(gamePanel);
        // Ghosts
        listElements.add(new Ghost(WIDTH_CASE, 12 * WIDTH_CASE, 14 * WIDTH_CASE, 2,0, -2,  Color.RED));
        listElements.add(new Ghost(WIDTH_CASE, 13 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.CYAN));
        listElements.add(new Ghost(WIDTH_CASE, 14 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, -2, Color.MAGENTA));
        listElements.add(new Ghost(WIDTH_CASE, 15 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.ORANGE));
        // Pacman
        Pacman pacman = new Pacman((int) (13.5 * WIDTH_CASE), 23 * WIDTH_CASE, 2, WIDTH_CASE);
        listElements.add(pacman);
        CollisionDetector collisionDetector = new CollisionDetector(this);
        pacman.setCollisionDetector(collisionDetector);
        pacman.registerObserver(this);
        pacman.registerObserver(gamePanel);
    }

    /**
     * Méthode permettant d'ajouter des points au score
     * @param i Le nombre de points à ajouter
     */
    public static void addScore(int i) {
        score = score + i;
    }

    /**
     * Méthode permettant de retirer une vie au joueur
     */
    public void gameOver() {
        lives--;
        gameOver = true;
    }

    /**
     * Méthode permettant de savoir si le joueur a perdu
     * @return Si le joueur a perdu
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Méthode permettant de récupérer la liste des éléments du labyrinthe
     * @return La liste des éléments du labyrinthe
     */
    public ArrayList<Element> getListElements() {
        return listElements;
    }

    /**
     * Méthode permettant de récupérer la liste des murs du labyrinthe
     * @return La liste des murs du labyrinthe
     */
    public static ArrayList<Wall> getWalls() {
        ArrayList<Wall> walls = new ArrayList<>();
        for (Element element : listElements) {
            if (element instanceof Wall) {
                walls.add((Wall) element);
            }
        }
        return walls;
    }

    /**
     * Méthode permettant d'actualiser les éléments du labyrinthe
     * @param g Le Graphics2D
     */
    public void render(Graphics2D g) {
        for (Element element : listElements) {
            if (!element.isDestroyed()) element.render(g);
        }
    }

    /**
     * Méthode permettant de récupérer le Pacman
     * @return Le Pacman
     */
    public static Pacman getPacman() {
        return (Pacman) listElements.getLast();
    }

    /**
     * Méthode permettant de récupérer les Ghosts
     * @return Les Ghosts
     */
    public static ArrayList<Ghost> getGhosts() {
        ArrayList<Ghost> ghosts = new ArrayList<>();
        for (Element element : listElements) {
            if (element instanceof Ghost) {
                ghosts.add((Ghost) element);
            }
        }
        return ghosts;
    }

    /**
     * Méthode permettant d'actualiser tout les éléments du labyrinthe et de vérifier si le joueur a gagné
     */
    public void update() {
        boolean allPacGumsEaten = true;
        if (score >= 5000) {
            lives++;
            score -= 5000;
        }
        for (Element element : listElements) {
            if (!element.isDestroyed()) {
                element.update();
                if (element instanceof PacGum || element instanceof SuperPacGum) allPacGumsEaten = false;
            }
        }
        if (allPacGumsEaten) victory = true;
    }

    /**
     * Méthode permettant de passer l'input au Pacman
     * @param keyHandler Le KeyHandler
     */
    public void input(KeyHandler keyHandler) {
        getPacman().input(keyHandler);
    }

    /**
     * Méthode permettant de changer le boolean firstInput
     * @param b Le nouveau boolean firstInput
     */
    public static void setFirstInput(boolean b) {
        firstInput = b;
    }

    /**
     * Méthode permettant de récupérer le boolean firstInput
     * @return Le boolean firstInput
     */
    public static boolean getFirstInput() {
        return firstInput;
    }

    /**
     * Méthode permettant de récupérer le score
     * @return Le score
     */
    public int getScore() {
        return score;
    }

    /**
     * Méthode permettant de récupérer le nombre de vies
     * @return Le nombre de vies
     */
    public int getLives() {
        return lives;
    }

    /**
     * Méthode permettant de mettre à jour le PacGum
     * @param pg Le PacGum
     */
    @Override
    public void updatePacGumEaten(PacGum pg) {
        pg.setEaten(true);
    }

    /**
     * Méthode permettant de mettre à jour le SuperPacGum
     * @param spg Le SuperPacGum
     */
    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        spg.setEaten(true);
    }

    /**
     * Méthode permettant de mettre à jour le Ghost
     * @param gh Le Ghost
     */
    @Override
    public void updateGhostCollision(Ghost gh) {
        if (gh.isVulnerable()) {
            gh.setEaten(true);
        } else {
            gameOver();
        }
    }

    /**
     * Méthode permettant de mettre rendre le Pacman invisible
     */
    @Override
    public void updatePacmanInvisible() {
        getPacman().setInvisible(true);
    }

    /**
     * Méthode permettant de mettre rendre le Pacman invincible
     */
    @Override
    public void updatePacmanInvincible() {
        getPacman().setInvincible(true);
        for (Ghost ghost : getGhosts()) {
            ghost.setVulnerable(true);
        }
    }

    /**
     * Méthode permettant de mettre à jour le changement de labyrinthe
     */
    @Override
    public void updateLabyrinthChange() {
        listElements.set(12, new EmptyCase(WIDTH_CASE, 12 * WIDTH_CASE, WIDTH_CASE));
        listElements.set(15, new EmptyCase(WIDTH_CASE, 15 * WIDTH_CASE, WIDTH_CASE));
        listElements.set(listElements.size() - 5 - 4 - 13, new EmptyCase(WIDTH_CASE, 13 * WIDTH_CASE, 30 * WIDTH_CASE));
        listElements.set(listElements.size() - 5 - 4 - 16, new EmptyCase(WIDTH_CASE, 16 * WIDTH_CASE, 30 * WIDTH_CASE));
    }

    /**
     * Méthode permettant de réinitialiser le jeu
     */
    public void resetGame() {
        // Ghosts
        listElements.set(listElements.size() - 5, new Ghost(WIDTH_CASE, 12 * WIDTH_CASE, 14 * WIDTH_CASE, 2,0, -2,  Color.RED));
        listElements.set(listElements.size() - 4, new Ghost(WIDTH_CASE, 13 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.CYAN));
        listElements.set(listElements.size() - 3, new Ghost(WIDTH_CASE, 14 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, -2, Color.MAGENTA));
        listElements.set(listElements.size() - 2, new Ghost(WIDTH_CASE, 15 * WIDTH_CASE, 14 * WIDTH_CASE, 2, 0, 2, Color.ORANGE));
        // Pacman
        Pacman pacman = new Pacman((int) (13.5 * WIDTH_CASE), 23 * WIDTH_CASE, 2, WIDTH_CASE);
        listElements.set(listElements.size() - 1, pacman);
        CollisionDetector collisionDetector = new CollisionDetector(this);
        pacman.setCollisionDetector(collisionDetector);
        pacman.registerObserver(this);
        pacman.registerObserver(gamePanel);
        gameOver = false;
        firstInput = false;
    }

    /**
     * Méthode permettant de savoir si le joueur a gagné
     * @return Si le joueur a gagné
     */
    public boolean isVictory() {
        return victory;
    }

}
