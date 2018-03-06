package uslessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import java.util.Random;

public class Hero {
    private int x,
                y;
    
    public Hero() {
        this.x = 50;
        this.y = 100;
    }
    
    public Hero( int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void upDate( int dx, int dy){
        this.x -= dx;
        this.y -= dy;
        
    }
    
    Color randColor(){
        Random rand = new Random();
        int color = rand.nextInt(10);
        
        switch( color){
            default:
                return Color.WHITE;
            case 0:
                return Color.WHITE;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.RED;
            case 5:
                return Color.LIGHT_GRAY;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.PINK;
            case 8:
                return Color.ORANGE;
            case 9:
                return Color.YELLOW;
        }
    }
    
    public void Draw(Graphics g){
        g.setColor( Color.BLACK);
        g.fillRect(x, y, 60, 60);
        
        g.setColor( this.randColor());
        g.fillOval(x+10, y+10, 10, 10);//lewe oko

        g.setColor( this.randColor());
        g.fillOval(x+40, y+10, 10, 10);//prawe oko

        g.setColor( this.randColor());
        g.fillRect(x+10, y+35, 40, 10);//usta
        
        g.setColor( Color.BLACK);
        g.fillRect(x+45, y+40, 5, 5);//prawy koncik
        g.fillRect(x+10, y+40, 5, 5);//lewy koncik
    }
    
}
