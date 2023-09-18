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
public class RecipeResponse {


    private Long id;
    private String name;

    private Double price;

    private List<Step> steps;

    private List<IngredientResponse> ingredients;


}
