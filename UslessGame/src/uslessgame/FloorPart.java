package uslessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;

public class FloorPart extends JComponent{
    final int width = GameFrame.WIN_WIDTH,
              height = GameFrame.WIN_HEIGHT,
              sizeJump = 60,
              size = 60;
    
    private Color color;
    
    private int x,
                y;
    
    public FloorPart(int x, int y) {
        this.x = x * this.sizeJump;
        this.y = y * this.sizeJump;
        this.color =  RandColor();
    }
    
    final Color RandColor(){
        int ColorNumber = new Random().nextInt(8);
        switch( ColorNumber) {
            default:
                return Color.WHITE;
            case 0:
                return Color.BLUE;
            case 1: 
                return Color.CYAN;
            case 2:
                return Color.MAGENTA;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.PINK;
            case 5:
                return Color.RED;
            case 6:
                return Color.YELLOW;
            case 7:
                return Color.GREEN;
        }
    }
        
    public void update(){
        if( this.y + this.sizeJump < this.height)
            this.y += this.sizeJump;
        else{
            this.y = 60;
            this.RandColor();
        }
    }
    
    public void draw( Graphics g){
        g.setColor( this.color);
        g.fillRect( x, y, size, size);
        
        g.setColor( Color.BLACK);
        g.drawRect( x, y, size, size);
        
    }
}
