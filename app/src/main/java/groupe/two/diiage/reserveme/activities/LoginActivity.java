package groupe.two.diiage.reserveme.activities;

import android.app.Activity;

import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.models.User;
import groupe.two.diiage.reserveme.mutables.LoginFormState;
import groupe.two.diiage.reserveme.mutables.LoginResult;
import groupe.two.diiage.reserveme.services.UserService;
import groupe.two.diiage.reserveme.viewmodels.LoginViewModel;
import groupe.two.diiage.reserveme.viewmodels.LoginViewModelFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory(this))
                .get(LoginViewModel.class);
        final EditText loginEditText = findViewById(R.id.login);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button signUpButton = findViewById(R.id.signup);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            signUpButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                loginEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(loginEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        loginEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(loginEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        signUpButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            this.connect(loginEditText.getText().toString(),
                    passwordEditText.getText().toString());
        });
    }

    private void connect(String login, String password) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("apiSettings", Context.MODE_PRIVATE);
        SharedPreferences connectedUserPreferences = this.getSharedPreferences("connectedUser", Context.MODE_PRIVATE);

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

        userService.login(login, password).enqueue(new Callback<User>() {

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

                redirectHome();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API Failure", t.getMessage());
            }
        });
    }

    private void redirectHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        this.startActivity(intent);
    }

    private void updateUiWithUser(User user) {
        String welcome = getString(R.string.welcome) + user.firstname;
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}