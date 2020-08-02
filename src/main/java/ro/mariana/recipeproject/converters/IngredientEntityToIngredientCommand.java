package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.IngredientCommand;
import ro.mariana.recipeproject.model.IngredientEntity;

@Component
public class IngredientEntityToIngredientCommand implements Converter<IngredientEntity, IngredientCommand> {

    private final UnitOfMeasureEntityToUnitOfMeasureCommand unitOfMeasureConverter;

    public IngredientEntityToIngredientCommand(UnitOfMeasureEntityToUnitOfMeasureCommand unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Override
    public IngredientCommand convert(IngredientEntity source) {
        if(source == null) {
            return null;
        }

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        if(source.getRecipe() != null){
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setUom(unitOfMeasureConverter.convert(source.getUnitOfMeasure()));

        return ingredientCommand;
    }
}
