package recipemanager.projekt.recipemanager.controller.request;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Step {


    private Long id;
    private Long recipeId;
}
