import java.awt.Color;

public class PacgommeGreen implements Pacgomme {

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public int getPoints() {
		return 1000;
	}

	
	public void effect(String[][] map) {
		map = Game.Mapeffect();
	}

	@Override
	public int getPosX() {
		return 19;
	}

	@Override
	public int getPosY() {
		return 1;
	}
	
	

}
