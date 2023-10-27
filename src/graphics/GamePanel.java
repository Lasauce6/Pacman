package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends Pannel {
    public GamePanel(Client client) {
        super(client);
        setLayout(null);
        setOpaque(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        client.drawLabyrinth(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
