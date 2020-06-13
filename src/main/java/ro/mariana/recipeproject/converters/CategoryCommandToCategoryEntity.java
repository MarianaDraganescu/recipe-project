package ro.mariana.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.dto.CategoryCommand;
import ro.mariana.recipeproject.model.CategoryEntity;

@Component
public class CategoryCommandToCategoryEntity implements Converter<CategoryCommand, CategoryEntity> {


    @Override
    public CategoryEntity convert(CategoryCommand source) {
        if(source == null) {
            return null;
        }

        final CategoryEntity category = new CategoryEntity();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}
