package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
//            System.out.println("W");
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
//            System.out.println("S");
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
//            System.out.println("A");
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
//            System.out.println("D");
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            //System.out.println("p");
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){

            upPressed = false;
        }
        if(code == KeyEvent.VK_S){

            downPressed = false;
        }
        if(code == KeyEvent.VK_A){

            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){

            rightPressed = false;
        }

    }
}
