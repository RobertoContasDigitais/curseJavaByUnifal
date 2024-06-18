package guinterface;

import resource.Checker;
import resource.ControllerTasks;
import resource.Tasks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

public class MyComponents extends JComponent implements ActionListener {
    private JFrame frame;
    private Tasks task;
    private LinkedHashMap<String,Component> map = new LinkedHashMap<String, Component>();
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

    public void createButons(String description,  int x, int y, int width, int height, String command,
        int fontLength, boolean stylo ){
        JButton button = new JButton(description);
        button.setBounds(x, y, width, height);
        if (stylo)
            button.setFont(new Font("Arial", Font.BOLD, fontLength));
        else{
            button.setFont(new Font("Arial", Font.ROMAN_BASELINE, fontLength));
            button.setMargin(new Insets(0, 0, 0, 0));
        }
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
    
    public void createTextFormated(String description,  int x, int y, int width, int height,  String key){
        try {
            JFormattedTextField textFieldFormated = new JFormattedTextField( 
            new MaskFormatter("##/##/####") );
        
        textFieldFormated.setBounds(x, y, width, height);
        textFieldFormated.setFont(new Font("Arial", Font.ITALIC, 14));
        textFieldFormated.setText("DD/MM/AAAA");
        textFieldFormated.setVisible(true);
        frame.add(textFieldFormated);    
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        

    }
    
    public void openNewDialog(){//-----------------------dialog para criar tarefas

        JDialog dialog = new JDialog(frame,"Adicione sua nova Tarefa", true);
        dialog.setSize(500,600);
        dialog.setLayout(null);
        dialog.setLocation(850,200);

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
        
        @SuppressWarnings("deprecation")
        Locale locale = new Locale("Pt","BR");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy",locale);//vai mudar de acordo com o task
        //aqui tem que modificar de acordo com o task que entrar
        JTextField dialogFieldDateInitial = new JTextField("Data Inicio: " + 
        format.format(new GregorianCalendar().getTime()), (col*4 - esp*2));
        dialogFieldDateInitial.setBounds(col*2, esp + lin, col*2-esp, lin);
        dialogFieldDateInitial.setEditable(false);
        dialogFieldDateInitial.setBackground(Color.WHITE);
        dialogFieldDateInitial.setVisible(true);
        dialogFieldDateInitial.addFocusListener(new MyFocusListener(dialogFieldDateInitial, "Data Final: DD/MM/AAAA"));
        dialog.add(dialogFieldDateInitial);
        
        JLabel messegerByUser = new JLabel("Para verificar a Tarefa a salva, faça uma nova busca na pagina principal!");
        messegerByUser.setBounds(esp, esp + lin*2, col*4-esp*2, lin);
        messegerByUser.setBackground(Color.white);
        messegerByUser.setVisible(true);
        dialog.add(messegerByUser);
        
        JTextArea dialogAreaDescription = new JTextArea("Descreva aqui as etapas:\n"+"Ex.\nInicio - Levantamento de dados\nDurante - Buscar novas parcerias");
        dialogAreaDescription.setBounds(esp, lin*3 + lin, col*4-esp*2, lin*10);
        dialogAreaDescription.addFocusListener(new MyFocusListener(dialogAreaDescription,
        "Descreva aqui as etapas:\n"+"Ex.\nInicio - Levantamento de dados\nDurante - Buscar novas parcerias"));
        dialogAreaDescription.setVisible(true);
        dialog.add(dialogAreaDescription);

        JButton button1Save = new JButton("SALVAR!");
        button1Save.setBounds(col*2, esp + lin*14, col*2-esp, lin);
        button1Save.setVisible(true);
        button1Save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                String name = dialogFieldname.getText();
                String date = dialogFieldDate.getText();
                String description = dialogAreaDescription.getText();
                if(Checker.dateFormatValidate(date) == true){
                    controllerTasks.createNewTask(name, Checker.formatDateDefalt(date), description);
                    dialog.dispose();
                }else
                    JOptionPane.showMessageDialog(null, "Algo de errado na data", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.add(button1Save);        
        dialog.setVisible(true);

    }

    public void openNewDialogTask(Tasks task){//-----------------------dialog para editar
        JDialog dialog = new JDialog(frame,"Editar sua nova Tarefa", true);
        dialog.setSize(500,600);
        dialog.setLayout(null);
        dialog.setLocation(850,200);

        int col = (int)dialog.getWidth()/4; int esp = col/5;
        int lin = (int)dialog.getHeight()/(2*8);
        
        //recebe o nome da tarefa
        JTextField dialogFieldname = new JTextField(task.getName(), (col*4 - esp*2));
        dialogFieldname.setBounds(esp, esp, col*4-esp*2, lin);
        dialogFieldname.setBackground(Color.WHITE);
        dialogFieldname.setEditable(false);
        dialogFieldname.setVisible(true);
        dialog.add(dialogFieldname);
        
        JTextField dialogFieldDate = new JTextField("Data que Iniciou a tarefa: "+task.getDateFinal());
        dialogFieldDate.setBounds(esp, esp + lin, col*2-esp, lin);
        dialogFieldDate.setBackground(Color.WHITE);
        dialogFieldDate.setEditable(false);
        dialogFieldDate.setVisible(true);
        dialog.add(dialogFieldDate);
        
        JTextField dialogFieldDateFinal = new JTextField("Data de Entrega: "+task.getDareInitial());
        dialogFieldDateFinal.setBounds(col*2, esp + lin, col*2-esp, lin);
        dialogFieldDateFinal.setVisible(true);
        dialog.add(dialogFieldDateFinal);
        
        JLabel messegerByUser = new JLabel("DESCRIÇÃO ABAIXO!");
        messegerByUser.setBounds(esp, esp + lin*2+10, col*4-esp*2, lin);
        messegerByUser.setBackground(Color.white);
        messegerByUser.setVisible(true);
        dialog.add(messegerByUser);

        //status
        String[] itens = {"ATIVA", "CANCELADA", "FINALIZADA", "STADBY"};
        JComboBox<String> cb = new JComboBox<>(itens);
        cb.setVisible(true);
        cb.setBounds(col*2, esp + lin*2+5, col*2-esp, lin);
        dialog.add(cb);
        cb.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            task.setStatus( (String)cb.getSelectedItem());
            }
        });

        
        JTextArea dialogAreaDescription = new JTextArea(task.getDescription());
        dialogAreaDescription.setBounds(esp, lin*3 + lin, col*4-esp*2, lin*10);
        dialogAreaDescription.setVisible(true);
        dialog.add(dialogAreaDescription);

        JButton button1Save = new JButton("Salvar Alteração!");
        button1Save.setBounds(col*2, esp + lin*14, col*2-esp, lin);
        button1Save.setVisible(true);
        button1Save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                String name = dialogFieldname.getText();
                String date = dialogFieldDate.getText().split(": ")[1];
                String description = dialogAreaDescription.getText();
                if(Checker.dateFormatValidate(date) == true){
                    System.out.println("aqui vamos salvar o task editado \n");
                    System.out.println(task.toString());
                    //aplicando todas as mudanças antes de mandar salvar;
                    task.setName(name); task.setDateFinal(date); task.setDescription(description); 
                    controllerTasks.saveNewTask(task);
                    dialog.dispose();
                }else
                    JOptionPane.showMessageDialog(null, "Algo de errado na data", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.add(button1Save);        
        dialog.setVisible(true);



    }

    public void editorTheTask(String id){
        try {
            System.out.println((mapTask.containsKey(Integer.valueOf(id))));
            System.out.println(!(Integer.valueOf(id)>mapTask.size()));
            System.out.println(!(Integer.valueOf(id)<0));
            if((mapTask.containsKey(Integer.valueOf(id))) && !(Integer.valueOf(id)>mapTask.size()) && !(Integer.valueOf(id)<0)){
                String taskToLoadString = controllerTasks.getInformationTask(id);
                JTextArea commandArea1 = (JTextArea)map.get("textarea");
                commandArea1.setText(taskToLoadString);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Digite um numero ao campo'Id' válido","Erro'Id'",JOptionPane.ERROR_MESSAGE);
        }
        

  
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String command = arg0.getActionCommand();

        switch (command) {
            case "buscar":
            //verificação de usuário
                JTextField commandField1 = (JTextField)map.get("user");
                String textField1 = commandField1.getText();                
                JTextField commandField2 = (JTextField)map.get("keyword");
                String textField2 = commandField2.getText();
              
                //busca o map do controler que tem doto id e tarefa -> map<Integer, Task>
                this.mapTask = controllerTasks.getMapTheTaskByUser(textField1, textField2);
                JTextArea commandArea1 = (JTextArea)map.get("textarea"); //busca o textarea para setar tudo que tem no map
                String textArea1 = "";
                
                //Se o map retornado for vazio tem que avisar na rext área
                if(mapTask.isEmpty()){
                    commandArea1.setText("Você não tem nenhum tarefa listada!\n"+
                    "Para incluir alguma tarefa utilize o botão NOVA TAREFA."
                    );                    
                }else{
                    for(int i = 0 ;i < this.mapTask.size(); i++){
                        //eu pego o valor do map a partir do 1 porque não existe a tarefa
                        textArea1 = textArea1+ String.valueOf(i+1)+ " - " + mapTask.get(i+1) +"\n";
                    }
                    commandArea1.setText(textArea1);
                    System.out.println(textArea1);
                }
                break;
            case "dialog":
                openNewDialog();
                break;
            
            case "mostrar":
                JTextField commandField3 = (JTextField)map.get("id");
                String textField3 = commandField3.getText();                
                editorTheTask(textField3);
                break;
            
            case "editar":
                JTextArea commandField4Field = (JTextArea)map.get("textarea");
                JTextField commandField5 = (JTextField)map.get("id");
                if(!commandField5.getText().equals("Id")){
                    //carregar todas a infrmaçãos do task cujo id é o id da text área
                    String textField5 = commandField5.getText();System.out.println(1);                
                    task = controllerTasks.saveNewTaskEditor(textField5);System.out.println(2);
                    openNewDialogTask(task);System.out.println(3);
                }
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

}
