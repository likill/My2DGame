package entity;

import main.GamePanel;

public class NPC_Nan extends Entity{
    public NPC_Nan(GamePanel gp) {
        super(gp);

        getNanImage();

    }
    public void getNanImage(){

        down1 = setup("/npc/A_down1_1");

    }
}
