package uslessgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Hero extends JComponent{
    final int width = GameFrame.WIN_WIDTH,
              height = GameFrame.WIN_HEIGHT,
              sizeJump = 60;
    
    private Image heroImage = null;
    
    private int x,
                y;
    
    public Hero() {
        this.x = 0;
        this.y = 0;
        this.loadImage();
    }
    
    public Hero( int x, int y) {
        this.x = x;
        this.y = y;
        this.loadImage();
    }
    
    private void loadImage(){
        try {
            this.heroImage = ImageIO.read( new File("hero.png"));
        } catch (IOException e) {
            System.out.println("Error L:30 C:HERO.java FAIL FILE READ");
        }
    }
    
    public boolean doMuveDown(){
        this.y += this.sizeJump;
        
        if( y > this.height)
            return false;
        
        return true;
    }
    
    public void upDate( int muve){
        switch(muve){
            case 1:
                if( this.x + this.sizeJump < this.width)
                    this.x += sizeJump;
                break;
            
            case 2:
                if( this.x - sizeJump >= 0)
                    this.x -= sizeJump;
                break;
            
            case 3:
                if( this.y + sizeJump < this.height)
                    this.y += sizeJump;
                break;
            
            case 4:
                if( this.y - sizeJump >= 0)
                    this.y -= sizeJump;
                break;
        }
    }
    
    public void Draw(Graphics g){
        g.drawImage( heroImage, x, y, this);
    }
}
