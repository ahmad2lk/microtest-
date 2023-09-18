package recipemanager.projekt.recipemanager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipemanager.projekt.recipemanager.client.IngredientCleint;
import recipemanager.projekt.recipemanager.client.StepClient;
import recipemanager.projekt.recipemanager.controller.response.FoodResponse;
import recipemanager.projekt.recipemanager.controller.response.IngredientResponse;
import recipemanager.projekt.recipemanager.controller.response.RecipeResponse;
import recipemanager.projekt.recipemanager.controller.response.Step;
import recipemanager.projekt.recipemanager.exception.RecipeNotFoundException;
import recipemanager.projekt.recipemanager.model.Recipe;
import recipemanager.projekt.recipemanager.repo.RecipeRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepo recipeRepo;
    private final ObjectMapper objectMapper;
    private final StepClient stepclient;
    private final IngredientCleint ingredientClient;


    public Recipe addRecipe(Recipe recipe) {

        return recipeRepo.save(recipe);
    }


    public List<RecipeResponse> findAllRecipes(String jwtToken) {
        List<Recipe> recipeList = recipeRepo.findAll();

        Map<Long, List<Step>> stepsMap = stepclient.findAllSteps(jwtToken)
                .stream()
                .collect(Collectors.groupingBy(Step::getRecipeId));

        Map<Long, List<IngredientResponse>> ingredientsMap = ingredientClient.findAllIngredient(jwtToken)
                .stream()
                .collect(Collectors.groupingBy(IngredientResponse::getRecipeId));



        return recipeList.parallelStream().map(recipe -> {
            RecipeResponse recipeResponse = new RecipeResponse();
            Long recipeId = recipe.getId();

            recipeResponse.setId(recipeId);
            recipeResponse.setName(recipe.getName());
            recipeResponse.setPrice(recipe.getPrice());

            // Retrieve associated steps and ingredients from the maps
            List<Step> stepList = stepsMap.getOrDefault(recipeId, Collections.emptyList());
            List<IngredientResponse> ingredientList = ingredientsMap.getOrDefault(recipeId, Collections.emptyList());



            recipeResponse.setSteps(stepList);
            recipeResponse.setIngredients(ingredientList);

            return recipeResponse;
        }).collect(Collectors.toList());
    }


    public Recipe findRecipesById(Long id) {
        return recipeRepo.findRecipeById(id).orElseThrow(()
                -> new RecipeNotFoundException("Recipe by id " + id + "was not found "));
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }


    @Transactional
    public void deleteRecipe(String jwtToken, Long recipeId) {

        ingredientClient.DeleteAllIngredientsByRecipe(jwtToken, recipeId);
        stepclient.DeleteAllStepsByRecipe(jwtToken, recipeId);

        recipeRepo.deleteRecipeById(recipeId);
    }


    public RecipeResponse findRecipeWithSteps(String jwtToken, Long recipeId) {

        var recipe = recipeRepo.findById(recipeId)
                .orElse(
                        Recipe.builder()

                                .name("NOT_FOUND")
                                .price(0.0)
                                .build()
                );

        var steps = stepclient.findAllStepsByRecipe(jwtToken, recipeId);
        var ingredients = ingredientClient.findAllIngredientsByRecipe(jwtToken, recipeId);
        return RecipeResponse.builder()
                .name(recipe.getName())
                .price(recipe.getPrice())
                .steps(steps)
                .ingredients(ingredients)
                .build();
    }


    public List<RecipeResponse> getAllRecipes(String jwtToken) {


        List<Recipe> recipeList = recipeRepo.findAll();
        if (recipeList.isEmpty()) {
            Recipe.builder()

                    .name("NOT_FOUND")
                    .price(0.0)
                    .build();
            return Collections.emptyList();
        }


        return recipeList.parallelStream().map(recipe -> {
            Long recipeId = recipe.getId();
            List<Step> stepList = stepclient.findAllStepsByRecipe(jwtToken, recipeId);
            List<IngredientResponse> ingredients = ingredientClient.findAllIngredientsByRecipe(jwtToken, recipeId);



            RecipeResponse recipeResponse = new RecipeResponse();



            recipeResponse.setId(recipe.getId());
            recipeResponse.setName(recipe.getName());
            recipeResponse.setPrice(recipe.getPrice());
            if (!stepList.isEmpty()) {
                recipeResponse.setSteps(stepList);
            } else {

                recipeResponse.setSteps(null);
            }
            recipeResponse.setIngredients(ingredients);

            if (!ingredients.isEmpty()) {
                recipeResponse.setIngredients(ingredients);
            } else {

                recipeResponse.setIngredients(null);
            }

            return recipeResponse;
        }).collect(Collectors.toList());
    }
}








