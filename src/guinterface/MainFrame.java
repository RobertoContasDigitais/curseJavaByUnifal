package guinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
  
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Gerenciados de Taredas Em JAVA");
        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //layout config
        setLayout(null);

        //painel de navegação
   
       
    }
    
}