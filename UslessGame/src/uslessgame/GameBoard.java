package uslessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements KeyListener{
    final int width = 600,
              height = 600;
    
    Hero hero;
    
    boolean onFloor = true;
    
    Timer timer;
    TimerTask task;
    
    public GameBoard(){
        super();
        setPreferredSize( new Dimension( width, height));
        setBackground( Color.WHITE);
        
        addKeyListener( this);
        
        this.hero = new Hero(width/2-30, height-60);
        
        this.timer = new Timer(true);
        this.task = new TimerTask() {
            @Override
            public void run() {
                hero.upDate(0,2);
                repaint();
            }
        };
        
        timer.scheduleAtFixedRate(task, 0, 100);
        
        setVisible(true);
    }
    
    @Override
    public void paint( Graphics g){
        super.paint(g);
        this.hero.Draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int keyCode = e.getKeyCode();
       
       if( keyCode == KeyEvent.VK_SPACE){
           System.out.println("elo");
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
