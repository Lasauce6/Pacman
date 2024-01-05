package game;

import game.elements.*;
import game.elements.superpacgum.*;
import game.utils.CollisionDetector;
import game.utils.KeyHandler;

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
                    "------.-- ---  --- --.------",
                    "      .   --    --   .      ",
                    "------.-- -------- --.------",
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
//                    "------.-- ---  --- --.------",
//                    "      .   --    --   .      ",
//                    "------.-- -------- --.------",
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
    private final ArrayList<Ghost> listGhosts = new ArrayList<>();
    private final ArrayList<SuperPacGum> listSuperPacGum = new ArrayList<>(); // La liste des super PacGum
    private static final ArrayList<Element> listElements = new ArrayList<>(); // La liste des éléments du labyrinthe
    private static boolean firstInput = false; // Si le joueur a fait son premier input
    private static int score = 0; // Le score du joueur
    private static int lives = 3; // Le nombre de vies du joueur

    /**
     * Constructeur de la classe Labyrinth
     */
    public Labyrinth() { // TODO: diviser en plusieurs méthodes
        // Le GamePanel
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
        // Murs pour la maison des fantômes
        listElements.add(new Wall(WIDTH_CASE, (int) (12.5 *  WIDTH_CASE), 12 * WIDTH_CASE));
        listElements.add(new Wall(WIDTH_CASE, (int) (14.5 * WIDTH_CASE), 12 * WIDTH_CASE));

        // Murs invisibles pour évider de sortir du labyrinthe et pour éviter les bugs
        listElements.add(new InvisibleWall(13 * WIDTH_CASE, WIDTH_CASE,  -WIDTH_CASE, -WIDTH_CASE));
        listElements.add(new InvisibleWall(2 * WIDTH_CASE, WIDTH_CASE,13 * WIDTH_CASE, -WIDTH_CASE));
        listElements.add(new InvisibleWall(13 * WIDTH_CASE, WIDTH_CASE, 16 * WIDTH_CASE, -WIDTH_CASE));
        listElements.add(new InvisibleWall(13 * WIDTH_CASE, WIDTH_CASE, -WIDTH_CASE, HEIGHT));
        listElements.add(new InvisibleWall(2 * WIDTH_CASE, WIDTH_CASE, 13 * WIDTH_CASE, HEIGHT));
        listElements.add(new InvisibleWall(13 * WIDTH_CASE, WIDTH_CASE, 16 * WIDTH_CASE, HEIGHT));
        listElements.add(new InvisibleWall(WIDTH_CASE, 14 * WIDTH_CASE, -WIDTH_CASE, -WIDTH_CASE));
        listElements.add(new InvisibleWall(WIDTH_CASE, 15 * WIDTH_CASE, -WIDTH_CASE, 14 * WIDTH_CASE));
        listElements.add(new InvisibleWall(WIDTH_CASE, 14 * WIDTH_CASE, WIDTH, -WIDTH_CASE));
        listElements.add(new InvisibleWall(WIDTH_CASE, 15 * WIDTH_CASE, WIDTH, 14 * WIDTH_CASE));


        // PacGum spécial
        listElements.add(new BluePacGum(WIDTH_CASE, 26 * WIDTH_CASE, 3 * WIDTH_CASE));
        GreenPacGum greenPacGum = new GreenPacGum(WIDTH_CASE, WIDTH_CASE, 21 * WIDTH_CASE);
        listElements.add(greenPacGum);
        greenPacGum.registerObserver(this);
        OrangePacGum orangePacGum = new OrangePacGum(WIDTH_CASE, WIDTH_CASE, 3 * WIDTH_CASE);
        listElements.add(orangePacGum);
        orangePacGum.registerObserver(this);
        PurplePacGum purplePacGum = new PurplePacGum(WIDTH_CASE, 26 * WIDTH_CASE, 21 * WIDTH_CASE);
        listElements.add(purplePacGum);
        purplePacGum.registerObserver(this);
        // Ghosts
        Ghost redGhost = new Ghost(WIDTH_CASE, 12 * WIDTH_CASE, 13 * WIDTH_CASE, 2, Color.RED);
        listElements.add(redGhost);
        listGhosts.add(redGhost);
        Ghost cyanGhost = new Ghost(WIDTH_CASE, 13 * WIDTH_CASE, 13 * WIDTH_CASE, 2, Color.CYAN);
        listElements.add(cyanGhost);
        listGhosts.add(cyanGhost);
        Ghost magentaGhost = new Ghost(WIDTH_CASE, 14 * WIDTH_CASE, 13 * WIDTH_CASE, 2, Color.MAGENTA);
        listElements.add(magentaGhost);
        listGhosts.add(magentaGhost);
        Ghost orangeGhost = new Ghost(WIDTH_CASE, 15 * WIDTH_CASE, 13 * WIDTH_CASE, 2, Color.ORANGE);
        listElements.add(orangeGhost);
        listGhosts.add(orangeGhost);
        // Pacman
        Pacman pacman = new Pacman((int) (13.5 * WIDTH_CASE), 21 * WIDTH_CASE, 2, WIDTH_CASE);
        listElements.add(pacman);

        CollisionDetector collisionDetector = new CollisionDetector(this);
        pacman.setCollisionDetector(collisionDetector);

        pacman.registerObserver(this);
        for (Ghost ghost : listGhosts) {
            pacman.registerObserver(ghost);
            orangePacGum.registerObserver(ghost);
            purplePacGum.registerObserver(ghost);
        }

        orangePacGum.registerObserver(pacman);
        purplePacGum.registerObserver(pacman);

        //TODO : Mettre les observers à la fin agencés de manière intelligente
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
     * Méthode permettant d'actualiser tous les éléments du labyrinthe et de vérifier si le joueur a gagné
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
        if (!gh.isEaten() && !gh.isVulnerable()) {
            gameOver();
        }
    }

    /**
     * Méthode permettant de rendre le Pacman invisible
     */
    @Override
    public void updatePacmanInvisible() {
        getPacman().switchInvisibleMode();
    }

    /**
     * Méthode permettant de rendre le Pacman invincible
     */
    @Override
    public void updatePacmanInvincible() {
        getPacman().switchInvincibleMode();
    }

    @Override
    public void timerPacmanInvisibleOver() {}

    @Override
    public void timerPacmanInvincibleOver() {}

    /**
     * Méthode permettant de mettre à jour le changement de labyrinthe
     */
    @Override
    public void updateLabyrinthChange() {
        listElements.set(12, new EmptyCase(WIDTH_CASE, 12 * WIDTH_CASE, WIDTH_CASE));
        listElements.set(15, new EmptyCase(WIDTH_CASE, 15 * WIDTH_CASE, WIDTH_CASE));
        listElements.set(listElements.size() - 5 - 4 - 12 - 13, new EmptyCase(WIDTH_CASE, 13 * WIDTH_CASE, 30 * WIDTH_CASE));
        listElements.set(listElements.size() - 5 - 4 - 12 - 16, new EmptyCase(WIDTH_CASE, 16 * WIDTH_CASE, 30 * WIDTH_CASE));
    }

    /**
     * Méthode permettant de réinitialiser le jeu
     */
    public void resetGame() {
        firstInput = false;
        // Ghosts
        for (Ghost ghost : listGhosts) {
            ghost.reset();
        }
        // Pacman
        getPacman().reset();
        gameOver = false;
    }

    /**
     * Méthode permettant de savoir si le joueur a gagné
     * @return Si le joueur a gagné
     */
    public boolean isVictory() {
        return victory;
    }

}
