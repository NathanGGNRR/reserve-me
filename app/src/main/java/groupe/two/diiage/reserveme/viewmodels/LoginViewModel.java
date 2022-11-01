package groupe.two.diiage.reserveme.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import groupe.two.diiage.reserveme.models.User;
import groupe.two.diiage.reserveme.repositories.LoginRepository;
import groupe.two.diiage.reserveme.models.Result;
import groupe.two.diiage.reserveme.R;
import groupe.two.diiage.reserveme.mutables.LoginFormState;
import groupe.two.diiage.reserveme.mutables.LoginResult;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        User user = loginRepository.login(username, password);

        if (user != null) {
            loginResult.setValue(new LoginResult(user));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String login, String password) {
        if (!isLoginValid(login)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isLoginValid(String login) {
        if (login == null) {
            return false;
        }
        if (login.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(login).matches();
        } else {
            return !login.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}