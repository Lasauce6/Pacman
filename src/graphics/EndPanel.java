package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class EndPanel
 */
public class EndPanel extends JPanel implements ActionListener {
    private final boolean victory; // true si victoire, false si défaite
    private final Client client; // Le client
    private final Font font = new Font("Arial", Font.BOLD, 40); // Police d'écriture
    private final JButton restart = new JButton("Restart"); // Bouton pour recommencer
    private final JButton menu = new JButton("Menu"); // Bouton pour retourner au menu

    /**
     * Constructeur
     * @param client le client
     * @param victory true si victoire, false si défaite
     */
    public EndPanel(Client client, boolean victory) {
        super();
        setLayout(null);
        setOpaque(true);
        setFocusable(true);
        requestFocus();
        this.client = client;
        this.victory = victory;
        restart.addActionListener(this);
        menu.addActionListener(this);
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
        if (victory) g.drawString("Victoire", Client.WIDTH / 2 - 80, 100);
        else g.drawString("Game Over", Client.WIDTH / 2 - 110, 100);
    }

    /**
     * Méthode qui dessine les boutons
     */
    private void setButtons() {
        restart.setBounds(Client.WIDTH / 2 - 75, 300, 150, 50);
        menu.setBounds(Client.WIDTH / 2 - 75, 400, 150, 50);
        this.add(restart);
        this.add(menu);
    }

    /**
     * Méthode qui gère les actions des boutons
     * @param e l'évènement
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restart) {
            client.game();
        } else if (e.getSource() == menu) {
            client.menu();
        }
    }
}
