import java.awt.*;
import java.util.*;

public class Game {

    // 4 Fantome
    // pacman
    // 4 pacgommme

    private Ghost ghost1;
    private Ghost ghost2;
    private Ghost ghost3;
    private Ghost ghost4;
    private int extraLIfe = 0;

    public int getExtraLIfe() {
        return extraLIfe;
    }

    public void setExtraLIfe(int extraLIfe) {
        this.extraLIfe = extraLIfe;
    }

    private ArrayList<Pacgomme> pacgommes;
    private Pacman pacman;
    private String[][] map = {
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "!", "!", "!", "!", "!", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "!", "!", "!", "!", "!", "!", "!", "!", "!", "!", "!", "!", "!", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "-", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "-", "!", "!", "-", "-", "-", "-", "-", "#", "-", "-", "-", "!", "!", "!", "!", "!", "!", "!", "!", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "#", "#", "#", "!", "#", "-", "#", "#", "#", "#", "!", "!", "!", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "-", "-", "-", "-", "-", "-", "-", "!", "#", "-", "#", "#", "#", "#", "!", "!", "!", "#", "#", "#", "#", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "#", "#", "#", "-", "#", "#", "#", "#", "!", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#"},
            {"#", "-", "-", "-", "-", "#", "#", "#", "#", "!", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "#", "#", "#", "#", "-", "-", "-", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "#", "#", "#", "!", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "-", "-", "-", "-", "-", "-", "-", "!", "#", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "!", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "!", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
            {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
    };

    public Game() {
        pacman = new Pacman();
        pacgommes = createListPacgommes();

        ghost1 = new Ghost(Color.PINK, Direction.UP, 20, 17, 8);
        ghost2 = new Ghost(Color.GRAY, Direction.UP, 20, 16, 9);
        ghost3 = new Ghost(Color.CYAN, Direction.RIGHT, 20, 17, 9);
        ghost4 = new Ghost(Color.RED, Direction.LEFT, 20, 18, 9);
    }

    public ArrayList<Pacgomme> createListPacgommes() {
        ArrayList<Pacgomme> pacgommes = new ArrayList<>();
        Pacgomme pacgommePurple = new PacgommePurple();
        Pacgomme pacgommeOrange = new PacgommeOrange();
        Pacgomme pacgommeGreen = new PacgommeGreen();
        pacgommes.add(pacgommePurple);
        pacgommes.add(pacgommeOrange);
        pacgommes.add(pacgommeGreen);
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 35; j++) {
                if (Objects.equals(map[i][j], "-")) {
                    if ((pacgommeGreen.getPosX() != i) || (pacgommeGreen.getPosY() != j)) {
                        if ((pacgommeOrange.getPosX() != i) || (pacgommeOrange.getPosY() != j)) {
                            if ((pacgommePurple.getPosX() != i) || (pacgommePurple.getPosY() != j)) {
                                pacgommes.add(new PacgommeBlue(i, j));
                            }
                        }
                    }
                }
            }
        }
//		System.out.println("coupDEHORS = " + coupDEHORS);
//		System.out.println("coupDEDANS = " + coupDEDANS);

        return pacgommes;
    }


    public ArrayList<Pacgomme> getPacgommes() {
        return pacgommes;
    }

    public void setPacgommes(ArrayList<Pacgomme> pacgommes) {
        this.pacgommes = pacgommes;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public Ghost getGhost1() {
        return ghost1;
    }

    public String[][] getMap() {
        return map;
    }

    public void setMap(int posX, int posY, String mapBox) {
        if (validBox(posX, posY))
            map[posX][posY] = mapBox;
    }

    public void setMapeffect(String[][] map) {
        this.map = map;
    }

    private boolean validBox(int posX, int posY) {
//		System.out.println("posX = " + posX +  " posY = " + posY);
        if ((posX >= 0) && (posY >= 0) && (posY < map.length)) {
//			System.out.println("validBOX taille 1 = " + map.length +  " taille 2 = " + map[0].length );
            return posX < map[0].length;
        }
        return false;
    }

    public void setGhost1(Ghost ghost1) {
        this.ghost1 = ghost1;
    }

    public Ghost getGhost2() {
        return ghost2;
    }

    public void setGhost2(Ghost ghost2) {
        this.ghost2 = ghost2;
    }

    public Ghost getGhost3() {
        return ghost3;
    }

    public void setGhost3(Ghost ghost3) {
        this.ghost3 = ghost3;
    }

    public Ghost getGhost4() {
        return ghost4;
    }

    public void setGhost4(Ghost ghost4) {
        this.ghost4 = ghost4;
    }


    public boolean move(int posX, int posY, Direction direction) {
        if (validBox(posY, posX) && ((Objects.equals(map[posX][posY], "-")) || (Objects.equals(map[posX][posY], "!")))) {
//			System.out.println("Je suis valide dans move");
            pacman.setDirection(direction);
            pacman.setPosX(posX);
            pacman.setPosY(posY);
            eatPacgomme(posX, posY);
//			System.out.println("Points pacamn = " + pacman.getPoints() + " extraLIfe = " + extraLIfe);
            extraLife();
            return true;
        }

        // wraparound
        if (posX == 9 && posY == 34 && pacman.getDirection() == Direction.RIGHT) {
            pacman.setPosY(0);
            return true;
        } else if (posX == 9 && posY == 0 && pacman.getDirection() == Direction.LEFT) {
            pacman.setPosY(34);
            return true;
        }

        return false;
    }

    void extraLife() {
        if (pacman.getPoints() - extraLIfe >= 5000) {
            extraLIfe = pacman.getPoints();
            pacman.incLife();
        }
    }


    public boolean isWall(Direction direction, int x, int y) {


        if (Direction.UP == direction) {
            if ((validBox(x, y - 1))) {
                if ((Objects.equals(map[y - 1][x], "#"))) {
                    return true;
                }
            }
        }

        if (Direction.DOWN == direction) {
            if (validBox(x, y + 1)) {
                if ((Objects.equals(map[y + 1][x], "#"))) {
                    return true;
                }
            }
        }

        if (Direction.LEFT == direction) {
            if ((validBox(x - 1, y))) {
                if ((Objects.equals(map[y][x - 1], "#"))) {
                    return true;
                }
            }
        }

        if (Direction.RIGHT == direction) {
            if (validBox(x + 1, y)) {
                return Objects.equals(map[y][x + 1], "#");
            }
        }
        return false;
    }


    public void handlerSpeed() {

    }

    public void ghostMove() {
        // Si j'avance avec la direction actuel est ce que je vais tomber sur un mur

        if (isWall(ghost1.getDirection(), ghost1.getPosX(), ghost1.getPosY())) {
            ArrayList<Direction> impossibleDirections = impossibleDirection(ghost1.getPosX(), ghost1.getPosY());
            System.out.println("JE MODIFIE LA DIRECTION");
            Direction DirectionRandom = Ghost.getRandomDirection(impossibleDirections, ghost1);
            ghost1.setDirection(DirectionRandom);
        }
        gMove(ghost1);


        if (isWall(ghost2.getDirection(), ghost2.getPosX(), ghost2.getPosY())) {
            ArrayList<Direction> impossibleDirections = impossibleDirection(ghost2.getPosX(), ghost2.getPosY());
            Direction DirectionRandom = Ghost.getRandomDirection(impossibleDirections, ghost2);
            ghost2.setDirection(DirectionRandom);
        }
        gMove(ghost2);


        if (isWall(ghost3.getDirection(), ghost3.getPosX(), ghost3.getPosY())) {
            ArrayList<Direction> impossibleDirections = impossibleDirection(ghost3.getPosX(), ghost3.getPosY());
            Direction DirectionRandom = Ghost.getRandomDirection(impossibleDirections, ghost3);
            ghost3.setDirection(DirectionRandom);
        }
        gMove(ghost3);

        if (isWall(ghost4.getDirection(), ghost4.getPosX(), ghost4.getPosY())) {
            ArrayList<Direction> impossibleDirections = impossibleDirection(ghost4.getPosX(), ghost4.getPosY());
            Direction DirectionRandom = Ghost.getRandomDirection(impossibleDirections, ghost4);
            ghost4.setDirection(DirectionRandom);
        }
        gMove(ghost4);


    }

    // �viter de revenir en arriere, mur, en dehors de labyrinth
    public ArrayList<Direction> impossibleDirection(int posX, int posY) {
        ArrayList<Direction> directions = new ArrayList<>();
        if ((Objects.equals(map[posY - 1][posX], "#"))) {

            directions.add(Direction.UP);
        }

        if ((Objects.equals(map[posY + 1][posX], "#"))) {
            directions.add(Direction.DOWN);
        }


        if ((Objects.equals(map[posY][posX + 1], "#"))) {
            directions.add(Direction.RIGHT);
        }

        if ((Objects.equals(map[posY][posX - 1], "#"))) {
            directions.add(Direction.LEFT);
        }

        return directions;
    }

    public void gMove(Ghost ghost) {
        int posX = ghost.getPosX();
        int posY = ghost.getPosY();

        switch (ghost.getDirection()) {
            case UP:
                if (((Objects.equals(map[posY - 1][posX], "-")) || (Objects.equals(map[posY - 1][posX], "!")))) {
                    ghost.setPosY(ghost.getPosY() - 1);
                }
                break;
            case DOWN:
                if (((Objects.equals(map[posY + 1][posX], "-")) || (Objects.equals(map[posY + 1][posX], "!")))) {
                    ghost.setPosY(ghost.getPosY() + 1);
                }
                break;
            case RIGHT:
                if (((Objects.equals(map[posY][posX + 1], "-")) || (Objects.equals(map[posY][posX + 1], "!")))) {
                    ghost.setPosX(ghost.getPosX() + 1);
                }

                break;
            case LEFT:
                if (((Objects.equals(map[posY][posX - 1], "-")) || (Objects.equals(map[posY][posX - 1], "!")))) {
                    ghost.setPosX(ghost.getPosX() - 1);
                }
                break;
            default:
                break;
        }

    }

    public boolean win() {
        return pacgommes.isEmpty();
    }

    public boolean die() {
        return !pacman.isAlive();
    }

    public boolean gameEnd() {
        return win() || die();
    }

    public void eatPacgomme(int posX, int posY) {
        if (Objects.equals(map[posX][posY], "-")) {
            int index = searchIndexPacgomme(posX, posY);
            Timer timer = new Timer();
            //			System.out.println("index mange = " + index);
            if (index != -1) {
                //				System.out.println("Je mange le pacgomme" + quelCouleur(pacgommes.get(index)) );
                Pacgomme pacgomme = pacgommes.get(index);

                if (postionEquality(posX, posY, 15, 33)) {
                    //					PacgommePurple	pg= (PacgommePurple) pacgommes.get(0);
                    //					pg.effect(pacman);
                    pacman.setState(PacManState.InvisiblePacMan);
                    //Jaune p�le
                    pacman.setColor(new Color(255, 255, 153));
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            pacman.setColor(Color.YELLOW);
                            pacman.setState(PacManState.NormalPacMan);

                        }
                    }, 1000 * 600);

                }

                if (postionEquality(posX, posY, 1, 5)) {
                    //					PacgommeOrange	pg= (PacgommeOrange) pacgommes.get(1);
                    ArrayList<Ghost> ghosts = new ArrayList<Ghost>(Arrays.asList(ghost1, ghost2, ghost3, ghost4));
                    //					pg.effect(pacman, ghosts);
                    pacman.setState(PacManState.SuperPacMan);
                    pacman.setColor(Color.ORANGE);
                    for (Ghost ghost : ghosts) {
                        ghost.setColor(Color.BLUE);
                    }
                    ghost1.lowerSpeed();
                    ghost2.lowerSpeed();
                    ghost3.lowerSpeed();
                    ghost4.lowerSpeed();
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            pacman.setColor(Color.YELLOW);
                            pacman.setState(PacManState.NormalPacMan);

                        }
                    }, 1000 * 600);
                }

                if (postionEquality(posX, posY, 19, 1)) {
                    map = Mapeffect();
                }

                pacman.addPoints(pacgomme.getPoints());
                removePacgomme(index);
            }
        }
    }


    public void removePacgomme(int index) {
//		if(index != -1)
        pacgommes.remove(index);
    }

    public int searchIndexPacgomme(int posX, int posY) {
        // Pas s�r parce que j'ai fais ce teste dans move
//		if(validBox(posX, posY)) {
//			return -1;
//		}
        for (int i = 0; i < pacgommes.size(); i++) {
            if ((pacgommes.get(i).getPosX() == posX) && (pacgommes.get(i).getPosY() == posY)) {
                return i;
            }
        }
        return -1;
    }

    public boolean postionEquality(int x1, int y1, int x2, int y2) {
        return (x1 == x2) && (y1 == y2);
    }

    public void collision() {

        if (pacman.getState() == PacManState.NormalPacMan) {
            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost1.getPosY(), ghost1.getPosX())) {
                System.out.println("REVENU A MAPLACE");
                pacman.setLife(pacman.getLife() - 1);
                pacman.setPosX(18);
                pacman.setPosY(17);
            }

            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost2.getPosY(), ghost2.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                pacman.setPosX(18);
                pacman.setPosY(17);
            }

            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost3.getPosY(), ghost3.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                pacman.setPosX(18);
                pacman.setPosY(17);
            }
            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost4.getPosY(), ghost4.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                pacman.setPosX(18);
                pacman.setPosY(17);
            }

        }

        if (pacman.getState() == PacManState.InvisiblePacMan) {
            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost1.getPosY(), ghost1.getPosX())) {
                ghost1.setPosX(17);
                ghost1.setPosY(8);
            }

            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost2.getPosY(), ghost2.getPosX())) {
                ghost2.setPosX(16);
                ghost2.setPosY(9);
            }

            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost3.getPosY(), ghost3.getPosX())) {
                ghost3.setPosX(17);
                ghost3.setPosY(9);
            }
            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost4.getPosY(), ghost4.getPosX())) {
                ghost4.setPosX(18);
                ghost4.setPosY(9);
            }
        }

        if (pacman.getState() == PacManState.SuperPacMan) {
            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost1.getPosY(), ghost1.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                ghost1.setPosX(17);
                ghost1.setPosY(8);
            }

            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost2.getPosY(), ghost2.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                ghost2.setPosX(16);
                ghost2.setPosY(9);
            }

            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost3.getPosY(), ghost3.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                ghost3.setPosX(17);
                ghost3.setPosY(9);
            }
            if (postionEquality(pacman.getPosX(), pacman.getPosY(), ghost4.getPosY(), ghost4.getPosX())) {
                pacman.setLife(pacman.getLife() - 1);
                ghost4.setPosX(18);
                ghost4.setPosY(9);
            }
        }
    }


    public void crossGhost() {
        if (pacman.getState() == PacManState.InvisiblePacMan) {

        }
    }

    public static String[][] Mapeffect() {
        return new String[][]{
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "#", "-", "#", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "#", "-", "#", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "#", "#", "#", "-", "#", "-", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "-", "#", "#", "#", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "-", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "-", "#", "#", "#", "-", "#", "#", "#", "-", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "#", "-", "#", "#", "#", "-", "#", "#", "#", "-", "#", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#"},
                {"#", "-", "-", "-", "-", "#", "#", "#", "#", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "#", "#", "#", "#", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "-", "#", "-", "#", "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "-", "#", "#", "-", "#", "-", "#"},
                {"#", "-", "#", "-", "#", "#", "-", "#", "#", "#", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "#", "#", "#", "-", "#", "#", "-", "#", "-", "#"},
                {"#", "-", "#", "-", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "-", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "-", "#"},
                {"#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }


}
