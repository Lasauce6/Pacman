package graphics;

import game.Labyrinth;
import game.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

/**
 * Class GamePanel
 */
public class GamePanel extends JPanel implements Runnable {
    private final Client client; // Le client
    private static Thread thread; // Le thread
    private static boolean running = false; // true si le jeu est en cours, false sinon
    private BufferedImage img; // L'image
    private Graphics2D graphics2D; // Le graphics
    private KeyHandler keyHandler; // Le keyHandler
    private static Labyrinth labyrinth; // Le labyrinthe

    /**
     * Constructeur
     * @param client le client
     */
    public GamePanel(Client client) {
        super();
        setLayout(null);
        setOpaque(true);
        setFocusable(true);
        requestFocus();
        this.client = client;
    }

    /**
     * Méthode qui fait un rendu de la fenêtre
     */
    public void render() {
        if (graphics2D != null) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
            labyrinth.render(graphics2D);
            Font font = new Font("Arial", Font.BOLD, 20);
            graphics2D.setFont(font);
            graphics2D.setColor(Color.YELLOW);
            graphics2D.drawString("Score : " + labyrinth.getScore(), 50, getHeight() - 19);
            graphics2D.drawString("Lives : " + labyrinth.getLives(), getWidth() - 150, getHeight() - 19);
        }
    }

    /**
     * Méthode qui dessine l'image pour chaque fps
     */
    public void draw() {
        Graphics g = this.getGraphics();
        if (g != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            g.dispose();
        }
    }

    /**
     * Méthode qui permet de lancer le thread du jeu
     */
    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null)  {
            thread = new Thread(this, "GameThread");
            init();
            thread.start();
        }
    }

    /**
     * Méthode qui initialise le jeu
     */
    public void init() {
        running = true;
        img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) img.getGraphics();
        keyHandler = new KeyHandler(this);
        KeyEventDispatcher dispatcher = e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keyHandler.keyPressed(e);
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                keyHandler.keyReleased(e);
            }
            return false;
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
        labyrinth = new Labyrinth();
    }

    /**
     * Méthode qui met à jour le jeu
     */
    public void update() {
        labyrinth.update();
    }

    /**
     * Méthode qui gère les entrées clavier
     * @param keyHandler le keyHandler
     */
    public void input(KeyHandler keyHandler) {
        labyrinth.input(keyHandler);
    }

    /**
     * Le thread du jeu
     */
    @Override
    public void run() {
        //Pour faire en sorte que le jeu tourne à 60FPS (tutoriel consulté : https://www.youtube.com/watch?v=LhUN3EKZiio)
        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time before update

        final int MUBR = 5; // Must update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60.0;
        final double TTBR = 1000000000 / TARGET_FPS; // Total time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        int invisibleTime = 10;
        int invincibleTime = 10;

        while (running) {
            if (labyrinth.isGameOver()) {
                if (labyrinth.getLives() > 0) labyrinth.resetGame();
                else {
                    running = false;
                    client.gameOver();
                }
            } else if (labyrinth.isVictory()) {
                running = false;
                client.victory();
            } else {
                double now = System.nanoTime();
                int updateCount = 0;
                while ((now - lastUpdateTime) > TBU && (updateCount < MUBR)) {
                    input(keyHandler);
                    update();
                    lastUpdateTime += TBU;
                    updateCount++;
                }

                if (now - lastUpdateTime > TBU) {
                    lastUpdateTime = now - TBU;
                }

                render();
                draw();
                lastRenderTime = now;
                frameCount++;

                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime) {
                    if (frameCount != oldFrameCount) {
                        System.out.println("FPS : " + frameCount);
                        oldFrameCount = frameCount;
                    }
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                while ((now - lastRenderTime < TTBR) && (now - lastUpdateTime < TBU)) {
                    Thread.yield();

                    try {
                        sleep(1);
                    } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                    }

                    now = System.nanoTime();
                }
            }

        }
    }
}
