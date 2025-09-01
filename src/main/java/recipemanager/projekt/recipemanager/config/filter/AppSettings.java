package recipemanager.projekt.recipemanager.config.filter;

import org.springframework.stereotype.Service;

@Service
public class AppSettings {
    private String appRole;

    public void setAppRole(String role) {
        this.appRole = role;
    }

    public String getAppRole() {
        return appRole;
    }
}