import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Animal {

    @SerializedName("animal_type")
    private String animalType;
    @SerializedName("age")
    private  int age;
    @SerializedName("name")
    private String name;
    @SerializedName("has_tail")
    private boolean hasTail;
    @SerializedName("colors")
    private List<String> color;
    @SerializedName("friends")
    private Friend friends;

    public Animal(){
        color = new ArrayList<String>();
        friends = new Friend();
    }

    public Animal(String animalType, int age, String name, boolean hasTail, List<String> color, int count) {
        this.animalType = animalType;
        this.age = age;
        this.name = name;
        this.hasTail = hasTail;
        this.color = color;
        friends = new Friend(count);
    }

}
