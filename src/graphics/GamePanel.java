package graphics;

import game.Labyrinth;
import game.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private Thread thread;
    private boolean running = false;
    private BufferedImage img;
    private Graphics2D graphics2D;
    private KeyHandler keyHandler;
    private Labyrinth labyrinth;
    public GamePanel() {
        super();
        setLayout(null);
        setOpaque(true);
        setFocusable(true);
        requestFocus();
    }

    public void render() {
        if (graphics2D != null) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
            labyrinth.render(graphics2D);
        }
    }

    public void draw() {
        Graphics g = this.getGraphics();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null)  {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

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

    public void update() {
        labyrinth.update();
    }

    public void input(KeyHandler keyHandler) {
        labyrinth.input(keyHandler);
    }

    @Override
    public void run() {
        init();

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

        while (running) {
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
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }

                now = System.nanoTime();
            }

        }
    }

}
