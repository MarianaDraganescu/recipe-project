package ro.mariana.recipeproject.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ro.mariana.recipeproject.services.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IndexPageControllerTest {

    IndexPageController indexPageController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        indexPageController = new IndexPageController(recipeService);
    }

    @Test
    public void getIndex() {

        String stringIndex = indexPageController.getIndex(model);

        assertEquals("index",stringIndex);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),anySet());
    }
}