package groupe.two.diiage.reserveme.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking implements Serializable {
    public Integer id;

    @SerializedName("start_at")
    public LocalDateTime startAt;

    @SerializedName("end_at")
    public LocalDateTime endAt;

    @SerializedName("book_at")
    public LocalDateTime bookAt;

    // FOREIGN KEY
    @SerializedName("location_id")
    public Integer locationId;

    @SerializedName("location_display")
    public String locationDisplay;

    @SerializedName("booked_by")
    public String bookedBy;

    // LIST
    @SerializedName("participants")
    public ArrayList<Participant> participants;

    public Booking(Integer id, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime bookAt, Integer locationId, String locationDisplay, String bookedBy, ArrayList<Participant> participants) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.bookAt = bookAt;
        this.locationId = locationId;
        this.locationDisplay = locationDisplay;
        this.bookedBy = bookedBy;
        this.participants = participants;
    }

}
