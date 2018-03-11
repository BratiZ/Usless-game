package uslessgame;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class WayPart {

    private final int width = GameFrame.WIN_WIDTH,
                      height = GameFrame.WIN_HEIGHT,
                      size = 50;
        
    private int x,
                y,
                score;
    
    private Image fieldImage[];
    
    public void setScore( int score){
        this.score = score;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public WayPart() {
        this.x = 0;
        this.y = 0;
        this.loadPictures();
    }
    
    public WayPart(int x, int y) {
        this.x = x * this.size;
        this.y = y * this.size;
        this.loadPictures();
    }
    
    private void loadPictures(){
        try{
            this.fieldImage = new Image[5];
            this.fieldImage[0] = ImageIO.read(new File("pictures/waypart000.png"));
            this.fieldImage[1] = ImageIO.read(new File("pictures/waypart100.png"));
            this.fieldImage[2] = ImageIO.read(new File("pictures/waypart200.png"));
            this.fieldImage[3] = ImageIO.read(new File("pictures/waypart300.png"));
            this.fieldImage[4] = ImageIO.read(new File("pictures/waypart400.png"));
        }catch( Exception e){
            System.out.println("Bad File fieldimage queen BG");
        }
    }
    
    
    
    public void draw( Graphics g){       
        if( this.score < 101)
            g.drawImage( this.fieldImage[0], this.x, this.y, this.size, this.size, null);
        
        else if( this.score < 201) 
            g.drawImage( this.fieldImage[1], this.x, this.y, this.size, this.size, null);
        
        else if( this.score < 301) 
            g.drawImage( this.fieldImage[2], this.x, this.y, this.size, this.size, null);
        
        else if( this.score < 401) 
            g.drawImage( this.fieldImage[3], this.x, this.y, this.size, this.size, null);
        
        else 
            g.drawImage( this.fieldImage[4], this.x, this.y, this.size, this.size, null);
    }
}
