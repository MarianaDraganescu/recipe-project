package ro.mariana.recipeproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.mariana.recipeproject.converters.IngredientCommandToIngredientEntity;
import ro.mariana.recipeproject.converters.IngredientEntityToIngredientCommand;
import ro.mariana.recipeproject.model.RecipeEntity;
import ro.mariana.recipeproject.repositories.RecipeRepository;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

public class IngredientServiceTest {

    @Mock
    private IngredientEntityToIngredientCommand ingredientEntityToIngredientCommand;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    private IngredientCommandToIngredientEntity ingredientCommandToIngredientEntity;

    @Mock
    private RecipeEntity recipeEntity;

    private IngredientService ingredientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientService(ingredientEntityToIngredientCommand,
                recipeRepository, unitOfMeasureRepository,
                ingredientCommandToIngredientEntity);
    }
}