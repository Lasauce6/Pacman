package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    private final Client client;
    private final Font font = new Font("Arial", Font.BOLD, 40);
    private final JButton start = new JButton("Start");
    private final JButton exit = new JButton("Exit");

    MenuPanel(Client client) {
        super();
        this.client = client;
        setLayout(null);
        setOpaque(true);
        start.addActionListener(this);
        exit.addActionListener(this);
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
        g.drawString("PacMan", Client.WIDTH / 2 - 80, 100);
    }

    private void setButtons() {
        start.setBounds(Client.WIDTH / 2 - 75 , 300, 150, 50);
        exit.setBounds(Client.WIDTH / 2 - 75, 400, 150, 50);
        this.add(start);
        this.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            client.game();
        } else if (e.getSource() == exit) {
            client.close();
        }
    }
}
