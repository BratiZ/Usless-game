package uslessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class GameBoard extends JPanel {
    final int width = 600,
              height = 700;
    
    final String[] buttonsAcrionMap = {"left", "right", "up", "down"};
    
    private Hero hero;
    private WayPart[][] way;
    
    private int[][] logicWay;
    private int step,
                tmpEndIndex;
    
    private Timer timer;
    private TimerTask task;
    
            
            
    public GameBoard(){
        super();
        setPreferredSize( new Dimension( this.width, this.height));
        setBackground( Color.BLACK);
        setKeyBindings();
        
        this.timer = new Timer( 1000, (ActionEvent ae) -> {
            generateWay();
            repaint();
            step++;
        });
       
        this.logicWay = new int[ this.height/50][this.width/50];
        this.way = new WayPart[ this.height/50][this.width/50];
        this.step = 0;
        
        this.loadWay();
        this.loadLogicWay();
        
        this.timer.start();
        setVisible(true);
    }
    
    
    private void loadWay(){
        for( int f = 0; f < this.way.length; ++f){
            for( int h = 0; h < this.way[f].length; ++h){
                this.way[f][h] = new WayPart(h,f);
            }
        }
    }  
    
    private void loadLogicWay(){
        for( int f = 0; f < this.way.length; ++f){
            this.logicWay[f][6] = 1;
        }
    }
    
    private void generateWay(){
        int height = this.height/50,
            width = this.width/50,
            choose,
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
                    choose = rand.nextInt(100);

                    if( choose >= 20 && endIndex+1 < this.logicWay[0].length)
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
                        if( choose >= 20 && endIndex+1 < this.logicWay[0].length)
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
            if( text == "left"){
                System.out.println("space");
            }
        }
    }
    
    @Override
    public void paint( Graphics g){
        super.paint(g);
        
        for( int f = 0; f < this.logicWay.length; ++f){
            for( int h = 0; h < this.logicWay[f].length; ++h){
                if( this.logicWay[f][h] == 1){
                    this.way[f][h].draw(g);
                }
            }
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