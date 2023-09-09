package recipemanager.projekt.recipemanager.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {


    @Id
    @SequenceGenerator(
            name = "ingredient_sequence",
            sequenceName = "ingredient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ingredient_sequence"
    )
    @Column(name = "ingredient_id",
            nullable = false,
            updatable = false)
    private Long id;


    private Integer quantity;


    private String unit;






    @JsonManagedReference(value = "ingredient_ingredientSteps")
    @OneToMany(mappedBy = "ingredient")
    private List<IngredientStep> ingredientSteps;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @JsonBackReference(value = "ingredients_food")
    private Food food;


}