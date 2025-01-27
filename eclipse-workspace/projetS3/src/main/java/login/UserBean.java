package login;

public class UserBean {
    private String email;
    private String password;
    private String role;

    // Constructeur par défaut
    public UserBean() {}

    // Constructeur avec paramètres
    public UserBean(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter pour email
    public String getEmail() {
        return email;
    }

    // Setter pour email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter pour password
    public String getPassword() {
        return password;
    }

    // Setter pour password
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Getter pour role
    public String getRole() {
        return role;
    }

    // Setter pour role
    public void setRole(String role) {
        this.role = role;
    }

    // Méthode toString pour afficher les informations de l'utilisateur
    @Override
    public String toString() {
        return "UserBean{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' + ", role='" + role + '\'' + 
                '}';
    }
}
