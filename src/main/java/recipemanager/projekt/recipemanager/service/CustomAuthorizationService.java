package recipemanager.projekt.recipemanager.service;

import org.springframework.stereotype.Service;
import recipemanager.projekt.recipemanager.controller.AccessTokenValidationResponse;

@Service
public class CustomAuthorizationService {

    public boolean hasAdminRole(AccessTokenValidationResponse accessTokenValidationResponse) {

        return accessTokenValidationResponse != null && "ADMIN".equals(accessTokenValidationResponse.getRole());
    }

    public boolean hasUserRole(AccessTokenValidationResponse accessTokenValidationResponse) {

        return accessTokenValidationResponse != null && "USER".equals(accessTokenValidationResponse.getRole());
    }
}
