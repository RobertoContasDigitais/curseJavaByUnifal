package guinterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class MyComponents extends JButton implements ActionListener {
    private JFrame frame;
    private Color newColor;

    public MyComponents( String text, JFrame frame, int x, int y, int width,
        int height, Color newColor ) {
        super( text );
        super.setBounds( x, y, width, height );
        super.setVisible( true );
        this.frame = frame;
        this.newColor = newColor;
        this.addActionListener( this );
    }
        
    public void actionPerformed( ActionEvent e ) {
    this.frame.getContentPane().setBackground( this.newColor );
    }   
}
