package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.UnitOfMeasureCommand;
import ro.mariana.recipeproject.model.UnitOfMeasureEntity;

@Component
public class UnitOfMeasureEntityToUnitOfMeasureCommand implements Converter<UnitOfMeasureEntity, UnitOfMeasureCommand> {

    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasureEntity source) {
        if(source == null) {
            return null;
        }

        final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(source.getId());
        unitOfMeasureCommand.setDescription(source.getDescription());

        return unitOfMeasureCommand;
    }
}
