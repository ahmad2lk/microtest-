package recipemanager.projekt.recipemanager.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationResponse {

    private boolean isValid;
    private String role;
}
