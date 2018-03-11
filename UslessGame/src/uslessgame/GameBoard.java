package uslessgame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;
import javafx.embed.swing.JFXPanel;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;

public class GameBoard extends JPanel {
    private final int width = GameFrame.WIN_WIDTH,
                      height = GameFrame.WIN_HEIGHT;
    
    private final String[] buttonsAcrionMap = {"left", "right", "up", "space", "r"};
                    
    private String speedLevel;
    
    boolean gameStart,
            jump,
            inGame,
            gameStarted;
    
    private String song,
                   deathSound;
    
    private int[][] logicWay;
    private int step,
                tmpEndIndex,
                playerScore;
    
    private Hero hero;
    private WayPart[][] way;
    
    private Timer timer;
    private Image backgroundImage;
    private JFXPanel j;
    
    private Media playerSong,
                  playerDeath;
    
    private MediaPlayer mediaPlayer;
    
    public GameBoard(){
        super();
        this.setPreferredSize( new Dimension( this.width, this.height));
        this.setKeyBindings();

        this.j = new JFXPanel();
        
        this.song = "voice/song.mp3";
        this.deathSound = "voice/DontWay.mp3";
        
        this.playerSong = new Media( new File( song).toURI().toString());
        this.playerDeath = new Media( new File( this.deathSound).toURI().toString());
        
        this.mediaPlayer = new MediaPlayer( playerSong);
        this.mediaPlayer.play();
        
        this.timer = new Timer( 1000, ( ActionEvent ae) -> {            
            if( step % 12 == 0 && timer.getDelay() >= 400)
                timer.setDelay( timer.getDelay() - 50);
            
            generateWay();
            hero.update( 4, 1);
            step++;
            
            checkMuve();
            mediaPlayer.play();            
            repaint();
        });
       
        this.logicWay = new int[ this.height / 50][this.width / 50];
        this.way = new WayPart[ this.height / 50][this.width / 50];
        
        this.step = 0;
        this.playerScore = 0;
        this.speedLevel = "normal";
        this.jump = false;
        this.inGame = true;
        this.gameStarted = true;
        
        this.loadWay();
        this.loadLogicWay();
        this.loadImage();
        
        this.setVisible( true);
    }
    
    private void loadImage(){
        try{
            this.backgroundImage = ImageIO.read( new File( "pictures/queen.png"));
        }catch( Exception e){
            System.out.println( "Bad File pictures queen BG");
        }
    }
    
    private void loadWay(){
        for( int f = 0; f < this.way.length; ++f){
            for( int h = 0; h < this.way[f].length; ++h){
                this.way[f][h] = new WayPart( h, f);
            }
        }
    }
    
    private void loadLogicWay(){
        for( int f = 0; f < this.way.length; ++f){
            for( int h = 0; h < this.way[f].length; ++h){
                 this.logicWay[f][h] = 0;               
            }
        }
        
        for( int f = 0; f < this.way.length; ++f){
            this.logicWay[f][ ( ( this.logicWay.length - 1) / 2) - 1] = 1;
        }
        
        this.hero = new Hero( ( ( this.logicWay.length - 1) / 2) - 1, this.logicWay.length - 2);
    }
    
    private void generateWay(){
        int choose,
            chooseSide;
        
        int endIndex = 0;
        
        boolean goUp = false;
        
        Random rand = new Random();
        if( step%2 == 0){
            this.muveLogicBoardDown();

            while( this.logicWay[1][endIndex++] != 1 );
            endIndex--;

            this.logicWay[0][endIndex] = 1;

            if( endIndex == 0){
                while( !goUp ){
                    choose = rand.nextInt( 100);

                    if( choose >= 20 && endIndex+1 < this.logicWay[0].length-1)
                        this.logicWay[0][++endIndex] = 1;
                    else
                        goUp = true;
                }
            }
            else if( endIndex == this.logicWay[0].length-1){
                while( !goUp ){
                    choose = rand.nextInt(100);

                    if( choose >= 20 && endIndex-1 >= 0)
                        this.logicWay[0][--endIndex] = 1;
                    else
                        goUp = true;
                }
            }
            else{
                chooseSide = rand.nextInt(2);
                while( !goUp ){
                    choose = rand.nextInt(100);

                    if( chooseSide == 0){
                        if( choose >= 20 && endIndex+1 < this.logicWay[0].length-1)
                            this.logicWay[0][++endIndex] = 1;
                        else
                            goUp = true;
                    }
                    else{
                        if( choose >= 20 && endIndex-1 > 0)
                            this.logicWay[0][--endIndex] = 1;
                        else
                            goUp = true;
                    }
                }
            }
            this.tmpEndIndex = endIndex;
        }
        else{
            this.muveLogicBoardDown();
            this.logicWay[0][tmpEndIndex] = 1;
        }
        
        for( int f = 0; f < this.logicWay[0].length; ++f){
             this.way[0][f].setScore( this.step);
        }
    }
    
    private void muveLogicBoardDown(){
        for( int f = this.logicWay.length-1; f > 0; --f){
            for( int g = 0; g < this.logicWay[f].length; ++g){
                this.logicWay[f][g] = this.logicWay[f-1][g];
            }
        }
        
        for( int f = 0; f < this.logicWay[0].length; ++f){
             this.logicWay[0][f] = 0;
        }
        
        for( int f = this.logicWay.length-1; f > 0; --f){
            for( int g = 0; g < this.logicWay[f].length; ++g){
                this.way[f][g].setScore( this.way[f-1][g].getScore()); 
            }
        }
    }
    
    private void setKeyBindings(){
        InputMap inMap = this.getInputMap( JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        for( String f : this.buttonsAcrionMap)
            inMap.put( KeyStroke.getKeyStroke( f.toUpperCase()), f);
         
        
        ActionMap ActMap = this.getActionMap();
        
        for( String f : this.buttonsAcrionMap)
            ActMap.put( f, new ActClass(f));
    }

    class ActClass extends AbstractAction{
        String text = "";

        public ActClass(String text) {
            this.text = text;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if( gameStarted){
                gameStarted = false;
            }
            if( inGame)
                if( text.equals( "left")){
                    muvePlayer(1);
                    checkMuve();
                    jump = false;
                }
                else if( text.equals( "right") ){
                    muvePlayer(2);
                    checkMuve();
                    jump = false;
                }
                else if( text.equals("up") ){
                    muvePlayer(3);
                    checkMuve();
                    jump = false;
                }
                else if( text.equals("space") ){
                    jump = !jump;
                    repaint();
                }
        
            if( text.equals("r") ){
                restart();
                repaint();
            }         
        }
        
    }
    
    private void restart(){
        this.mediaPlayer.stop();
        this.loadLogicWay();
        this.timer.stop();
        this.timer.setDelay(1000);
        this.step = 0;
        this.playerScore = 0;
        this.inGame = true;
        this.mediaPlayer = new MediaPlayer( playerSong);
        this.mediaPlayer.play();
        this.gameStarted = true;
    }
    
    public void startTimer(){
        this.timer.start();
    }
    
    public void stopTimer(){
        this.timer.stop();
    }
    public void songStop(){
        this.mediaPlayer.stop();
    }
    private void muvePlayer( int muve){
        int heroMuve = 1;
        
        if( !gameStart && this.inGame){
            startTimer();
        }
        
        if( this.jump){
            heroMuve = 2;
        }
        
        switch( muve){
            case 1:
                this.hero.update(1, heroMuve);
                break;

            case 2:
                this.hero.update(2, heroMuve);
                break;

            case 3:
                this.hero.update(3, heroMuve);
                break;
                
            case 4:
                this.hero.update(4, heroMuve);
                break;
        }
        repaint();
    }
    
    private void setSpeedLevel(){
        if( 1000 - timer.getDelay() < 200)
            this.speedLevel = "Uganda Newbie";
                
        else if( 1000 - timer.getDelay() < 300)
            this.speedLevel = "Uganda Junior";

        else if( 1000 - timer.getDelay() < 400)
            this.speedLevel = "Uganda Warrior";
        
        else if( 1000 - timer.getDelay() < 500)
            this.speedLevel = "Uganda Senior";
        
        else if( 1000 - timer.getDelay() <= 600)
            this.speedLevel = "Uganda KING";
    }
    
    private void checkMuve(){
        int heroLogicX = this.hero.getLogicPositionX(),
            heroLogicY = this.hero.getLogicPositionY();
        
        if( this.logicWay[heroLogicY][heroLogicX] == 1)
            this.playerScore++;
        else{
            this.stopTimer();
            this.inGame = false;
        }        
    }
    
    private void gameEnd( Graphics g){
        String gameOver = "Game Over";
        String text = "Score: " + this.playerScore;
        String text2 = "Press 'R' to restart";
        
        g.setColor( new Color( 0, 0, 0, 180));
        g.fillRect( 0, 0, this.width, this.height); 
        this.mediaPlayer.stop(); 
        this.mediaPlayer = new MediaPlayer( this.playerDeath);
        this.mediaPlayer.play();
        
        g.setColor( new Color( 255, 255, 255));
        g.setFont( new Font( Font.SERIF, Font.BOLD, 30));
        g.drawString( gameOver, ( this.width - g.getFontMetrics().stringWidth( gameOver)) / 2, this.height / 2);
        g.drawString( text, ( this.width - g.getFontMetrics().stringWidth( text)) / 2, ( this.height + 60) / 2);
        g.drawString( text2, ( this.width - g.getFontMetrics().stringWidth( text2)) / 2, ( this.height + 120) / 2);
    }
    
    private void gameStart( Graphics g){
        String gameOver = "Start Game!";
        String text = "Press UP to start.";
        
        g.setColor( new Color( 64, 224, 208, 90));
        g.fillRect( 0, 0, this.width, this.height - 50); 
        
        g.setColor( new Color( 0, 0, 0));
        g.setFont( new Font( Font.SERIF, Font.BOLD, 30));       
        g.drawString( gameOver, ( this.width - g.getFontMetrics().stringWidth( gameOver)) / 2, this.height / 2);
        g.drawString( text, ( this.width - g.getFontMetrics().stringWidth( text)) / 2, ( this.height + 60) / 2);
    }
    
    @Override
    public void paint( Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        this.setSpeedLevel();
        
        g.drawImage( this.backgroundImage, 0, 0, this.width, this.height, this);
        
        for( int f = 0; f < this.logicWay.length-1; ++f){
            for( int h = 0; h < this.logicWay[f].length; ++h){
                if( this.logicWay[f][h] == 1){
                    this.way[f][h].draw(g);
                }
            }
        }
        
        this.hero.draw(g);
        
        g.setColor( new Color( 0, 0, 0, 150));
        g.fillRect( 3, this.height - 45, this.width - 6, 40);
        
        g2d.setStroke( new BasicStroke( 3));
        g.setColor( Color.GRAY);
        g.drawRect( 3, this.height - 45, this.width - 6, 40);
        
        g.setColor( Color.WHITE);
        g.setFont( new Font( Font.SERIF, Font.BOLD, 30));
        g.drawString( "Score: " + this.playerScore, 7, this.height - 15);
        g.drawString( this.speedLevel, this.width - g.getFontMetrics().stringWidth( this.speedLevel) - 6, this.height - 15);
        
        if( this.jump)
            g.setColor( Color.YELLOW);
        else
            g.setColor( Color.GRAY);
        
        g.setFont( new Font( Font.SERIF, Font.BOLD, 30));
        g.drawString( "JUMP", ( this.width - g.getFontMetrics().stringWidth( "JUMP")) / 2, this.height - 15);
        
        if( !this.inGame){            
            this.gameEnd(g);
        }
        
        if( this.gameStarted){
            this.gameStart(g);
        }
    }
    
    @Override
    public String toString(){
        String resoult = "";
        
        for( int f = 0; f < this.logicWay.length; ++f){
            for( int h = 0; h < this.logicWay[f].length; ++h){
                resoult += this.logicWay[f][h];
            }
            resoult += "\n";
        }
        
        return resoult;
    }
}