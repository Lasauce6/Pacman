import java.awt.Color;
import java.util.ArrayList;


public class PacgommePurple implements Pacgomme {

	@Override
	public Color getColor() {
		return new Color(102, 0, 153);
	}

	@Override
	public int getPoints() {
		return 300;
	}

	
	public void effect(Pacman pacman) {
		pacman.setState(PacManState.InvisiblePacMan);
		//Jaune p�le
		pacman.setColor(new Color(255,255,153));
	}

	@Override
	public int getPosX() {
		return 15;
	}

	@Override
	public int getPosY() {
		return 33;
	}
	
}
