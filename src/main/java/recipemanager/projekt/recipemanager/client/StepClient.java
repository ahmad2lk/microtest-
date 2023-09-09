package recipemanager.projekt.recipemanager.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import recipemanager.projekt.recipemanager.controller.request.Step;

import java.util.List;

@FeignClient(name = "step-service", url = "${application.config.steps-url}")
public interface StepClient {


    @GetMapping("/recipe/{recipe-id}")
   List<Step> findAllStepsByRecipe(@RequestHeader("Authorization") String jwtToken,@PathVariable("recipe-id") Long recipeId);


    @DeleteMapping("/{recipe-id}")
    void DeleteAllStepsByRecipe(@RequestHeader("Authorization") String jwtToken,@PathVariable("recipe-id") Long recipeId);

}
