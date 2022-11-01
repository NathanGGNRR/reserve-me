package groupe.two.diiage.reserveme.repositories;

import android.content.Context;

import groupe.two.diiage.reserveme.models.User;
import groupe.two.diiage.reserveme.services.LoginService;
import groupe.two.diiage.reserveme.models.Result;

public class LoginRepository {

    private LoginService loginService;
    private User user = null;


    public LoginRepository(LoginService loginService) {
        this.loginService = loginService;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        loginService.logout();
    }

    private void setLoggedInUser(User user) {
        this.user = user;
    }

    public User login(String username, String password) {
        // handle login
        User user = loginService.login(username, password);
        return user;
    }
}