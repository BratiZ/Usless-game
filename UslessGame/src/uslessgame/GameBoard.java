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
    FloorPart[][] floor;
    
    Timer timer;
    TimerTask task;
    
    public GameBoard(){
        super();
        setPreferredSize( new Dimension( this.width, this.height));
        setBackground( Color.BLACK);
        setKeyBindings();

        this.hero = new Hero( 0, 0);
        this.floor = new FloorPart[this.height/60][this.width/60];
        
        for( int f = 1; f < this.floor.length; ++f){
            for( int g = 0; g < this.floor[f].length; ++g){
                this.floor[f][g] = new FloorPart( g, f);
            }
        }
        
        this.timer = new Timer(true);
        this.task = new TimerTask() {
            @Override
            public void run() {
                playerInGame();
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
        for( int f = 1; f < this.floor.length; ++f){
            for( int g = 0; g < this.floor[f].length; ++g){
                this.floor[f][g].update();
            }
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
    
    int[] x1 = { 50, 100, 100};
    int[] y1 = {  0,   0,  50};
    int n1 = x1.length;
    
    int[] x2 = { 320, 320, 370};
    int[] y2 = {   0,  50,   0};
    int n2 = x2.length;
    
    @Override
    public void paint( Graphics g){
    super.paint(g);
        
    for( int f = 1; f < this.floor.length; ++f){
        for( int h = 0; h < this.floor[f].length; ++h){
            this.floor[f][h].draw(g);
        }
    }
        
    this.hero.Draw(g);

    g.setColor( Color.LIGHT_GRAY);
    g.fillPolygon( x1, y1, n1);
    g.fillPolygon( x2, y2, n2);
    g.fillRect( 100, 0, 220, 50);
    }
}
