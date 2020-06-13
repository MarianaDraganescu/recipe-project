package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.IngredientCommand;
import ro.mariana.recipeproject.model.IngredientEntity;

@Component
public class IngredientCommandToIngredientEntity implements Converter<IngredientCommand, IngredientEntity> {

    private final UnitOfMeasureCommandToUnitOfMeasureEntity unitOfMeasureConverter;

    public IngredientCommandToIngredientEntity(UnitOfMeasureCommandToUnitOfMeasureEntity unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Override
    public IngredientEntity convert(IngredientCommand source) {
        if(source == null) {
            return null;
        }

        final IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(source.getId());
        ingredientEntity.setDescription(source.getDescription());
        ingredientEntity.setAmount(source.getAmount());
        ingredientEntity.setUnitOfMeasure(unitOfMeasureConverter.convert(source.getUom()));

        return ingredientEntity;

    }
}
