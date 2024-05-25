package resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


//recebe os dados de uma task que foi criada adiciona ele no controller
//e o controler gerencia as tasks de acordo com o que o admin precisa
public class ControllerTasks{

    private File file = new File("/home/roberto/Documents/cursojava/finalproject/"+
    "finalprojectcurse/src/repository/tasksContoler.txt");


    Map<Integer,String> map;

    public ControllerTasks(){
        this.map  = new LinkedHashMap<Integer,String>();
    }

//carrega o arquivo id-description
    public void loadTask(){

        try {
            if(!this.file.exists()){
                this.file.createNewFile();
                FileWriter fileWriter = new FileWriter(this.file);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "EEEE, 'de' dd MMMM 'de' yyyy", new Locale("pt","BR")); 
                String firstLine =  "Arquivo criado " + 
                    simpleDateFormat.format( new GregorianCalendar().getTime());

                fileWriter.write(firstLine);
                fileWriter.close();
    
            }else{
                Scanner scanner = new Scanner(this.file);
                for(int i = 0 ; scanner.hasNextLine(); i++) {
                    if(i==0)
                    {
                        String line = (scanner.nextLine());
                        System.out.println(line);
                    }else
                    {
                        String line = (scanner.nextLine());
                        String[] lineTupla = line.split("  ");
                        System.out.println("primeiro elemento{"+lineTupla[0]+"} Segundo elemento{"+lineTupla[1]+"}");
                        this.map.put(Integer.valueOf(lineTupla[0]), lineTupla[1]);
                    
                    }
                }
                scanner.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

//cria uma nova Task no controle de task e recarrega o map
/*    public void writerNewTask(){
        try {
            FileWriter fileWriter = new FileWriter(this.file);

            fileWriter.write();
            fileWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map.clear();
        loadTask();
    }

 */


     public void put(Integer key, String taskDescription){
        this.map.put(key, taskDescription);        
    }

    public boolean containsKey(Integer taskId){
        return this.map.containsKey(taskId);
    }

}
