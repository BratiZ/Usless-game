package uslessgame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameFrame extends JFrame{

    public GameFrame() throws HeadlessException {
        super();
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        
        add( new GameBoard());
        
        pack();
        setVisible( true);
    }

    
    
}
