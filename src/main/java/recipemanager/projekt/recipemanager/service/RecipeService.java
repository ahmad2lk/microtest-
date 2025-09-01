package recipemanager.projekt.recipemanager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipemanager.projekt.recipemanager.client.IngredientCleint;
import recipemanager.projekt.recipemanager.client.StepClient;
import recipemanager.projekt.recipemanager.controller.response.IngredientResponse;
import recipemanager.projekt.recipemanager.controller.response.RecipeResponse;
import recipemanager.projekt.recipemanager.controller.response.Step;
import recipemanager.projekt.recipemanager.exception.RecipeNotFoundException;
import recipemanager.projekt.recipemanager.model.Recipe;
import recipemanager.projekt.recipemanager.repo.RecipeRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepo recipeRepo;
    private final StepClient stepclient;
    private final IngredientCleint ingredientClient;


    public Recipe addRecipe(Recipe recipe) {

        return recipeRepo.save(recipe);
    }


    public List<RecipeResponse> findAllRecipes(String jwtToken) {

        List<Recipe> recipeList = recipeRepo.findAll();
        List<Step> allSteps = stepclient.findAllSteps(jwtToken);
        List<IngredientResponse> allIngredients = ingredientClient.findAllIngredient(jwtToken);

        List<RecipeResponse> recipeResponses = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            RecipeResponse recipeResponse = new RecipeResponse();
            Long recipeId = recipe.getId();

            recipeResponse.setId(recipeId);
            recipeResponse.setName(recipe.getName());
            recipeResponse.setPrice(recipe.getPrice());

            List<Step> stepList = new ArrayList<>();
            List<IngredientResponse> ingredientList = new ArrayList<>();

            for (Step step : allSteps) {
                if (step.getRecipeId().equals(recipeId)) {
                    stepList.add(step);
                }
            }

           for (IngredientResponse ingredient : allIngredients) {
                if (ingredient.getRecipeId().equals(recipeId)) {
                    ingredientList.add(ingredient);
                }
            }

            recipeResponse.setSteps(stepList);
            recipeResponse.setIngredients(ingredientList);

            recipeResponses.add(recipeResponse);
        }

        return recipeResponses;
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








