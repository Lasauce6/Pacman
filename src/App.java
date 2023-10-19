import graphics.Client;


public class App {
    public static void main(String[] args) {
        Game game = new Game();
        Labyrinth view = new Labyrinth(game);
        Client client = new Client();
        client.menu();


//        JFrame frame = new JFrame("PacMan");
//        frame.add(view);
//
//        frame.addKeyListener(new Key(game, view));
//        frame.setSize(view.getSize());
//        frame.setLocation(500, 200);
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        do {
//
//
////            if(game.getPacman().getState() == PacManState.SuperPacMan) {
//
////                Timer timer = new Timer();
////                timer.schedule(new TimerTask() {
////                    @Override
////                    public void run() {
////                        game.ghostMove();
////                    }
////                },5000);
////            }
////            else {
//            game.ghostMove();
//
////            }
//
//
//            game.collision();
//            // rajouter move pacman ici
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            view.repaint();
//        } while (!game.gameEnd());
//
//        if (game.win()) {
//            JOptionPane.showMessageDialog(frame, "Vous avez gagné");
//        } else {
//            JOptionPane.showMessageDialog(frame, "Vous avez perdu");
//        }
    }
}
