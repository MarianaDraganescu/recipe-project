package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.RecipeCommand;
import ro.mariana.recipeproject.model.RecipeEntity;

@Component
public class RecipeEntityToRecipeCommand implements Converter<RecipeEntity, RecipeCommand> {

    private final CategoryEntityToCategoryCommand categoryConverter;
    private final IngredientEntityToIngredientCommand ingredientConverter;
    private final NotesEntityToNotesCommand notesConverter;

    public RecipeEntityToRecipeCommand(CategoryEntityToCategoryCommand categoryConverter,
                                       IngredientEntityToIngredientCommand ingredientConverter,
                                       NotesEntityToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public RecipeCommand convert(RecipeEntity source) {
        if(source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirection(source.getDirection());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        if(source.getIngredients() != null && source.getIngredients().size() >0){
            source.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)) );
        }

        if(source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(category ->recipeCommand.getCategories().add(categoryConverter.convert(category)) );
        }

        return recipeCommand;
    }
}
