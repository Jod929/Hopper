package main;

import java.util.Random;

import entity.NPC_OldMan;
// import object.OBJ_BOOTS;
// import object.OBJ_CHEST;
// import object.OBJ_DOOR;
// import object.OBJ_KEY;

public class AssetSetter {

  GamePanel gp;

  AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setObject() {

  }

  public void setNPC() {

    for (int i = 0; i < 4; i++) {
      gp.npc[i] = new NPC_OldMan(gp);

      Random sideRandom = new Random();
      int side = sideRandom.nextInt(2);

      Random speedRandom = new Random();
      int speed = speedRandom.nextInt(1, 4);

      if (side == 1) {
        // RIGHT
        gp.npc[i].worldX = gp.tileSize * 21 + (5 * 50);
        gp.npc[i].worldY = gp.tileSize * 10 + (i * 50);
        gp.npc[i].direction = "left";
      } else {
        // LEFT
        gp.npc[i].worldX = gp.tileSize * 21 + (5 * -50);
        gp.npc[i].worldY = gp.tileSize * 10 + (i * 50);
        gp.npc[i].direction = "right";
      }

      gp.npc[i].speed = speed;
      // gp.npc[i].collision = true;


    }

    // gp.npc[0] = new NPC_OldMan(gp);
    // gp.npc[0].worldX = gp.tileSize * 21;
    // gp.npc[0].worldY = gp.tileSize * 21;

    // gp.npc[1] = new NPC_OldMan(gp);
    // gp.npc[1].worldX = gp.tileSize * 24;
    // gp.npc[1].worldY = gp.tileSize * 24;

  }


}
