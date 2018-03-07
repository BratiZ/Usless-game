package uslessgame;

import java.awt.Color;
import java.awt.Graphics;

public class Hero {
    final int width = GameFrame.WIN_WIDTH,
              height = GameFrame.WIN_HEIGHT;
    
    int x,
        y;
    
    public Hero() {
        this.x = this.width/2;
        this.y = this.height/2;
    }
    
    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw( Graphics g){
        
        
        
        g.setColor( Color.BLUE);
        g.fillOval( x+30, y+10, 21, 21);
        g.setColor( Color.BLACK);
        g.drawOval( x+30, y+10, 21, 21);
        
        g.setColor( Color.BLUE);
        g.fillOval( x-10, y+10, 21, 21);
        g.setColor( Color.BLACK);
        g.drawOval( x-10, y+10, 21, 21);
        
        g.setColor( Color.CYAN);
        g.fillOval( x, y, 41, 41);
        g.setColor( Color.BLACK);
        g.drawOval( x, y, 41, 41);
    }

    
}
