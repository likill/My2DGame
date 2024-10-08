package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;

import java.awt.*;

//游戏画面
public class GamePanel extends JPanel implements Runnable{
    //屏幕设置
    final int originalTileSize = 16;//16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;//48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    //system
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    //Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entity and object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];


    //gamestate
    public int gameState;
    //public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public final int dialogueState = 3;


    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        //set object
        aSetter.setObject();
        //set NPC
        aSetter.setNPC();

        //playMusic(0);
        //stopMusic();
        gameState = playState;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;//0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                //1、更新信息
                update();
                //2、绘制地图
                repaint();
                delta--;
                drawCount++;
            }


        }
    }
    public void update()
    {
        if(gameState == playState){
            //player
            player.update();
            //NPC
            for(int i = 0; i < npc.length; i++)
            {
                if(npc[i] != null)
                    npc[i].update();
            }
        }
        if(gameState == pauseState){
            //stopMusic();
            //playSE(2);
        }


    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //draw tile
        tileM.draw(g2);

        //draw object
        for(int i = 0; i < obj.length; i++)
        {
            if(obj[i] != null)
                obj[i].draw(g2, this);
        }
        //draw NPC
        for(int i = 0; i < npc.length; i++)
        {
            if(npc[i] != null)
                npc[i].draw(g2);
        }
        //player的图层必须在map后
        player.draw(g2);

        ui.draw(g2);
        g2.dispose();

    }
    //音乐播放
//    public void playMusic(int i){
//        sound.setFile(i);
//        sound.play();
//        sound.loop();
//    }
//    public void stopMusic(){
//        sound.stop();
//    }
//    public void playSE(int i){
//        sound.setFile(i);
//        sound.play();
//    }
}
