package resource;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Scanner;


//recebe os dados de uma task que foi criada adiciona ele no controller
//e o controler gerencia as tasks de acordo com o que o admin precisa
public class ControllerTasks{

    private File file = new File("/home/roberto/Documentos/java/curseJavaByUnifal-master/src/repository/tasksContoler.txt");

    LinkedHashMap<Integer,String> map;

    public ControllerTasks(){
        this.map  = new LinkedHashMap<Integer,String>();
        loadTask();
    }

//carrega o arquivo id-description
    public void loadTask(){

        try {
            if(!this.file.exists()){
                this.file.createNewFile();
            }else{
                Scanner scanner = new Scanner(this.file);
                while(scanner.hasNextLine()) {
                    String line = (scanner.nextLine());
                    String[] lineTupla = line.split("  ");
                    this.map.put(Integer.valueOf(lineTupla[0]), lineTupla[1]);
                }
                scanner.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public LinkedHashMap getMapTheTaskByUser(String user){
        return this.map;
    }

    public void createNewTask(String name, String dataEntrega, String description ){
        
        Tasks task = new Tasks(name, description,dataEntrega, map.size());
        saveInFinalTasksController(task);
        saveNewTask(task);
        loadTask();

    }

    public void saveInFinalTasksController(Tasks newTasks){
        try {
            loadTask();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(newTasks.getId()+"  "+newTasks.getName()+"\n");  
            fileWriter.close(); 
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNewTask(Tasks task){
        Path path = Paths.get("/home/roberto/Documentos/java/curseJavaByUnifal-master/src/repositoryTask/",
        String.valueOf(task.getId()) + ".txt");
        try {
                Files.createDirectories(path.getParent());
                FileWriter fileWriter = new FileWriter(path.toFile());
                fileWriter.write(task.toString());
                fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void put(Integer key, String taskDescription){
        this.map.put(key, taskDescription);        
    }

    public boolean containsKey(Integer taskId){
        return this.map.containsKey(taskId);
    }

    public String editorTaskByControlerId(String id){
        Path path = Paths.get("/home/roberto/Documentos/java/curseJavaByUnifal-master/src/repositoryTask/",id+ ".txt");
        String stringTaskLoad = "";
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                stringTaskLoad += scanner.nextLine() + "\n";
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringTaskLoad;    
    }
    
    public void saveNewTaskEditor(String id, String toStringTask){
        try {
            Path path = Paths.get("/home/roberto/Documentos/java/curseJavaByUnifal-master/src/repositoryTask/",id+ ".txt");
            FileWriter fileWriter = new FileWriter(path.toFile());
            fileWriter.write(toStringTask);
            fileWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
