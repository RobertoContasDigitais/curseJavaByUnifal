package guinterface;


import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private float width, height;

    public MainFrame(String title, int width, int height){
        super(title);
        super.setLayout(null);
        super.setLocation(900,200);
        super.setSize(width,height);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.width = (float)super.getWidth();
        this.height = (float)super.getHeight();
    }

    public void visibleMainFrame(){
        int col = (int)(width-10)/4;
        int esp = col/5;
        int lin = (int)(height*0.3-5)/5;

        MyComponents myFramComponents = new MyComponents(this);
        
        myFramComponents.createLabel("Usuário", esp, lin, 120, 25);
        myFramComponents.createTextFild("Roberto", col+esp, lin, 120, 25, "user");
        myFramComponents.createLabel("Tarefa", esp, lin*2, 120, 25);
        myFramComponents.createTextFild("Senha", col+esp, lin*2, 120, 25, "keyword");
        myFramComponents.createButons("Buscar Taferas do Usuário", esp, lin*3, col*2, 25, "buscar",14, true);
        myFramComponents.createButons("Editar ->", esp, lin*4, col, 25, "editar",14, true);
        myFramComponents.createTextFild("Id", esp+col, lin*4, col/2, 25, "id");
        myFramComponents.createButons("Salvar", esp+col+col/2, lin*4, col/2, 25,"salvar", 14, false);
        myFramComponents.createButons("Nova Tarefa", col*2+esp*4, lin, col, lin, "dialog", 14, true);
        myFramComponents.createTextArea("Descrição:\nId - Tarefa", 10, lin*8, col*4-esp/2, lin*9, "textarea");

        

        System.out.println(this.width);
        System.out.println(col);
        System.out.println(lin);
        System.out.println(this.height);
        
        


        // label1.createButons("Buscar", 100, 150, 120, 20);
        this.add(myFramComponents);
        this.setVisible(true);
    }


}