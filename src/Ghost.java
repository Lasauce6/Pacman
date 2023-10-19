import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultBoundedRangeModel;


public class Ghost {
	
     private int speed;
     private Color color;
     private Direction direction; //la direction ici est aleatoir du coup on va utliser des valeur et random 
     private int posX;
     private int posY;
     
     public Ghost(Color color,Direction direction, int speed, int posX, int posY) {
    	 this.color=color;
    	 this.direction=direction;
    	 this.speed=speed;
    	 this.posX = posX;
    	 this.posY = posY;
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

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
			speed=speed/2;
	}
	
	public void lowerSpeed() {
		this.speed = this.speed / 2;
	}
	
	public void increaseSpeed() {
		this.speed = this.speed * 2;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	

	
	public static Direction getRandomDirection(ArrayList<Direction> impossibleDirections, Ghost ghost) {
		
//		System.out.println("SIZE TAB = " + impossibleDirections.size());
		
		boolean imposs = false;
		int[] tabDirections = new int[impossibleDirections.size()];
		
		for (int i=0; i<impossibleDirections.size(); i++) {
				tabDirections[i] = impossibleDirections.get(i).ordinal();
//				System.out.println("INT DIREC IMPO = " + tabDirections[i]);
		}
		
		Random rand = new Random(); 
		int numberRandom;		
		
		do {
			numberRandom = rand.nextInt(4);
			
			for (int i : tabDirections) {
				
				if(numberRandom == i) {
					imposs = true;
//					System.out.println("##################");
					break;
				}
				else {
					imposs = false;
				}
			}
//			System.out.println("*****************************");
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("mon RAND  = " + numberRandom);
		} while (imposs);
		
	
		switch(numberRandom) {
			case 0:	return Direction.UP;
//					System.out.println("setRandomDirection UP");
//					break;
			case 1:	return Direction.DOWN;
//					System.out.println("setRandomDirection DOWN");
//					break;
			case 2:	return Direction.LEFT;
//					System.out.println("setRandomDirection LEFT");
//					break;
			case 3: return Direction.RIGHT;
//					  System.out.println("setRandomDirection RIGHT");
//					  break;
			default: 
//					System.out.println("CAS IMPOSSIBLE DANS GETDIRECTIONRANDOM");
					return ghost.getDirection();
		}
	}
}
