package groupe.two.diiage.reserveme.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import java.net.ConnectException;

import groupe.two.diiage.reserveme.repositories.LoginRepository;
import groupe.two.diiage.reserveme.services.LoginService;


/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
Context context;
    public LoginViewModelFactory(Context context)
    {
        this.context = context;
    }
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(new LoginRepository(new LoginService(this.context)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}