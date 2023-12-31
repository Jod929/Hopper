package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

  KeyHandler keyH;

  public final int screenX;
  public final int screenY;
  public int score;
  public int scoreAdder = 0;
  public boolean gameOver = false;


  public Player(GamePanel gp, KeyHandler keyH) {

    super(gp);

    this.keyH = keyH;

    screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

    solidArea = new Rectangle();
    solidArea.x = 8;
    solidArea.y = 16;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    solidArea.width = 32;
    solidArea.height = 32;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {

    worldX = gp.tileSize * 23;
    worldY = gp.tileSize * 21;
    speed = 4;
    direction = "down";
  }

  public void getPlayerImage() {

    try {

      up1 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_up_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_up_2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_down_1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_down_2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_left_1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_left_2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_right_1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("../res/player/boy_right_2.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void update() {

    if (keyH.upPressed == true ||
        keyH.downPressed == true ||
        keyH.leftPressed == true ||
        keyH.rightPressed == true) {


      if (keyH.upPressed == true) {
        direction = "up";
      }
      else if (keyH.downPressed == true) {
        direction = "down";
      }
      else if (keyH.leftPressed == true) {
        direction = "left";
      }
      else if (keyH.rightPressed == true) {
        direction = "right";
      }

      // CHECK TILE COLLISION
      collisionOn = false;
      gp.collisionChecker.checkTile(this);


      // CHECK OBJECT COLLISON

      // int objIndex = gp.collisionChecker.checkObject(this, true);
      // pickUpObject(objIndex);


      // IF COLLISION IS FALSE, PLAYER CAN MOVE
      if (collisionOn == false) {
        switch(direction) {
          case "up":
            worldY -= speed;
            scoreAdder++;
            if (scoreAdder >= gp.tileSize / 4) {
              score++;
              scoreAdder = 0;
            }
            break;
          // case "down":
          //   worldY += speed;
          //   break;
          case "left":
            worldX -= speed;
            break;
          case "right":
            worldX += speed;
            break;
        }
      }

      System.out.println("score " + score);

      spriteCounter++;

      if (spriteCounter > 20) {
        if (spriteNum == 1) {
          spriteNum = 2;
        } else if (spriteNum == 2) {
          spriteNum = 1;
        }
        spriteCounter = 0;
      }

    }

  }


  // public void pickUpObject(int i) {

  //   if (i != 999) {

  //     String objectName = gp.obj[i].name;

  //     switch (objectName) {
  //       case "Key":
  //         hasKey++;
  //         gp.obj[i] = null;
  //         System.out.println("Key: " + hasKey);
  //         break;
  //       case "Door":
  //         if (hasKey > 0) {
  //           gp.obj[i] = null;
  //           hasKey--;
  //         }
  //         System.out.println("Key: " + hasKey);
  //         break;
  //       case "Boots":
  //         gp.obj[i] = null;
  //         speed += 2;
  //         break;
  //     }
  //   }

  // }


  public void draw(Graphics2D g2) {

    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (spriteNum == 1) {
          image = up1;
        }
        if (spriteNum == 2) {
          image = up2;
        }
        break;
      case "down":
        if (spriteNum == 1) {
          image = down1;
        }
        if (spriteNum == 2) {
          image = down2;
        }
        break;
      case "right":
        if (spriteNum == 1) {
          image = right1;
        }
        if (spriteNum == 2) {
          image = right2;
        }
        break;
      case "left":
        if (spriteNum == 1) {
          image = left1;
        }
        if (spriteNum == 2) {
          image = left2;
        }
        break;
    }

    if (gp.gameOver) {

      if (score > gp.highScore) {
        gp.highScore = score;
      }

      g2.drawString("GAME OVER", screenX, screenY);
      g2.drawString(Integer.toString(score), screenX, screenY + gp.tileSize);
      g2.drawString("High Score " + Integer.toString(score), solidAreaDefaultX, solidAreaDefaultY);
    } else {
      g2.drawString(Integer.toString(score), solidAreaDefaultX + gp.tileSize * 15, solidAreaDefaultY);
      g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }



  }



}
