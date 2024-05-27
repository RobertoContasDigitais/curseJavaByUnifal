package guinterface;

<<<<<<< HEAD
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
=======
import resource.ControllerTasks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class MyComponents extends JComponent implements ActionListener {
    private JFrame frame;
    private LinkedHashMap<String,Component> map = new LinkedHashMap<String, Component>();
    @SuppressWarnings("rawtypes")
    private LinkedHashMap mapTask;
    private ControllerTasks controllerTasks = new ControllerTasks();

    public MyComponents(JFrame frame){
        this.frame = frame;
    }

    public void createLabel(String description,  int x, int y, int width, int height){
        JLabel label = new JLabel(description);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setVisible(true);
        this.frame.add(label);
    }

    public void createButons(String description,  int x, int y, int width, int height, String command){
        JButton button = new JButton(description);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setVisible(true);
        button.setActionCommand(command);
        button.addActionListener(this);
        this.frame.add(button);
    }

    public void createTextFild(String description,  int x, int y, int width, int height, String key){
        JTextField textField = new JTextField(description, 25);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Arial", Font.ITALIC, 14));
        textField.addFocusListener(new MyFocusListener(textField, description));
        textField.setVisible(true);
        frame.add(textField);
        this.map.put(key, textField);
    }

    public void createTextArea(String description,  int x, int y, int width, int height,  String key){
        JTextArea textArea = new JTextArea(description,width, height);
        textArea.setBounds(x, y, width, height);
        textArea.setFont(new Font("Arial", Font.ITALIC, 14));
        textArea.addFocusListener(new MyFocusListener(textArea, description));
        textArea.setVisible(true);
        frame.add(textArea);
        this.map.put(key, textArea);

    }

    public void openNewDialog(){
        JDialog dialog = new JDialog(frame,"Adicione sua nova Tarefa", true);
        dialog.setSize(500,600);
        dialog.setLayout(null);
        dialog.setLocation(1300,320);

        int col = (int)dialog.getWidth()/4; int esp = col/5;
        int lin = (int)dialog.getHeight()/(2*8);
        
        //recebe o nome da tarefa
        JTextField dialogFieldname = new JTextField("Nome:", (col*4 - esp*2));
        dialogFieldname.setBounds(esp, esp, col*4-esp*2, lin);
        dialogFieldname.addFocusListener(new MyFocusListener(dialogFieldname, "Nome:"));
        dialogFieldname.setVisible(true);
        dialog.add(dialogFieldname);
        
        JTextField dialogFieldDate = new JTextField("Data Final: DD/MM/AAAA", (col*4 - esp*2));
        dialogFieldDate.setBounds(esp, esp + lin, col*2-esp, lin);
        dialogFieldDate.setVisible(true);
        dialogFieldDate.addFocusListener(new MyFocusListener(dialogFieldDate, "Data Final: DD/MM/AAAA"));
        dialog.add(dialogFieldDate);

        JLabel labelDateInit = new JLabel("Data Inicial é quando clicar SALVAR!");
        labelDateInit.setBounds(esp, esp + lin*2, col*2-esp, lin);
        labelDateInit.setBackground(Color.white);
        labelDateInit.setVisible(true);
        dialog.add(labelDateInit);
        
        JLabel messegerByUser = new JLabel("Para verificar a Tarefa a salva, faça uma nova busca na pagina principal!");
        messegerByUser.setBounds(esp, esp + lin*3, col*4-esp*2, lin);
        messegerByUser.setBackground(Color.white);
        messegerByUser.setVisible(true);
        dialog.add(messegerByUser);
        
        JTextArea dialogAreaDescription = new JTextArea("Descreva aqui as etapas:\n"+"Ex.\nInicio - Levantamento de dados\nDurante - Buscar novas parcerias");
        dialogAreaDescription.setBounds(esp, lin*4 + lin*2, col*4-esp*2, lin*10);
        dialogAreaDescription.addFocusListener(new MyFocusListener(dialogAreaDescription,
        "Descreva aqui as etapas:\n"+"Ex.\nInicio - Levantamento de dados\nDurante - Buscar novas parcerias"));
        dialogAreaDescription.setVisible(true);
        dialog.add(dialogAreaDescription);

        JButton button1Save = new JButton("SALVAR!");
        button1Save.setBounds(col*2, esp + lin*2, col*2-esp, lin);
        button1Save.setVisible(true);
        button1Save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                String name = dialogFieldname.getText();
                String date = dialogFieldDate.getText();
                String description = dialogAreaDescription.getText();
                
                controllerTasks.createNewTask(name, date, description);
                dialog.dispose();
            }
        });
        
        dialog.add(button1Save);        
        dialog.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        String command = arg0.getActionCommand();

        switch (command) {
            case "buscar":
                // pegar o conteudo de uma textfild especifica
                JTextField commandField1 = (JTextField)map.get("user");
                String textField1 = commandField1.getText();                
                System.out.println(textField1);

                JTextField commandField2 = (JTextField)map.get("keyword");
                String textField2 = commandField2.getText();
                System.out.println(textField2);
              
                this.controllerTasks.loadTask();
                this.mapTask = controllerTasks.getMapTheTaskByUser(textField1);

                JTextArea commandArea1 = (JTextArea)map.get("textarea");
                String textArea1 = "";
                for(int i = 0 ;i < this.mapTask.size(); i++){
                    textArea1 = textArea1 + mapTask.get(i) +"\n";
                }
                commandArea1.setText(textArea1);
                System.out.println(textArea1);


        
                break;
            case "dialog":
                openNewDialog();
                break;
        
            default:
                break;
        }
        
    }

    //classe aninhada
    public class MyFocusListener implements FocusListener {
        private final JTextComponent textComponent;
        private final String placeholder;

        public MyFocusListener(JTextComponent textComponent,  String placeholder){
                this.textComponent = textComponent;
                this.placeholder = placeholder;
        }


        @Override
        public void focusGained(FocusEvent arg0) {
            if(textComponent.getText().equals(placeholder)){
                textComponent.setText("");
            }            
        }

        @Override
        public void focusLost(FocusEvent arg0) {
            if(textComponent.getText().isEmpty()){
                textComponent.setText(placeholder);
            }
        }
    
        
    }

>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
}
