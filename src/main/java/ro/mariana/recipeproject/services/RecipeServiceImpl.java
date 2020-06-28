package ro.mariana.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.mariana.recipeproject.converters.RecipeCommandToRecipeEntity;
import ro.mariana.recipeproject.converters.RecipeEntityToRecipeCommand;
import ro.mariana.recipeproject.dto.RecipeCommand;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipeEntity recipeCommandToRecipeEntity;
    private final RecipeEntityToRecipeCommand recipeEntityToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipeEntity recipeCommandToRecipeEntity,
                             RecipeEntityToRecipeCommand recipeEntityToRecipeCommand) {

        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipeEntity = recipeCommandToRecipeEntity;
        this.recipeEntityToRecipeCommand = recipeEntityToRecipeCommand;
    }


    @Override
    public Set<RecipeEntity> getRecipes() {
        log.debug("I'm in the service");

        Set<RecipeEntity> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public RecipeEntity findById(Long id) {

        Optional<RecipeEntity> optionalRecipe = recipeRepository.findById(id);

        if(!optionalRecipe.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }

        return optionalRecipe.get();
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        RecipeEntity convertedRecipe = recipeCommandToRecipeEntity.convert(command);

        RecipeEntity savedRecipe = recipeRepository.save(convertedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());


        return recipeEntityToRecipeCommand.convert(savedRecipe);
    }

    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeEntityToRecipeCommand.convert(findById(id)) ;
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
