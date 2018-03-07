package uslessgame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    public static final int WIN_WIDTH = 600,
                            WIN_HEIGHT = 1000;        
    
    public GameFrame() throws HeadlessException {
        super("Usless Game");
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        add( new GameBoard());
        pack();
        setVisible( true);
    }

    
    
}
