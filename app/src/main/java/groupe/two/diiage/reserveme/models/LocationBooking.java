package groupe.two.diiage.reserveme.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LocationBooking implements Serializable {
    public Integer id;

    @SerializedName("start_at")
    public LocalDateTime startAt;

    @SerializedName("end_at")
    public LocalDateTime endAt;

    @SerializedName("booked_by")
    public Integer bookedBy;

    @SerializedName("booked_by_display")
    public String bookedByDisplay;

    public LocationBooking(Integer id, LocalDateTime startAt, LocalDateTime endAt, Integer bookedBy, String bookedByDisplay) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.bookedBy = bookedBy;
        this.bookedByDisplay = bookedByDisplay;
    }
}
