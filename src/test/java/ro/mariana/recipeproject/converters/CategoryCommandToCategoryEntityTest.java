package ro.mariana.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.mariana.recipeproject.dto.CategoryCommand;
import ro.mariana.recipeproject.model.CategoryEntity;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryEntityTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private CategoryCommandToCategoryEntity converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategoryEntity();
    }


    //check for null object
    @Test
    public void convertNullObject() {

        CategoryEntity convertedObject = converter.convert(null);

        assertNull(convertedObject);

    }

    //check for empty object
    @Test
    public void convertEmptyObject(){

        CategoryEntity convertedObject = converter.convert(new CategoryCommand());

        assertNotNull(convertedObject);
    }

    //check for properties set on object
    @Test
    public void convertPropertiesForObject(){

        //arrange
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //act
        CategoryEntity objectConverted = converter.convert(categoryCommand);

        //assert

        assertEquals(ID_VALUE,objectConverted.getId());
        assertEquals(DESCRIPTION,objectConverted.getDescription());

    }


}