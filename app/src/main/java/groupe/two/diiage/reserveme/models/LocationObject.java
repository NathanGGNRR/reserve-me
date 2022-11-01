package groupe.two.diiage.reserveme.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationObject implements Serializable {
    public Integer id;

    @SerializedName("location_object_display")
    public String locationObjectDisplay;

    public Integer quantity;

    public LocationObject(Integer id, String locationObjectDisplay, Integer quantity) {
        this.id = id;
        this.locationObjectDisplay = locationObjectDisplay;
        this.quantity = quantity;
    }
}
