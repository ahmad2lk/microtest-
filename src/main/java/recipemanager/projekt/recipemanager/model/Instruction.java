package recipemanager.projekt.recipemanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "instructions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "instruction_type",
        discriminatorType = DiscriminatorType.STRING)
public class Instruction implements Serializable {

    @Id
    @SequenceGenerator(
            name = "instruction_sequence",
            sequenceName = "instruction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "instruction_sequence"
    )
    @Column( name = "instruction_id",
            nullable = false ,
            updatable = false )
    private Long id;

    @Column( name = "description",
            nullable = false)
            private String description;





}
