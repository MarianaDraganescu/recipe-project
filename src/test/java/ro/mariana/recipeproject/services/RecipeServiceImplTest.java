package ro.mariana.recipeproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        RecipeEntity recipe = new RecipeEntity();
        Set<RecipeEntity> recipesSet = new HashSet<>();
        recipesSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesSet);

        Set<RecipeEntity> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void findRecipeById(){
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(1L);

        when(recipeRepository.findById(1L)).thenReturn(java.util.Optional.of(recipeEntity));

        RecipeEntity recipeFound = recipeService.findById(1L);

        assertEquals(1L,recipeFound.getId());
        assertNotNull(recipeFound);
        verify(recipeRepository,times(1)).findById(any());
    }


    //TODO add test to verify throwing the exception
}