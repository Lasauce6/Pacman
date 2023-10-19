import java.awt.Color;
import java.util.ArrayList;

public class PacgommeOrange implements Pacgomme{

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public int getPoints() {
		return 500;
	}

	
	public void effect(Pacman pacman, ArrayList<Ghost> ghosts) {
		pacman.setState(PacManState.SuperPacMan);
		pacman.setColor(Color.ORANGE);
		for (Ghost ghost : ghosts) {
			ghost.setColor(Color.BLUE);
		}
	}

	@Override
	public int getPosX() {
		return 1;
	}

	@Override
	public int getPosY() {
		return 5;
	}

}
