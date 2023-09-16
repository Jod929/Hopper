package player;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Player extends JPanel {

  public Player() {
    setLayout(new BorderLayout());
    add(new JLabel("im the player", JLabel.CENTER));
    setBorder(new LineBorder(Color.GRAY));
  }

}
