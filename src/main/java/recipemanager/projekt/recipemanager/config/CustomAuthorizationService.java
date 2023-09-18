package recipemanager.projekt.recipemanager.config;


import org.springframework.stereotype.Service;

@Service
public class CustomAuthorizationService {

    public boolean hasAdminRole(TokenValidationResponse accessTokenValidationResponse) {

        return accessTokenValidationResponse != null && "ADMIN".equals(accessTokenValidationResponse.getRole());
    }

    public boolean hasUserRole(TokenValidationResponse accessTokenValidationResponse) {

        return accessTokenValidationResponse != null && "USER".equals(accessTokenValidationResponse.getRole());
    }
}
