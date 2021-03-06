package ro.mariana.recipeproject.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.mariana.recipeproject.dto.RecipeCommand;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.services.RecipeServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    RecipeServiceImpl recipeService;

    MockMvc mockMvc;

    RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void getRecipeById_findRecipe_true() throws Exception {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setId(1L);

        when(recipeService.findById(any())).thenReturn(recipeEntity);

        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService,times(1)).findById(any());
    }

    @Test
    public void newRecipe_whenNewRecipeIsMade_showNewForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();

        mockMvc.perform(get("/recipes/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void saveOrUpdate_whenSavingNewRecipe_aNewFormAppears() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")

        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show/2"));
    }

    @Test
    public void updateRecipe_whenUpdatingArecipe_newFormAppears() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(post("/recipes/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));

        verify(recipeService).findCommandById(1L);
    }

    @Test
    public void deleteRecipe_whenDeletingArecipe_aRedirectToMainFormHappens() throws Exception {
        mockMvc.perform(get("/recipes/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService).deleteById(anyLong());
    }

}