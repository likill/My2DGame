package main;

import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40,arial_80B;
    //BufferedImage keyImage, pickaxeImage, axeImage,hoeImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        //OBJ_Key key = new OBJ_Key(gp);
        //keyImage = key.image;
        //OBJ_iron_pickaxe pickaxe = new OBJ_iron_pickaxe(gp);
        //pickaxeImage = pickaxe.image;
        //OBJ_iron_axe axe = new OBJ_iron_axe(gp);
        //axeImage = axe.image;
        //OBJ_iron_hoe hoe = new OBJ_iron_hoe(gp);
        //hoeImage = hoe.image;

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

//        if(gp.gameState == gp.titleState){
//        }
        //play state
        if(gp.gameState == gp.playState){
            //gp.player.draw(g2);
        }
        //pause state
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //dialogue state
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

        //画出钥匙X数量
//        g2.setFont(arial_40);
//        g2.setColor(Color.white);
//        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//        g2.drawString("X"+ gp.player.hasKey, 74, 60);
//        //如果有破土工具就会显示稿子
//        if(gp.player.able_distroy == true) {
//            g2.drawImage(pickaxeImage, 0, gp.screenHeight - gp.tileSize, gp.tileSize, gp.tileSize, null);
//        }
//        if(gp.player.able_farm == true) {
//            g2.drawImage(hoeImage, gp.tileSize*2, gp.screenHeight - gp.tileSize , gp.tileSize, gp.tileSize, null);
//        }
//        if(gp.player.able_logging == true) {
//            g2.drawImage(axeImage, gp.tileSize, gp.screenHeight - gp.tileSize, gp.tileSize, gp.tileSize, null);
//        }
//
//
//
//        if(messageOn == true){
//
//            g2.setFont(g2.getFont().deriveFont(30F));
//            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
//
//            messageCounter++;
//
//            if(messageCounter > 120){
//                messageCounter = 0;
//                messageOn = false;
//            }
//        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        String text = "PAUSE";
        //System.out.println("p");

        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        //window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}

