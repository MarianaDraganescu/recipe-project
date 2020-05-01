package ro.mariana.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.recipeproject.model.RecipeEntity;

public interface RecipeRepository extends CrudRepository<RecipeEntity,Long> {
}
