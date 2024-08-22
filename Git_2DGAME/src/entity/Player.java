package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    //public int hasKey = 0;
    int standCount = 0;
    boolean moving = false;
    int pixelCounter = 0;

    //技能
    public boolean able_distroy = false;
    public boolean able_farm = false;
    public boolean able_logging = false;




    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2-(gp.tileSize/2);
        screenY = gp.screenHeight / 2-(gp.tileSize/2);

        solidArea = new Rectangle();//人物图片是48x48
        solidArea.x = 1;
        solidArea.y = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 46;
        solidArea.height= 46;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){

        up1 = setup("/player/A_up1_4");
        up2 = setup("/player/A_up2_3");
        down1 = setup("/player/A_down1_1");
        down2 = setup("/player/A_down2_1");
        left1 = setup("/player/A_left1_2");
        left2 = setup("/player/A_left2_14");
        right1 = setup("/player/A_right1_1");
        right2 = setup("/player/A_right2_1");

    }

    public void update(){
        if(moving == false) {
            if (keyH.upPressed == true || keyH.downPressed == true
                    || keyH.leftPressed == true || keyH.rightPressed == true) {
                if (keyH.upPressed == true) {
                    direction = "up";

                } else if (keyH.downPressed == true) {
                    direction = "down";

                } else if (keyH.leftPressed == true) {
                    direction = "left";

                } else if (keyH.rightPressed == true) {
                    direction = "right";

                }
                moving = true;
                //check collision
                collisionOn = false;
                gp.cChecker.checkTile(this);

                //check object collision
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                //check npc collision
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);
            }
            else {
                standCount++;

                if (standCount == 20) {
                    spriteNum = 1;
                    standCount = 0;
                }
            }
        }
        if (moving == true){
            //if collision is false, player can move
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            pixelCounter += speed;
            if(pixelCounter == 48){
                moving = false;
                pixelCounter = 0;
            }
        }
    }
    public void pickUpObject(int i){
        if(i != 999){

//            String objectName = gp.obj[i].name;
//            switch (objectName){
//                case "Key":
//                    hasKey++;
//                    gp.obj[i] = null;
//                    gp.ui.showMessage("You got a key!");
//                    break;
//                case "box":
//                    if(hasKey > 0){
//                        gp.obj[i] = null;
//                        gp.ui.showMessage("box is open!You can run faster!");
//                        speed++;
//                        hasKey--;
//                    }
//                    else{
//                        gp.ui.showMessage("You need a key!");
//                    }
//                    break;
//                //稿子 碎石
//                case "pickaxe":
//                    gp.obj[i] = null;
//                    able_distroy = true;
//                    gp.ui.showMessage("You can distroy some stone!");
//                    break;
//                case "Rubble":
//                    if(able_distroy) {
//                        gp.obj[i] = null;
//                    }
//                    break;
//
//                //农作物//锄头
//                case "hoe":
//                    gp.obj[i] = null;
//                    able_farm = true;
//                    gp.ui.showMessage("You can farming!");
//                    break;
//                case "potato":
//                    if(able_farm) {
//                        gp.obj[i] = null;
//                    }
//                    break;
//
//                //砍伐//斧子
//                case "axe":
//                    gp.obj[i] = null;
//                    able_logging = true;
//                    gp.ui.showMessage("You can log some something!");
//                    break;
//                case "cactus":
//                    if(able_logging) {
//                        gp.obj[i] = null;
//                    }
//                    break;


//            }
        }
    }
    public void interactNPC(int i){
        if(i != 999){
            gp.gameState = gp.dialogueState;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }

                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }

                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }

                break;
        }
        g2.drawImage(image, screenX, screenY,  null);

        //显示碰撞框
//        g2.setColor(Color.red);
//        g2.drawRect(screenX+solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
}
