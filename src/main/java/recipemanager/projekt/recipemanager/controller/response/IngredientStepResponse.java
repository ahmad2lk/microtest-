package recipemanager.projekt.recipemanager.controller.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientStepResponse {

    private Long id;


    private Integer quantity;


    private String unit;


    private String presentation;

    private Long stepId;

    private Long foodId;

}
