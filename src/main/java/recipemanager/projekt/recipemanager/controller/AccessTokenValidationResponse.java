package recipemanager.projekt.recipemanager.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenValidationResponse {

    private boolean isValid;
    private String role;
}
