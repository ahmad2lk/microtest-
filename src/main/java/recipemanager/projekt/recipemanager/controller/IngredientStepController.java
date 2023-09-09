package recipemanager.projekt.recipemanager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import recipemanager.projekt.recipemanager.model.IngredientStep;
import recipemanager.projekt.recipemanager.service.IngredientStepService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredientStep")
//@PreAuthorize("hasRole('ADMIN')")
public class IngredientStepController {

    private final IngredientStepService ingredientStepService;


    public IngredientStepController(IngredientStepService ingredientStepService) {

        this.ingredientStepService = ingredientStepService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<IngredientStep>> getAllIngredientStep() {
        List<IngredientStep> ingredientSteps = ingredientStepService.findAllIngredientSteps();
        return new ResponseEntity<>(ingredientSteps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientStep> getIngredientStepById(@PathVariable("id") Long id) {
        IngredientStep ingredientStep = ingredientStepService.findIngredientStepById(id);
        return new ResponseEntity<>(ingredientStep, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<IngredientStep> addIngredientStep(@RequestBody IngredientStep ingredientStep) {


        IngredientStep newIngredientStep = ingredientStepService.addIngredientStep(ingredientStep);
        if (newIngredientStep != null) {
            return new ResponseEntity<>(newIngredientStep, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
