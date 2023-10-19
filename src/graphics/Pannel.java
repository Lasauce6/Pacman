package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

abstract class Pannel extends JPanel implements ActionListener {
    public final Client client;

    Pannel(Client client) {
        this.client = client;
    }


}
