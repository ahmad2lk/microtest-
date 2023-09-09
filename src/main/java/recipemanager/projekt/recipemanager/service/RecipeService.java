package recipemanager.projekt.recipemanager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recipemanager.projekt.recipemanager.client.StepClient;
import recipemanager.projekt.recipemanager.controller.request.RecipeResponse;
import recipemanager.projekt.recipemanager.exception.RecipeNotFoundException;
import recipemanager.projekt.recipemanager.model.Recipe;
import recipemanager.projekt.recipemanager.repo.RecipeRepo;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepo recipeRepo;
    private final ObjectMapper objectMapper;
    private final StepClient client;





        public Recipe addRecipe(Recipe recipe) {

            return recipeRepo.save(recipe);
        }


        public List<Recipe> findAllRecipes() {

            return recipeRepo.findAll().stream().toList();
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


            client.DeleteAllStepsByRecipe(jwtToken,recipeId);

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

            var steps = client.findAllStepsByRecipe(jwtToken,recipeId);
            return RecipeResponse.builder()
                    .name(recipe.getName())
                    .price(recipe.getPrice())
                    .steps(steps)
                    .build();
        }


    }





