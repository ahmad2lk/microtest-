package recipemanager.projekt.recipemanager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import recipemanager.projekt.recipemanager.model.Ingredient;
import recipemanager.projekt.recipemanager.service.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ingredient")
//@PreAuthorize("hasRole('ADMIN')")
public class IngredientController {


    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){
        this.ingredientService= ingredientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.findAllIngredients()
                .stream()
                .collect(Collectors.toList());
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.findIngredientById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        if (ingredient.getQuantity() == null) {
            return new ResponseEntity<>(ingredient, HttpStatus.BAD_REQUEST);
        }

        Ingredient newIngredient = ingredientService.addIngredient(ingredient);
        return new ResponseEntity<>(newIngredient, HttpStatus.CREATED);
    }



}
