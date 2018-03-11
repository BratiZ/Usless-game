package uslessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Hero {
    private final int width = GameFrame.WIN_WIDTH,
                      height = GameFrame.WIN_HEIGHT,
                      waySize = 50;
    
    private int x,
                y,
                heroSize = 40;
    
    private Image heroImage;
    
    public Hero() {
        this.x = this.width/2;
        this.y = this.height/2;
    }
    
    public Hero(int x, int y) {
        this.x = x * this.waySize;
        this.y = y * this.waySize;
        
        try{
            this.heroImage = ImageIO.read(new File("pictures/knuckles.png")); 
        } catch( Exception e){
            System.out.println("Bad File Knuckles.png");
        }
    }
    
    public int getLogicPositionX(){
        return this.x / this.waySize;
    }
    
    public int getLogicPositionY(){
        return this.y / this.waySize;
    }
    
    public int getHeroSize(){
        return this.heroSize;
    }
    
    public void setHeroSize( int heroSize){
        this.heroSize = heroSize;
    }
    
    public void update( int muve, int heroMuve){
        switch( muve){
            case 1:
                if( this.x - heroMuve * this.waySize >= 0)
                    this.x -= heroMuve * this.waySize;
                break;
            
            case 2:
                if( this.x +  heroMuve * this.waySize < this.width)
                    this.x += heroMuve*this.waySize;
                break;
                
            case 3:
                if( this.y - heroMuve * this.waySize >= 0)
                    this.y -= heroMuve*this.waySize;
                break;
                
            case 4:
                if( this.y + 2 * heroMuve * this.waySize < this.height)
                    this.y += heroMuve*this.waySize;
                break;
        }
    }
        
    public void draw( Graphics g){
        
        g.drawImage( this.heroImage, this.x+5, this.y+5, this.heroSize, this.heroSize, null);
    }

    
}
