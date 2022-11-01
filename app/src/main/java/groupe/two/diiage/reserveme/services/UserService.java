package groupe.two.diiage.reserveme.services;

import groupe.two.diiage.reserveme.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("auth/")
    Call<User> login(@Query("login") String username, @Query("password") String password);
}
