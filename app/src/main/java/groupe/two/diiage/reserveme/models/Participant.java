package groupe.two.diiage.reserveme.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Participant implements Serializable {
    public Integer id;

    @SerializedName("user_display")
    public String userDisplay;

    public Participant(Integer id, String userDisplay) {
        this.id = id;
        this.userDisplay = userDisplay;
    }
}
