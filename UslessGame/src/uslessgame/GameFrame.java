package uslessgame;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    public static final int WIN_WIDTH = 600,
                            WIN_HEIGHT = 720;        
    
    public GameFrame() throws HeadlessException {
        super("Usless Game");
        
        setSize( WIN_WIDTH, WIN_HEIGHT);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        add( new GameBoard());
        
        setVisible( true);
    }

    
    
}
