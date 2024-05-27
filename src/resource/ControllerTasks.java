package resource;

<<<<<<< HEAD
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
=======

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
import java.util.Scanner;


//recebe os dados de uma task que foi criada adiciona ele no controller
//e o controler gerencia as tasks de acordo com o que o admin precisa
public class ControllerTasks{

<<<<<<< HEAD
    private File file = new File("/home/roberto/Documents/cursojava/finalproject/"+
    "finalprojectcurse/src/repository/tasksContoler.txt");


    Map<Integer,String> map;
=======
    private File file = new File("/home/roberto/Documentos/java/curseJavaByUnifal-master/src/repository/tasksContoler.txt");

    LinkedHashMap<Integer,String> map;
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)

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
<<<<<<< HEAD
                        System.out.println(line);
=======
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
                    }else
                    {
                        String line = (scanner.nextLine());
                        String[] lineTupla = line.split("  ");
<<<<<<< HEAD
                        System.out.println("primeiro elemento{"+lineTupla[0]+"} Segundo elemento{"+lineTupla[1]+"}");
=======
>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
                        this.map.put(Integer.valueOf(lineTupla[0]), lineTupla[1]);
                    
                    }
                }
                scanner.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
<<<<<<< HEAD

        }
    }

=======
        }
    }

    public LinkedHashMap getMapTheTaskByUser(String user){
        return this.map;
    }

    public void createNewTask(String name, String dataEntrega, String description ){
        
        loadTask();
        Tasks task = new Tasks(name, description,dataEntrega, map.size());
        saveInFinalTasksController(task);
        saveNewTask(task);
        loadTask();

    }

    public void saveInFinalTasksController(Tasks newTasks){
        try {
            loadTask();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(newTasks.getId()+"  "+newTasks.getId()+"\n");  
            fileWriter.close(); 
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNewTask(Tasks task){
        Path path = Paths.get("/home/roberto/Documentos/java/curseJavaByUnifal-master/src/repositoryTask/", String.valueOf(task.getName()) + ".txt");
        try {
                Files.createDirectories(path.getParent());
                FileWriter fileWriter = new FileWriter(path.toFile());
                fileWriter.write(task.toString());
                fileWriter.close();
    
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



>>>>>>> b91d010 (criado alguns componentes de interface gráfica)
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
