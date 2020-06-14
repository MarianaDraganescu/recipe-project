package ro.mariana.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.mariana.recipeproject.dto.CategoryCommand;
import ro.mariana.recipeproject.model.CategoryEntity;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityToCategoryCommandTest {

    private final Long ID_VALUE = 2L;
    private final String DESCRIPTION = "description";
    private CategoryEntityToCategoryCommand categoryCommandConverter;

    @BeforeEach
    public void setUp(){
        categoryCommandConverter = new CategoryEntityToCategoryCommand();

    }

    @Test
    public void convertNullObject() {

        CategoryCommand convertedNullObject = categoryCommandConverter.convert(null);

        assertNull(convertedNullObject);
    }

    @Test
    public void convertEmptyObject(){

        CategoryCommand convertedEmptyObject = categoryCommandConverter.convert(new CategoryEntity());

        assertNotNull(convertedEmptyObject);
    }

    @Test
    public void convertPropertiesForObject(){

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(ID_VALUE);
        categoryEntity.setDescription(DESCRIPTION);

        CategoryCommand convertedObject = categoryCommandConverter.convert(categoryEntity);

        assertEquals(ID_VALUE,convertedObject.getId());
        assertEquals(DESCRIPTION,convertedObject.getDescription());

    }
}