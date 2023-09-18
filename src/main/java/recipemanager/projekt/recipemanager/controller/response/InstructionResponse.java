package recipemanager.projekt.recipemanager.controller.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructionResponse {


    private Long id;

    private String description;

    private String type;


    private Integer duration;

    private Integer    mixingLevel;

    private Integer  temperature;


    private Long  stepId;
}
