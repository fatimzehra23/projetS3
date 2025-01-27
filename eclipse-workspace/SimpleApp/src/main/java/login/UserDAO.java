package login;

public interface UserDAO {
    UserBean authenticate(String email, String password);
}