import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString

@Accessors(chain = false)
public class Friend {

    @SerializedName("count")
    private int count;

    public Friend() {
    }

    public Friend(int count) {
        this.count = count;
    }

}
