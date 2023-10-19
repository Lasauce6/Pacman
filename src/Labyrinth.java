import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;




public class Labyrinth extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Game game;

	public Labyrinth(Game game){
		super();
		this.game = game;
		//addKeyListener( new Key(game, this));
		setOpaque(true);
		setSize(713, 500);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);		
		setBackground(Color.WHITE);
		g.setColor(Color.BLACK);
		drawScore(g);
		drawLife(g);
		drawMap(g);
		drawPath(g);
		drawPacman(g);
		drawPacgomme(g);
		drawGhost(g);

		//System.out.println("SCORE" + game.getPacman().getPoints());
	}
	
	private void drawMap(Graphics g){
		String[][] map = game.getMap();
		for(int i =0; i <21; i++)
		{ 
		  for(int j = 0; j <35; j++)
		  {
			 if(map[i][j]=="#") {
				 g.setColor(Color.BLACK);  
				 g.fillRect(j*20,i*20,20,20); 
			 }
		  }
		}
	}
	
	private void drawPath(Graphics g){
		String[][] map = game.getMap();
		for(int i =0; i <21; i++)
		{ 
			for(int j = 0; j <35; j++)
			{
				if(map[i][j]=="!" || map[i][j]=="-") {
					g.setColor(Color.WHITE);  
					g.fillRect(j*20,i*20,20,20); 
				}
			}
		}
	}
	
	private void drawPacman(Graphics g){
		

		g.setColor(game.getPacman().getColor());  
		g.fillRect(game.getPacman().getPosY()*20,game.getPacman().getPosX()*20,20,20);
	}
	
	
	private void drawPacgomme(Graphics g) {
		ArrayList<Pacgomme> pacgommes = game.getPacgommes();
		for (Pacgomme pacgomme : pacgommes) {
				g.setColor(pacgomme.getColor());  
				g.fillRect(pacgomme.getPosY()*20,pacgomme.getPosX()*20,18,18); 
		}
	}
	
	private void drawGhost(Graphics g) {
		
		g.setColor(game.getGhost1().getColor());  
		g.fillOval(game.getGhost1().getPosX()*20,game.getGhost1().getPosY()*20,20,20); 
//		g.fillOval(33*20,5*20,20,20);
//		g.fillOval((game.getGhost1().getPosX()-1)*20,(game.getGhost1().getPosY()-1)*20,20,20);
//		g.fillOval((game.getGhost1().getPosX()-1)*20,(game.getGhost1().getPosY()-1)*20,20,20);
//		int x = game.getGhost1().getPosX();
//		int y = game.getGhost1().getPosY();
//		String[][] map = game.getMap();
//		System.out.println("GHost 1 " +  map[y][x] + " de couleur " + game.getGhost1().getColor());
//		System.out.println("_________________________");
//		
//x = game.getGhost2().getPosX();
//y = game.getGhost2().getPosY();
//		System.out.println("GHost 2 " +  map[y][x] + " de couleur " + game.getGhost2().getColor());
//		System.out.println("_________________________");
//		
//x = game.getGhost3().getPosX();
//y = game.getGhost3().getPosY();
//		System.out.println("GHost 3 " +  map[y][x] + " de couleur " + game.getGhost3().getColor());
//		System.out.println("_________________________");
//		
//x = game.getGhost4().getPosX();
//y = game.getGhost4().getPosY();
//		System.out.println("GHost 4 " +  map[y][x] + " de couleur " + game.getGhost4().getColor());
//		System.out.println("_________________________");
		
g.setColor(game.getGhost2().getColor());  
g.fillOval(game.getGhost2().getPosX()*20,game.getGhost2().getPosY()*20,20,20);

g.setColor(game.getGhost3().getColor());  
g.fillOval(game.getGhost3().getPosX()*20,game.getGhost3().getPosY()*20,20,20);

g.setColor(game.getGhost4().getColor());  
g.fillOval(game.getGhost4().getPosX()*20,game.getGhost4().getPosY()*20,20,20);
		
	}
	
	private void drawScore(Graphics g) {
		
		String score =  "Score : " + game.getPacman().getPoints();
		
		Font font = new Font("Courier", Font.BOLD, 20);
	    g.setFont(font);
	    // R�cup�rer les m�triques de la police actuelle
	    FontMetrics metrics = g.getFontMetrics();
	    // La largeur de notre chaine 'score'
	    int w = metrics.stringWidth(score);
	    // La hauteur standard de notre police, ici 'Courrier'
	    int h = metrics.getHeight();
	    
	    // dessiner un rectangle pour effacer le score pr�cedent
		g.setColor(Color.WHITE);
		g.fillRect(25*20,21*20, w, h);
		
		// dessiner mon score
		g.setColor(Color.ORANGE);
		g.drawString(score , 25*20, 22*20);
	}
	
	private void drawLife(Graphics g) {
String life =  "life: " + game.getPacman().getLife();
		
		Font font = new Font("Courier", Font.BOLD, 20);
	    g.setFont(font);
	    // R�cup�rer les m�triques de la police actuelle
	    FontMetrics metrics = g.getFontMetrics();
	    // La largeur de notre chaine 'score'
	    int w = metrics.stringWidth(life);
	    // La hauteur standard de notre police, ici 'Courrier'
	    int h = metrics.getHeight();
	    
	    // dessiner un rectangle pour effacer le score pr�cedent
		g.setColor(Color.WHITE);
		g.fillRect(3*20,21*20, w, h);
		
		// dessiner mon score
		g.setColor(Color.RED);
		g.drawString(life , 3*20, 22*20);
	}
}
	

