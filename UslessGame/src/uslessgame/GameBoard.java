package uslessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameBoard extends JPanel{
    final String[] buttonsAcrionMap = {"up", "down", "left", "right","w","s","a","d"};
    
    final int width = GameFrame.WIN_WIDTH,
              height = GameFrame.WIN_HEIGHT;
    
    
    Hero hero;
    
    Timer timer;
    TimerTask task;
    
    public GameBoard(){
        super();
        setPreferredSize( new Dimension( this.width, this.height));
        setBackground( Color.BLACK);
        setKeyBindings();
        
        this.hero = new Hero( 0, 0);
        this.timer = new Timer(true);
        this.task = new TimerTask() {
            @Override
            public void run() {
                //playerInGame();
                repaint();
            }
        };
        
        this.timer.scheduleAtFixedRate(task, 0, 800);
        
        setVisible(true);
    }
    
    private void playerInGame(){
        if( !this.hero.doMuveDown()){
            this.timer.cancel();
            this.task.cancel();
        }
    }
    
    private void heroMuve( int muve){
        this.hero.upDate(muve);
        repaint();
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
                case "up":
                case "w":
                    heroMuve(4);
                    break;
                    
                case "down":
                case "s":
                    heroMuve(3);
                    break;
                    
                case "left":
                case "a":
                    heroMuve(2);
                    break;
                    
                case "right":
                case "d":
                    heroMuve(1);
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
