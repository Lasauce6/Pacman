import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Key implements KeyListener {
	
	Game game;
	Labyrinth labyrinth;
	
	public Key(Game game, Labyrinth labyrinth) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.labyrinth = labyrinth;		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
//		switch(e.getKeyCode()) {
//		case KeyEvent.VK_UP : System.out.println("UP"); 
//			break;
//		case KeyEvent.VK_DOWN : System.out.println("DOWN");
//			break;
//		case KeyEvent.VK_RIGHT : System.out.println("RIGHT");
//			break;
//		case KeyEvent.VK_LEFT : System.out.println("LEFT");
//			break;
//		default: break;
//       }
	
		int posX = game.getPacman().getPosX();
		int posy = game.getPacman().getPosY();
		String[][] map = game.getMap();
		Pacman pacman = game.getPacman();
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			if(game.move(posX-1, posy, Direction.UP)) {
//				labyrinth.repaint();
			}	
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN ){
			if(game.move(posX+1, posy, Direction.DOWN)) {
//				labyrinth.repaint();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ){
			if(game.move(posX, posy+1, Direction.RIGHT)) {
//				labyrinth.repaint();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(game.move(posX, posy-1, Direction.LEFT)) {
//				labyrinth.repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
