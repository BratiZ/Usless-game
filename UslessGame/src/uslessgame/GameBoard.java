package uslessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameBoard extends JPanel{
    final String[] buttonsAcrionMap = {"space"};
    
    final int width = 600,
              height = 600;
    
    int FlyTime;
    
    Hero hero;
    
    boolean onFloor = true,
            goUp = false;
    
    Timer timer;
    TimerTask task;
    
    public GameBoard(){
        super();
        setPreferredSize( new Dimension( width, height));
        setBackground( Color.WHITE);
        setKeyBindings();
        
        this.hero = new Hero(width/2-30, height-60);
        this.timer = new Timer(true);
        this.task = new TimerTask() {
            @Override
            public void run() {
                
                repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
        setVisible(true);
    }
    
    private void updateHero(){
        if( !this.goUp){
            this.goUp = true;
        }
        else if( )
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
            switch(text){
                case "space":
                        updateHero();
                    break;
            }
        }
    }
    
    @Override
    public void paint( Graphics g){
        super.paint(g);
        this.hero.Draw(g);
    }
}
