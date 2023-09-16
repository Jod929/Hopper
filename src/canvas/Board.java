package canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import player.Player;

public class Board extends JPanel {

  public Board() {
    add(new Player());
  }

}