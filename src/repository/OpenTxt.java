package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import resource.Tasks;

public class OpenTxt {

    public static void writerTasks(String path, Tasks task){
        try(
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter writerByTasks = new PrintWriter(buffer);
        ){
            writerByTasks.append(task.toString());
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
