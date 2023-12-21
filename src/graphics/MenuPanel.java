package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class MenuPanel
 */
public class MenuPanel extends JPanel implements ActionListener {
    private final Client client; // Le client
    private final Font font = new Font("Arial", Font.BOLD, 40); // Police d'écriture
    private final JButton start = new JButton("Start"); // Bouton pour commencer
    private final JButton exit = new JButton("Exit"); // Bouton pour quitter

    /**
     * Constructeur
     * @param client le client
     */
    MenuPanel(Client client) {
        super();
        this.client = client;
        setLayout(null);
        setOpaque(true);
        start.addActionListener(this);
        exit.addActionListener(this);
    }

    /**
     * Méthode qui dessine le panel
     * @param g le graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        setTitle(g);
        setButtons();
    }

    /**
     * Méthode qui dessine le titre
     * @param g le graphics
     */
    private void setTitle(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(font);
        g.drawString("PacMan", Client.WIDTH / 2 - 80, 100);
    }

    /**
     * Méthode qui dessine les boutons
     */
    private void setButtons() {
        start.setBounds(Client.WIDTH / 2 - 75 , 300, 150, 50);
        exit.setBounds(Client.WIDTH / 2 - 75, 400, 150, 50);
        this.add(start);
        this.add(exit);
    }

    /**
     * Méthode qui gère les actions des boutons
     * @param e l'évènement
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            client.game();
        } else if (e.getSource() == exit) {
            client.close();
        }
    }
}
