package recipemanager.projekt.recipemanager.config.filter;



import org.springframework.stereotype.Service;

@Service("authorizationService")
public class AuthorizationService {

    private String userRole;

    public void setRole(String role) {
        this.userRole = role;
    }

    public String getUserRole() {
        return userRole;
    }
}
