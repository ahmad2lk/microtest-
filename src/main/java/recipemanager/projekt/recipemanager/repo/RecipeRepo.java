package recipemanager.projekt.recipemanager.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipemanager.projekt.recipemanager.model.Recipe;


import java.util.Optional;

@Repository
public interface RecipeRepo extends JpaRepository <Recipe,Long > {

    @Transactional
    void deleteRecipeById(Long id);

    Optional<Recipe> findRecipeById(Long id );


    Optional<Recipe> findByName (String name );

}
