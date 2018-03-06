package uslessgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    public static final int WIN_WIDTH = 420,
                            WIN_HEIGHT = 600;        
    
    public GameFrame() throws HeadlessException {
        super("Usless Game");
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        add( new GameBoard());
        pack();
        setVisible( true);
    }

    
    
}
