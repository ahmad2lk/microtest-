package recipemanager.projekt.recipemanager.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import recipemanager.projekt.recipemanager.model.Food;
import recipemanager.projekt.recipemanager.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
//@PreAuthorize("hasRole('ADMIN')")
public class FoodController {


    private final FoodService foodService;

    public FoodController(FoodService foodService) {

        this.foodService = foodService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.findAllFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")

    public ResponseEntity<Food> getFoodById(@PathVariable("id") Long id) {
        Food food = foodService.findFoodById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Food> addFood(@RequestBody Food food) {

        Food newFood = foodService.addFood(food);

        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }


}
