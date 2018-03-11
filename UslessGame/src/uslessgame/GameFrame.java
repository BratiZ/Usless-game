package uslessgame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
public static final int WIN_WIDTH = 550,
                        WIN_HEIGHT = 700;    

    GameBoard GB;
    
    public GameFrame() throws HeadlessException {
        super("Usless Game");
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        this.GB = new GameBoard();
        
        add( this.GB);
        
        pack();
        setVisible( true);
    }

    
    
}
