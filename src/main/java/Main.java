import com.google.gson.GsonBuilder;
import org.everit.json.schema.ValidationException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userChoise = 0;
        int userChoiseSecondLevel = 0;
        String nameOfFile;
        String pathToFile;
        List<String> color = new ArrayList<>();
        GsonBuilder1 gsonBuilder = new GsonBuilder1();


        System.out.println("Hello, dear User");
        System.out.println("This program can work with JSON");
        do {
            System.out.println("You can choose function:");
            System.out.println("\t1 - Serialize object; 2 - Validate json; 3 - Deserialize json");
            System.out.println("For exit enter 4");
            System.out.println("Enter number that you choose");
            try {
                userChoise = sc.nextInt();
            }catch(java.util.InputMismatchException e){
                System.out.println("Uncorrect input data");
            }

            switch (userChoise){
                case 1:{
                    System.out.println("Let's try create animal object, enter some info ");
                    System.out.println("Create animal by myself - 1, Default animal - another number");
                    Animal animal;
                    try {
                        userChoiseSecondLevel = sc.nextInt();
                    }catch(java.util.InputMismatchException e){
                        System.out.println("Uncorrect input data");
                    }
                    if(userChoiseSecondLevel == 1) {
                        System.out.println("Type of animal: ");
                        String tmpAnimaType = sc.next();
                        System.out.println("Name of animal: ");
                        String tmpAnimaName = sc.next();
                        System.out.println("Age of animal: ");
                        int tmpAnimaAge;
                        try {
                            tmpAnimaAge = sc.nextInt();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Uncorrect input data");
                            tmpAnimaAge = 0;
                        }
                        System.out.println("Has an animal tail: ");
                        boolean tmpAnimaTail;
                        try {
                            tmpAnimaTail = sc.nextBoolean();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Uncorrect input data");
                            tmpAnimaTail = false;
                        }
                        System.out.println("Color of animal: ");
                        String tmpAnimaColor = sc.next();
                        color.add(tmpAnimaColor);
                        System.out.println("How many friens does an animal have: ");
                        int tmpAnimaFriend;
                        try {
                            tmpAnimaFriend = sc.nextInt();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Uncorrect input data");
                            tmpAnimaFriend = 0;
                        }
                        animal  = new Animal(tmpAnimaType, tmpAnimaAge, tmpAnimaName, tmpAnimaTail, color, tmpAnimaFriend);
                    }
                    else{
                        System.out.println("I WORK");
                        color.add("black");
                        color.add("white");
                        animal  = new Animal("Dog", 2, "Bobic", true, color, 3);
                    }

                    System.out.println("Give name for your file: ");
                    nameOfFile = sc.next();
                    gsonBuilder.serialize(animal, nameOfFile);
                    break;
                }
                case 2:{
                    System.out.println("For validate your json  with the animal jsonschema Enter path to the json: ");
                    pathToFile = sc.next();
                    gsonBuilder.validJson(pathToFile);

                    break;
                }

                case 3:{
                    System.out.println("For desirialize your json Enter path to the json: ");
                    pathToFile = sc.next();
                    gsonBuilder.deserialize(pathToFile, Animal.class);
                    break;
                }
                default:{
                    break;
                }
            }
        }while (userChoise != 4);

        System.out.println("Program is endind its work");

       /* For check gsonBuilder class
        System.out.println(gsonBuilder.deserialize("C:\\Users\\Админ\\IdeaProjects\\Lesson13_HW_Gson\\src\\main\\resources\\1.json", Animal.class));
        System.out.println(gsonBuilder.deserialize("C:\\Users\\Админ\\IdeaProjects\\Lesson13_HW_Gson\\src\\main\\resources\\2.json", Animal.class));


        try {
            gsonBuilder.validJson("C:\\Users\\Админ\\IdeaProjects\\Lesson13_HW_Gson\\src\\main\\resources\\1.json");
            System.out.println("The JSON is valid");
        }catch (ValidationException ex){
            System.out.println("The JSON is not valid");
        }
        */

    }
}
