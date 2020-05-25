package ro.mariana.recipeproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryEntityTest {

    CategoryEntity category;

    @BeforeEach
    public void setUp(){
        category = new CategoryEntity();
    }

    @Test
    public void getId() {
        Long idValue = 1L;

        category.setId(idValue);

        assertEquals(idValue,category.getId());
    }

    @Test
    public void getRecipes() {
    }

    @Test
    public void getDescription() {
    }
}