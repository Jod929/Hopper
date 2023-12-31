package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

  // SCREEN SETTINGS
  final int originalTileSize = 16; // 16 x 16 tile
  final int scale = 3;

  public final int tileSize = originalTileSize * scale; // 48x48
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol; // 768
  public final int screenHeight = tileSize * maxScreenRow; // 576

  //  WORLD SETTINGS
  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;

  // FPS
  int FPS = 60;

  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();
  Sound sound = new Sound();
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  public AssetSetter aSetter = new AssetSetter(this);
  EndScreen endScreen = new EndScreen(this);

  Thread gameThread;

  // ENTITY AND OBJECTS
  public Player player = new Player(this, keyH);
  public SuperObject obj[] = new SuperObject[10];
  public Entity npc[] = new Entity[10];

  // GAME STATS
  public boolean gameOver = false;
  public int highScore;

  public GamePanel() {

    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void setUpGame() {

    aSetter.setObject();
    aSetter.setNPC();
    // playMusic(0);
  }

  public void startGameThread() {

    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {

    double drawInterval = 1000000000/FPS; // 0.01666666
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    while(gameThread != null) {

      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);
      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();
        delta--;
        drawCount++;
      }

      if (timer >= 1000000000) {
        System.out.println("FPS: " + drawCount);
        drawCount = 0;
        timer = 0;
      }

    }

  }

  public void update() {

    player.update();

    for (int i = 0; i < npc.length; i++) {
      if (npc[i] != null) {
        npc[i].update();
        npc[i].checkNPCPosition(this.npc[i], this.player);
      }
    }

  }

  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)g;

    if (gameOver == false) {

      // TILE
      tileM.draw(g2);

      // OBJECT
      for (int i = 0; i < obj.length; i++) {
        if (obj[i] != null) {
          obj[i].draw(g2, this);
        }
      }

      // PLAYER
      player.draw(g2);

      // NPC
      for (int i = 0; i < npc.length; i++) {
        if (npc[i] != null) {
          npc[i].draw(g2);
        }
      }

      g2.dispose();
    } else {
      endScreen.updateScore(highScore);
      endScreen.draw(g2);
    }
  }

  public void playMusic(int i) {

    sound.setFile(i);
    sound.play();
    sound.loop();
  }

  public void stopMusic() {

    sound.stop();
  }

  public void playSE(int i) {

    sound.setFile(i);
    sound.play();
  }

}
