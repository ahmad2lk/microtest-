package recipemanager.projekt.recipemanager.controller.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse {

    private Long id;

    private String consistency;
    private String unit;
    private List<IngredientStepResponse> ingredientStepResponses;
}
