package ro.mariana.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.mariana.recipeproject.converters.IngredientCommandToIngredientEntity;
import ro.mariana.recipeproject.converters.IngredientEntityToIngredientCommand;
import ro.mariana.recipeproject.dto.IngredientCommand;
import ro.mariana.recipeproject.model.IngredientEntity;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.repositories.RecipeRepository;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

import java.util.Optional;
@Slf4j
@Service
public class IngredientService {

    private final IngredientEntityToIngredientCommand converter;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredientEntity ingredientCommandToIngredientEntity;

    public IngredientService(IngredientEntityToIngredientCommand converter,
                             RecipeRepository recipeRepository,
                             UnitOfMeasureRepository unitOfMeasureRepository,
                             IngredientCommandToIngredientEntity ingredientCommandToIngredientEntity) {
        this.converter = converter;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredientEntity = ingredientCommandToIngredientEntity;
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

    @Transactional
    public IngredientCommand saveOrUpdateIngredient(IngredientCommand command){
        //get the recipe from repository with the id camed from UI
        Optional<RecipeEntity> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            RecipeEntity recipe = recipeOptional.get();

            //get ingredient from recipe from repository
            Optional<IngredientEntity> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()) )
                    .findFirst();

            if(ingredientOptional.isPresent()){
                //replace  repository properties with properties from UI (UPDATE)
                IngredientEntity ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUom().getId())
                                .orElseThrow(()-> new RuntimeException("UOM not found"))); //todo address this
            } else {
                //add new ingredient (SAVE) to recipe
                IngredientEntity ingredientEntity = new IngredientEntity();
                ingredientEntity.setRecipe(recipe);
                recipe.addIngredient(ingredientEntity);
            }

            RecipeEntity savedRecipe = recipeRepository.save(recipe);

            Optional<IngredientEntity> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //to do check for fail
            return converter.convert(savedIngredientOptional.get());
        }
    }
}
