package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.attribute.IntegerSyntax;
import javax.swing.JButton;
// import javax.swing.JLabel;
import javax.swing.JLabel;

public class EndScreen {

  GamePanel gp;
  BufferedImage img;
  JButton button;
  JLabel score;
  JLabel highScore;

  public EndScreen(GamePanel gp) {
    this.gp = gp;

    getBackground();

    button = new JButton("Restart");
    gp.add(button);
  }

  public void getBackground() {

    try {
      img = ImageIO.read(getClass().getResourceAsStream("../res/endscreen/game_over.png"));

    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void updateScore(int finalScore) {
    highScore = new JLabel(Integer.toString(finalScore));
    gp.add(highScore);
  }

  public void draw(Graphics2D g2) {
    // g2.drawImage(img, 125, 10, 500, 500, null);

    gp.setBackground(Color.WHITE);


  }

}
