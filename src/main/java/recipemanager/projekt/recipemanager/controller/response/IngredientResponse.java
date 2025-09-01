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
public class IngredientResponse {

    private Long id;

    private Integer quantity;

    private String unit;

    private Long recipeId;

    private FoodResponse food ;



}
