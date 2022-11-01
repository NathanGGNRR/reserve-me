package groupe.two.diiage.reserveme.services;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;

import groupe.two.diiage.reserveme.models.Result;
import groupe.two.diiage.reserveme.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginService extends ContextWrapper {


    public LoginService(Context base) {
        super(base);
    }

    public User login(String username, String password) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("apiSettings", Context.MODE_PRIVATE);
        SharedPreferences connectedUserPreferences = this.getSharedPreferences("connectedUser", Context.MODE_PRIVATE);

        final BlockingQueue<User> blockingQueue = new ArrayBlockingQueue<>(1);

        User userResponse = null;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("baseUrl", "https://9m5l2ws8sj.execute-api.us-east-1.amazonaws.com/Prod/");
        editor.apply();
        // TODO: Encrypt password
        // TODO: call our service to authenticate
        String test = sharedPreferences.getString("baseUrl", "default");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(test)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        userService.login(username, password).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User connectedUser = response.body();

                SharedPreferences.Editor editor = connectedUserPreferences.edit();

                editor.putInt("userId", connectedUser.id);
                editor.putString("userFirstname", connectedUser.firstname);
                editor.putString("userLastname", connectedUser.lastname);
                editor.putBoolean("userIsAdmin", connectedUser.isAdmin);
                editor.putString("userLogin", connectedUser.login);
                editor.putString("userToken", connectedUser.token);
                editor.apply();
                blockingQueue.add(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Failure", t.getMessage());
            }
        });
        try {
            userResponse = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userResponse;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}