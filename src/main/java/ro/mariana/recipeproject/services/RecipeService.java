package ro.mariana.recipeproject.services;

import ro.mariana.recipeproject.dto.RecipeCommand;
import ro.mariana.recipeproject.model.RecipeEntity;

import java.util.Set;

public interface RecipeService {

    public Set<RecipeEntity> getRecipes();

    public RecipeEntity findById(Long id);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);
}
