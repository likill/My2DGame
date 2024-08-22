package main;

import entity.NPC_Nan;
import entity.NPC_OldMan;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_iron_hoe(gp);
        gp.obj[0].worldX = 30 * gp.tileSize;
        gp.obj[0].worldY = 24 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 39 * gp.tileSize;
        gp.obj[1].worldY = 24 * gp.tileSize;

        gp.obj[2] = new OBJ_iron_pickaxe(gp);
        gp.obj[2].worldX = 33 * gp.tileSize;
        gp.obj[2].worldY = 24 * gp.tileSize;

        gp.obj[3] = new OBJ_box(gp);
        gp.obj[3].worldX = 33 * gp.tileSize;
        gp.obj[3].worldY = 23 * gp.tileSize;

        gp.obj[4] = new OBJ_iron_axe(gp);
        gp.obj[4].worldX = 34 * gp.tileSize;
        gp.obj[4].worldY = 23 * gp.tileSize;

        gp.obj[5] = new OBJ_Rubble(gp);
        gp.obj[5].worldX = 35 * gp.tileSize;
        gp.obj[5].worldY = 23 * gp.tileSize;

        gp.obj[6] = new OBJ_cactus(gp);
        gp.obj[6].worldX = 36 * gp.tileSize;
        gp.obj[6].worldY = 23 * gp.tileSize;

        gp.obj[7] = new OBJ_potato_sprouts(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 23 * gp.tileSize;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        //设置初始位置
        gp.npc[0].worldX = 26 * gp.tileSize;
        gp.npc[0].worldY = 24 * gp.tileSize;
    }
}
