package groupe.two.diiage.reserveme.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    @SerializedName("Id")
    public Integer id;

    @SerializedName("Firstname")
    public String firstname;

    @SerializedName("Lastname")
    public String lastname;

    public LocalDateTime birthday;

    @SerializedName("Login")
    public String login;

    public LocalDateTime createdAt;

    @SerializedName("IsAdmin")
    public Boolean isAdmin;

    @SerializedName("Token")
    public String token;

    public User(Integer id, String firstname, String lastname, LocalDateTime birthday, String login, LocalDateTime createdAt, Boolean isAdmin, String token) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.login = login;
        this.createdAt = createdAt;
        this.isAdmin = isAdmin;
        this.token = token;
    }

}
