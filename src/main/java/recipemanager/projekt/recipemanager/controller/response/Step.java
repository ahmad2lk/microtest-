package recipemanager.projekt.recipemanager.controller.response;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Step {


    private Long id;
    private Long recipeId;
    private List<InstructionResponse> instructions;
    private List<IngredientStepResponse> ingredientSteps;
}
