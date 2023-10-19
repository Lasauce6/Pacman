import java.awt.Color;

public class PacgommeBlue implements Pacgomme{
    private final int posX;
    private final int posY;
    
    public PacgommeBlue(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public int getPoints() {
        return 100;
    }

    public void effect(Pacman pacman, Ghost ghost, Labyrinth labyrinth) {
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

}
