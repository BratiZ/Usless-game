package uslessgame;

import java.awt.Color;
import java.awt.Graphics;

public class WayPart {

    private final int width = GameFrame.WIN_WIDTH,
                      height = GameFrame.WIN_HEIGHT,
                      size = 50;
    
    private Color color,
            borderColor;
    
    private int x,
                y;
    
    public WayPart() {
        this.x = 0;
        this.y = 0;
        this.color = Color.WHITE;
        this.borderColor = Color.BLACK;
    }
    
    public WayPart(int x, int y) {
        this.x = x * this.size;
        this.y = y * this.size;
        this.color = Color.WHITE;
        this.borderColor = Color.BLACK;
    }
 
    public void draw( Graphics g){
        g.setColor( color);
        g.fillRect(this.x, this.y, this.size, this.size);
        
        g.setColor( this.borderColor);
        g.drawRect(this.x, this.y, this.size, this.size);
    }
}
