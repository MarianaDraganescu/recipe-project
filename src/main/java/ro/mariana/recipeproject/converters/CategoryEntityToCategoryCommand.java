package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.CategoryCommand;
import ro.mariana.recipeproject.model.CategoryEntity;

@Component
public class CategoryEntityToCategoryCommand implements Converter<CategoryEntity, CategoryCommand> {


    @Override
    public CategoryCommand convert(CategoryEntity source) {
        if(source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;

    }
}
