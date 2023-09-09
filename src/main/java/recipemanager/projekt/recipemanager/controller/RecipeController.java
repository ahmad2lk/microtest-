package recipemanager.projekt.recipemanager.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import recipemanager.projekt.recipemanager.controller.request.RecipeResponse;
import recipemanager.projekt.recipemanager.controller.request.Step;
import recipemanager.projekt.recipemanager.model.Recipe;
import recipemanager.projekt.recipemanager.service.RecipeService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/recipe")
//@PreAuthorize("hasAnyRole('ADMIN' ,'USER')")
public class RecipeController {


    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;

    }




    @GetMapping("/all")
    //@PreAuthorize("hasRole('ADMIN' )")
    @PreAuthorize("@customAuthorizationService.hasAdminRole(principal) or @customAuthorizationService.hasUserRole(principal)")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        log.info("Received fetching  request");
        List<Recipe> recipes = recipeService.findAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('admin:read', 'user:read')")
    @PreAuthorize("@customAuthorizationService.hasAdminRole(principal) or @customAuthorizationService.hasUserRole(principal)")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") Long id) {
        log.info("Received fetching  request");
        Recipe recipe = recipeService.findRecipesById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }


    @PostMapping("/add")
    @PreAuthorize("@customAuthorizationService.hasAdminRole(principal)")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {

            Recipe newRecipe = recipeService.addRecipe(recipe);
            return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }



    @PutMapping("/update")
    //@PreAuthorize("hasAuthority('admin:update')")
    @PreAuthorize("@customAuthorizationService.hasAdminRole(principal)")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateRecipe(recipe);
        log.info("Received update  request");
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    //@PreAuthorize("hasAuthority('admin:delete')")
    @PreAuthorize("@customAuthorizationService.hasAdminRole(principal)")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") Long recipeId,
                                               @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        log.info("Received update  request");
        recipeService.deleteRecipe(jwtToken,recipeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/with-steps/{recipe-id}")
    @PreAuthorize("@customAuthorizationService.hasAdminRole(principal) or @customAuthorizationService.hasUserRole(principal)")
    public ResponseEntity<RecipeResponse> findAllSteps( @PathVariable("recipe-id") Long recipeId,
                                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {

        RecipeResponse response = recipeService.findRecipeWithSteps(jwtToken, recipeId);
        return ResponseEntity.ok(response);
    }
    }

