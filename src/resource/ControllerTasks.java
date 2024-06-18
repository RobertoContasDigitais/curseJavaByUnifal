package resource;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;


//recebe os dados de uma task que foi criada adiciona ele no controller
//e o controler gerencia as tasks de acordo com o que o admin precisa
public class ControllerTasks{

    private String repository = "repository";
    private String repositoryTasks = "repository/repositoryTask";
    private String fileController = "taskController";
    private File directoriControler = new File(repository);
    private File directoryTasks = new File(repositoryTasks);
    
    LinkedHashMap<Integer,String> map;
    
    public ControllerTasks(){
        this.map  = new LinkedHashMap<Integer,String>();
        createDirectories();        
    }

    public void createDirectories(){
        if(!this.directoriControler.exists()){
            this.directoriControler.mkdir();
            this.directoryTasks.mkdir();
            try {
                Files.createFile(Paths.get(repository,fileController));
                System.out.println("Arquivos criados com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }           
        }else{
            loadTaskController();
        }  
    }
    
    public void loadTaskController(){    
        File file = new File(this.repository,this.fileController);
        try {
            Scanner scanner = new Scanner(file);
            int low = 0;
            while(scanner.hasNextLine()) {
                String line = (scanner.nextLine());
                if(low != 0){
                    String[] lineTupla = line.split("  ");
                    this.map.put(Integer.valueOf(lineTupla[0]), lineTupla[1]);
                }low ++;
            }
            scanner.close();
            System.out.println("O Arquivo["+fileController+"]Foi carregado com sucesso!");            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashMap getMapTheTaskByUser(String user, String passWord){
        
        if((user.equals("Roberto")) && (passWord.equals("Senha"))){
            loadTaskController();
            return this.map;
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha errado", "Erro", JOptionPane.ERROR_MESSAGE);
            return null; 
        }
        

    }

    public void createNewTask(String name, String dataEntrega, String description ){
        
        Tasks task = new Tasks(name, description,dataEntrega, map.size());
        saveInFinalTasksController(task);
        saveNewTask(task);
        loadTaskController();

    }

    public void saveInFinalTasksController(Tasks newTasks){
        File file = new File(this.repository,this.fileController);
        try {
            loadTaskController();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n"+newTasks.getId()+"  "+newTasks.getName());  
            fileWriter.close(); 
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNewTask(Tasks task){
        Path path = Paths.get(this.repositoryTasks,String.valueOf(task.getId()) + ".txt");
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

    public String getInformationTask(String id){
        Path path = Paths.get(this.repositoryTasks,id+ ".txt");
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
    
    public Tasks saveNewTaskEditor(String id){
        System.out.println(map.containsKey(Integer.valueOf(id)));
        if(map.containsKey(Integer.valueOf(id))){

            Path pathTask = Paths.get(this.repositoryTasks,id+ ".txt");
            Tasks taskEdit = new Tasks(Integer.valueOf(id));
            boolean inDescription = false;
            StringBuilder builder = new StringBuilder();

            try {
                Scanner scanner = new Scanner(pathTask);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    System.out.println(line);
                    if((line.startsWith("["))&&(line.endsWith("]")))
                        taskEdit.setName(line.substring(1, line.length() -1));
                    else if(line.startsWith("Date Inicialize: ")){
                        taskEdit.setDateInitialize(line.split(": ")[1]);
                    }else if(line.startsWith("Date to finish :")){
                        taskEdit.setDateFinal(line.split(": ")[1]);
                    }else if(line.startsWith("Task Status    :")){
                        taskEdit.setStatus(line.split(":")[1]);
                    }else if(line.startsWith("Description:")){
                        inDescription = true;
                    }else if(line.startsWith("_________________________________________|")){
                        inDescription = false;
                    }
                    else if(inDescription){
                        builder.append(line).append("\n");
                    }
                }
                taskEdit.setDescription(builder.toString().trim());
                scanner.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"id não encontrado","Erro'Id'",JOptionPane.ERROR_MESSAGE);
            }        
            return taskEdit;
        }else{
            return null;
        }
    }

}
