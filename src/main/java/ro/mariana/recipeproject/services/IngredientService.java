package ro.mariana.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.mariana.recipeproject.converters.IngredientEntityToIngredientCommand;
import ro.mariana.recipeproject.dto.IngredientCommand;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.repositories.RecipeRepository;

import java.util.Optional;
@Slf4j
@Service
public class IngredientService {

    private final IngredientEntityToIngredientCommand converter;
    private final RecipeRepository recipeRepository;

    public IngredientService(IngredientEntityToIngredientCommand converter,
                             RecipeRepository recipeRepository) {
        this.converter = converter;
        this.recipeRepository = recipeRepository;
    }


    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<RecipeEntity> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            //todo implement error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        RecipeEntity recipeEntity = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipeEntity.getIngredients().stream()
                .filter(ingredient ->ingredient.getId().equals(ingredientId))
                .map(ingredient ->converter.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
