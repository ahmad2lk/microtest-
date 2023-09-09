package recipemanager.projekt.recipemanager.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ingredientSteps")
public class IngredientStep  implements Serializable {

    @Id
    @SequenceGenerator(
            name = "ingredientStep_sequence",
            sequenceName = "ingredientStep_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ingredientStep_sequence"
    )
    @Column(name = "ingredientStep_id",
            nullable = false,
            updatable = false)
    private Long id;


    private Integer quantity;


    private String unit;

    @Column(name = "presentation")
    private String presentation;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    @JsonBackReference(value = "ingredient_ingredientSteps")
    private Ingredient ingredient;







    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @JsonBackReference(value = "ingredientStep_food")
    private Food food;

}