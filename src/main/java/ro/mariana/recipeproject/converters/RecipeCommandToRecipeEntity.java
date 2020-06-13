package ro.mariana.recipeproject.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.RecipeCommand;
import ro.mariana.recipeproject.model.RecipeEntity;

@Component
public class RecipeCommandToRecipeEntity implements Converter<RecipeCommand, RecipeEntity> {

    private final CategoryCommandToCategoryEntity categoryConverter;
    private final IngredientCommandToIngredientEntity ingredientConverter;
    private final NotesCommandToNotesEntity notesConverter;

    public RecipeCommandToRecipeEntity(CategoryCommandToCategoryEntity categoryConverter,
                                       IngredientCommandToIngredientEntity ingredientConverter,
                                       NotesCommandToNotesEntity notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }


    @Override
    public RecipeEntity convert(RecipeCommand source) {

        if (source == null) {
            return null;
        }

        final RecipeEntity recipe = new RecipeEntity();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirection(source.getDirection());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        if(source.getCategories() != null && source.getCategories().size() >0){
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)) );
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)) );
        }

        return recipe;

    }
}
