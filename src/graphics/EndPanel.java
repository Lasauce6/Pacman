package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends JPanel implements ActionListener {
    private final boolean victory;
    private final Client client;
    private final Font font = new Font("Arial", Font.BOLD, 40);
    private final JButton restart = new JButton("Restart");
    private final JButton menu = new JButton("Menu");
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        setTitle(g);
        setButtons();
    }

    private void setTitle(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(font);
        if (victory) g.drawString("Victoire", 500, 100);
        else g.drawString("Game Over", 500, 100);
    }

    private void setButtons() {
        restart.setBounds(500, 300, 150, 50);
        menu.setBounds(500, 400, 150, 50);
        this.add(restart);
        this.add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restart) {
            client.game();
        } else if (e.getSource() == menu) {
            client.menu();
        }
    }
}
