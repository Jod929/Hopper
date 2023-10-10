package main;

import entity.Entity;
import entity.Player;

public class CollisionChecker {

  GamePanel gp;

  public CollisionChecker(GamePanel gp) {

    this.gp = gp;
  }

  public void checkTile(Entity entity) {
    //              starting_player_spot_x +       8


    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeftWorldX/gp.tileSize;
    int entityRightCol = entityRightWorldX/gp.tileSize;
    int entityTopRow = entityTopWorldY/gp.tileSize;
    int entityBottomRow = entityBottomWorldY/gp.tileSize;

    int tileNum1, tileNum2;

    switch(entity.direction) {
      case "up":
        entityTopRow = (entityBottomWorldY - entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

        if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }

        break;
      case "down":
        entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }

        break;
      case "left":
        entityBottomRow = (entityLeftWorldX + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

        if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }
        break;
      case "right":
        entityBottomRow = (entityRightWorldX + entity.speed) / gp.tileSize;
        tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }
        break;
    }

  }

  public int checkObject(Entity entity, boolean player) {

    int index = 999;

    for (int i = 0; i < gp.obj.length; i++) {
      if (gp.obj[i] != null) {

        // get entitys solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get the objects solid area position
        gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
        gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
              if (gp.obj[i].collision == true) {
                entity.collisionOn = true;
              }

              if (player == true) {
                index = i;
              }
            }
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
              if (gp.obj[i].collision == true) {
                entity.collisionOn = true;
              }

              if (player == true) {
                index = i;
              }
            }
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
              if (gp.obj[i].collision == true) {
                entity.collisionOn = true;
              }

              if (player == true) {
                index = i;
              }
            }
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
              if (gp.obj[i].collision == true) {
                entity.collisionOn = true;
              }

              if (player == true) {
                index = i;
              }
            }
            break;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
        gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

      }
    }

    return index;
  }

  public boolean checkMonsterHit(Entity entity, Player player, boolean hit) {

    entity.solidArea.x = entity.worldX + entity.solidArea.x;
    entity.solidArea.y = entity.worldY + entity.solidArea.y;

    // get the objects solid area position
    player.solidArea.x = player.worldX + player.solidArea.x;
    player.solidArea.y = player.worldY + player.solidArea.y;

    switch (entity.direction) {
      case "up":
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(player.solidArea)) {
          if (player.collision == true) {
            entity.collisionOn = true;
            entity.stopNPC = true;
          }

          if (hit == true) {
            // index = i;
          }
        }
        break;
      case "down":
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(player.solidArea)) {
          if (player.collision == true) {
            entity.collisionOn = true;
            entity.stopNPC = true;
          }

          if (hit == true) {
            // index = i;
          }
        }
        break;
      case "left":
        entity.solidArea.x -= entity.speed;

        if (entity.solidArea.intersects(player.solidArea)) {
          if (player.collision == true) {
            entity.collisionOn = true;
            entity.stopNPC = true;
          }

          if (hit == true) {
            // index = i;
            // System.out.println("HIT GOING LEFT");
            return true;
          }
        }
        break;
      case "right":
        entity.solidArea.x += entity.speed;

        if (entity.solidArea.intersects(player.solidArea)) {
          if (player.collision == true) {
            entity.collisionOn = true;
            entity.stopNPC = true;
          }

          if (hit == true) {
            // index = i;
            // System.out.println("HIT GOING RIGHT");
            return true;
          }
        }
        break;
    }

    entity.solidArea.x = entity.solidAreaDefaultX;
    entity.solidArea.y = entity.solidAreaDefaultY;
    player.solidArea.x = player.solidAreaDefaultX;
    player.solidArea.y = player.solidAreaDefaultY;

    return false;

  }

    // int index = 999;

    // for (int i = 0; i < gp.npc.length; i++) {
    //   if (gp.npc[i] != null) {

    //     // need to get the players solid area position and the entity solid area positio
    //     // and compare those

    //     // get entitys solid area position
    //     entity.solidArea.x = entity.worldX + entity.solidArea.x;
    //     entity.solidArea.y = entity.worldY + entity.solidArea.y;

    //     // get the objects solid area position
    //     gp.npc[i].solidArea.x = gp.npc[i].worldX + gp.npc[i].solidArea.x;
    //     gp.npc[i].solidArea.y = gp.npc[i].worldY + gp.npc[i].solidArea.y;

    //     switch (entity.direction) {
    //       case "up":
    //         entity.solidArea.y -= entity.speed;
    //         if (entity.solidArea.intersects(gp.npc[i].solidArea)) {
    //           if (gp.npc[i].collision == true) {
    //             entity.collisionOn = true;
    //             entity.stopNPC = true;
    //           }

    //           if (player == true) {
    //             index = i;
    //           }
    //         }
    //         break;
    //       case "down":
    //         entity.solidArea.y += entity.speed;
    //         if (entity.solidArea.intersects(gp.npc[i].solidArea)) {
    //           if (gp.npc[i].collision == true) {
    //             entity.collisionOn = true;
    //             entity.stopNPC = true;
    //           }

    //           if (player == true) {
    //             index = i;
    //           }
    //         }
    //         break;
    //       case "left":
    //         entity.solidArea.x -= entity.speed;

    //         System.out.println("working in CC left");

    //         if (entity.solidArea.intersects(gp.npc[i].solidArea)) {
    //           if (gp.npc[i].collision == true) {
    //             entity.collisionOn = true;
    //             entity.stopNPC = true;
    //           }

    //           if (player == true) {
    //             index = i;
    //           }
    //         }
    //         break;
    //       case "right":
    //         entity.solidArea.x += entity.speed;

    //         System.out.println("working in right");

    //         if (entity.solidArea.intersects(gp.npc[i].solidArea)) {
    //           if (gp.npc[i].collision == true) {
    //             entity.collisionOn = true;
    //             entity.stopNPC = true;
    //           }

    //           if (player == true) {
    //             index = i;
    //           }
    //         }
    //         break;
    //     }

    //     entity.solidArea.x = entity.solidAreaDefaultX;
    //     entity.solidArea.y = entity.solidAreaDefaultY;
    //     gp.npc[i].solidArea.x = gp.npc[i].solidAreaDefaultX;
    //     gp.npc[i].solidArea.y = gp.npc[i].solidAreaDefaultY;

    //   }
    // }

    // return index;
  // }

}
