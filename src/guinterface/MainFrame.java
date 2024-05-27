package guinterface;


import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private float width, height;

    public MainFrame(String title, int width, int height){
        super(title);
        super.setLayout(null);
        super.setLocation(1400,350);
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
        myFramComponents.createButons("Buscar Taferas do Usuário", esp, lin*3, col*2, 25, "buscar");

        myFramComponents.createButons("Nova Tarefa", col*2+esp*4, lin, col, lin, "dialog");

        // myFramComponents.createButons("Nova Tarefa", 20, 115, 110, 25);

        System.out.println(this.width);
        System.out.println(col);
        System.out.println(lin);
        System.out.println(this.height);
        
        

        myFramComponents.createTextArea("Descrição:", 10, lin*8, col*5-10, lin*9, "textarea");

        // label1.createButons("Buscar", 100, 150, 120, 20);
        this.add(myFramComponents);
        this.setVisible(true);
    }


}