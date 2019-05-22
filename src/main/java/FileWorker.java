import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWorker {
    private Path DEFAULT_SAVE_DIRECTORY;
    private Path pathWriteToFile;
    private Path pathReadFromFile;


    public FileWorker() {
        DEFAULT_SAVE_DIRECTORY = Paths.get("C:\\Users\\Админ\\IdeaProjects\\Lesson13_HW_Gson\\src\\main\\resources");
    }

    public void writeToFile(String nameofJson, String json){
        pathWriteToFile = Paths.get(DEFAULT_SAVE_DIRECTORY + "\\" + nameofJson + ".json");
        if (!Files.isWritable(pathWriteToFile)){
            try {
                Files.createFile(pathWriteToFile);
            } catch (IOException e) {
                //Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, "Could not create new file", e);
                System.out.println("The File was not created");
            }
        }

        try {
            Files.write(pathWriteToFile, json.getBytes());
        } catch (IOException e) {
           // Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, "Could not write to file", e);
            System.out.println("The File was not wroten");
        }

    }

    public String readFromFile(String path){
        List<String> jsonList = new ArrayList<>();
        String json = "";

        if(Paths.get(path).isAbsolute()){
            pathReadFromFile = Paths.get(path);
        }
        else
            System.out.println("Uncorrect path");

        try {
            jsonList = Files.readAllLines(pathReadFromFile);
        } catch (IOException e) {
           // Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, "Could not read file", e);
            System.out.println("The File is not available");
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String str : jsonList)
            stringBuilder.append(str);

        json = stringBuilder.toString();
        return json;
    }
}
