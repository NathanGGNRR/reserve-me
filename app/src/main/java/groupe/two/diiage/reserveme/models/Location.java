package groupe.two.diiage.reserveme.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;

public class Location implements Serializable {
    public Integer id;

    public String name;

    @SerializedName("number_limit")
    public Integer numberLimit;

    public String description;

    @SerializedName("complement_information")
    public String complementInformation;

    public Blob picture;

    @SerializedName("created_at")
    public LocalDate createdAt;

    @SerializedName("is_available_now")
    public Boolean isAvailableNow;

    // FOREIGN KEY
    @SerializedName("location_type_display")
    public String locationTypeDisplay;

    @SerializedName("owner_display")
    public String ownerDisplay;

    // LIST
    @SerializedName("bookings")
    public ArrayList<LocationBooking> locationBookings;

    @SerializedName("bookings")
    public ArrayList<Booking> bookings;

    @SerializedName("location_objects")
    public ArrayList<LocationObject> locationObjects;

    public Location(Integer id, String name, Integer numberLimit, String description, String complementInformation, Blob picture, LocalDate createdAt, Boolean isAvailableNow, String locationTypeDisplay, String ownerDisplay, ArrayList<LocationBooking> locationBookings, ArrayList<LocationObject> locationObjects) {
        this.id = id;
        this.name = name;
        this.numberLimit = numberLimit;
        this.description = description;
        this.complementInformation = complementInformation;
        this.picture = picture;
        this.createdAt = createdAt;
        this.isAvailableNow = isAvailableNow;
        this.locationTypeDisplay = locationTypeDisplay;
        this.ownerDisplay = ownerDisplay;
        this.locationBookings = locationBookings;
        this.locationObjects = locationObjects;
    }

    public Location(Integer id, String name, Integer numberLimit, String description, String complementInformation, Blob picture, LocalDate createdAt, Boolean isAvailableNow, String locationTypeDisplay, ArrayList<Booking> bookings, String ownerDisplay, ArrayList<LocationObject> locationObjects) {
        this.id = id;
        this.name = name;
        this.numberLimit = numberLimit;
        this.description = description;
        this.complementInformation = complementInformation;
        this.picture = picture;
        this.createdAt = createdAt;
        this.isAvailableNow = isAvailableNow;
        this.locationTypeDisplay = locationTypeDisplay;
        this.ownerDisplay = ownerDisplay;
        this.bookings = bookings;
        this.locationObjects = locationObjects;
    }
}
