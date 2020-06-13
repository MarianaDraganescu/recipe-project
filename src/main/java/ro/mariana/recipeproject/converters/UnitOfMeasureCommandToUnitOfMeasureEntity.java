package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.UnitOfMeasureCommand;
import ro.mariana.recipeproject.model.UnitOfMeasureEntity;

@Component
public class UnitOfMeasureCommandToUnitOfMeasureEntity implements Converter<UnitOfMeasureCommand, UnitOfMeasureEntity> {

    @Override
    public UnitOfMeasureEntity convert(UnitOfMeasureCommand source) {
        if(source == null) {
            return null;
        }

        final UnitOfMeasureEntity unitOfMeasureEntity = new UnitOfMeasureEntity();
        unitOfMeasureEntity.setId(source.getId());
        unitOfMeasureEntity.setDescription(source.getDescription());

        return unitOfMeasureEntity;
    }
}
