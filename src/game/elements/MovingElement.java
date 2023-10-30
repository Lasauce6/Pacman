package game.elements;

import game.Labyrinth;
import game.utils.Direction;

import java.awt.*;

public abstract class MovingElement extends Element {
    protected int speed;
    protected int xVel;
    protected int yVel;
    protected Direction direction;
    public MovingElement(int size, int xPos, int yPos, int speed)  {
        super(size, xPos, yPos);
        this.speed = speed;
    }

    @Override
    public void update()  {
       updatePosition();
    }

    public void updatePosition() {
        if (!(xVel == 0 && yVel == 0)) {
            xPos += xVel;
            yPos += yVel;

            // On change la direction
            updateDirection();
        }

        // Si on sort de l'écran, on revient de l'autre côté
        if (xPos > Labyrinth.WIDTH) {
            xPos = -size + speed;
        }
        if (xPos < -size + speed) {
            xPos = Labyrinth.WIDTH;
        }
        if (yPos > Labyrinth.HEIGHT) {
            yPos = -size + speed;
        }
        if (yPos < -size + speed) {
            yPos = Labyrinth.HEIGHT;
        }
    }

    public boolean onGameplayWindow() {
        return xPos >= 0 || xPos <= Labyrinth.WIDTH || yPos >= 0 || yPos <= Labyrinth.HEIGHT;
    }

    public void updateDirection() {
        if (xVel > 0) {
            direction = Direction.RIGHT;
        } else if (xVel < 0) {
            direction = Direction.LEFT;
        } else if (yVel < 0) {
            direction = Direction.UP;
        } else {
            direction = Direction.DOWN;
        }
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
    }
}

