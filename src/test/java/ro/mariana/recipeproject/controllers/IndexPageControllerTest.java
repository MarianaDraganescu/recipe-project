package ro.mariana.recipeproject.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexPageControllerTest {

    private IndexPageController indexPageController;

    private MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexPageController = new IndexPageController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(indexPageController).build();
    }

    @Test
    public void testMockMVC() throws Exception {
        when(recipeService.getRecipes()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndex() {
        //given
        Set<RecipeEntity> recipes = new HashSet<>();
        recipes.add(new RecipeEntity());

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(1L);

        recipes.add(recipeEntity);

        when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<RecipeEntity>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String stringIndex = indexPageController.getIndex(model);


        //then
        assertEquals("index",stringIndex);
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        assertEquals(2,argumentCaptor.getValue().size());
    }
}