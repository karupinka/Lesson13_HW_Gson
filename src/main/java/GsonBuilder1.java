import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GsonBuilder1<T> {
    private Gson gson;
    private FileWorker fileWorker;
    private String json;
    private Object Exception;


    GsonBuilder1(){
        gson = new Gson();
        fileWorker = new FileWorker();
    }

    public String serialize(T objectToJson, String nameSaveFile){
        json = gson.newBuilder().setPrettyPrinting().create().toJson(objectToJson);
        fileWorker.writeToFile(nameSaveFile, json);
        return json;
    }

    public String serialize(List<T> objectToJson, String nameSaveFile){
        json = gson.newBuilder().setPrettyPrinting().create().toJson(objectToJson);
        fileWorker.writeToFile(nameSaveFile, json);
        return json;
    }

    public List<T> deserialize(String pathToJson, Type type){
        json = fileWorker.readFromFile(pathToJson);
        try {
            return gson.fromJson(json, new TypeToken<List<T>>() {}.getType());
        }catch(Exception e)
         {
             try {
                 List<T> temp = new ArrayList<>();
                 temp.add(gson.fromJson(json, type));
                 return temp;
             }catch(Exception e2){
                // Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, "Could not deserialize file", e);
                 System.out.println("The File was not created");
                 return null;
             }
        }
    }

    public void validJson(String pathToJson){
        String temp = fileWorker.readFromFile(pathToJson);
        JSONObject jsonSchema = new JSONObject(new JSONTokener(GsonBuilder1.class.getResourceAsStream("/schema.json")));
        JSONArray jsonArrayJsonsubjects;

        try {
            jsonArrayJsonsubjects = new JSONArray(temp);
        }catch (org.json.JSONException e){
            JSONObject jsonSubbject = new JSONObject(temp);
            jsonArrayJsonsubjects = new JSONArray();
            jsonArrayJsonsubjects.put(jsonSubbject);

        }



        String path = "file:" + Paths.get("").toUri().getPath() + "src/main/resources/schema.json";
        System.out.println("Folder "+ path);
        if (path.contains(" ")) {
            path=path.replaceAll(" ", "%20");
        }


        SchemaLoader schemaLoader = SchemaLoader.builder().schemaJson(jsonSchema)
                .resolutionScope(path)
                .build();
        Schema schema = schemaLoader.load().build();

        for(int i = 0; i < jsonArrayJsonsubjects.length(); i++) {
            try {
                schema.validate(jsonArrayJsonsubjects.getJSONObject(i));
                System.out.println("The JSON is valid");
            }catch (ValidationException ex){
                System.out.println("The JSON is not valid");
            }   catch(Exception e){
                // Logger.getLogger(FileWorker.class.getName()).log(Level.SEVERE, "Can't validate json", e);
                System.out.println("Error");
            }

        }
    }


}
