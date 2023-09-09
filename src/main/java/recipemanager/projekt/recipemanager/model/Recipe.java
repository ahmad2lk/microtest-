package recipemanager.projekt.recipemanager.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "recipes",
        uniqueConstraints =
        @UniqueConstraint(name = "unique_designation_in_recipe_table",
                columnNames = "designation"))
public class Recipe  implements Serializable {


    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )
    @Column( name = "recipe_id",
            nullable = false
             )
    private Long id;


    @Column(name = "designation",
            nullable = false ,
            columnDefinition = "TEXT",
            unique = true  )
    private String name;

    private Double price;






}
