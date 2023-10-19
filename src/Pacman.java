import java.awt.Color;

public class Pacman {
	private int life;
	private PacManState state;
	private int points;
	private Direction direction;
	private Color color;
	int posX;
	int posY;
	
	public Pacman() {
		this.life = 3;
		this.state = PacManState.NormalPacMan;
		this.points = 0;
		this.direction = Direction.UP;
		this.color = Color.YELLOW;
		this.posX = 18;
		this.posY = 17;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public PacManState getState() {
		return state;
	}

	public void setState(PacManState state) {
		this.state = state;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void addPoints(int points) {
		this.points = this.points + points;
	}
	
	public boolean isAlive() {
		if(life <= 0)
			return false;
		return true;
	}
	
	public void incLife() {
		this.life++;
	}
}
