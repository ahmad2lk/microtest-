package recipemanager.projekt.recipemanager.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import recipemanager.projekt.recipemanager.controller.response.IngredientResponse;


import java.util.List;

@FeignClient(name = "ingredient-service", url = "${application.config.ingredients-url}")
public interface IngredientCleint {


    @GetMapping("recipe/{recipe-id}")
    List<IngredientResponse> findAllIngredientsByRecipe(@RequestHeader("Authorization") String jwtToken, @PathVariable("recipe-id") Long recipeId);



    @GetMapping("/all")
    List<IngredientResponse> findAllIngredient(@RequestHeader("Authorization") String jwtToken);

    @DeleteMapping("/{recipe-id}")
    void DeleteAllIngredientsByRecipe(@RequestHeader("Authorization") String jwtToken,@PathVariable("recipe-id") Long recipeId);



}
