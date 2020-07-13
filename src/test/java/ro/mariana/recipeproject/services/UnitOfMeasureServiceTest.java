package ro.mariana.recipeproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.mariana.recipeproject.converters.UnitOfMeasureEntityToUnitOfMeasureCommand;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceTest {

    private UnitOfMeasureService unitOfMeasureService;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    private UnitOfMeasureEntityToUnitOfMeasureCommand unitOfMeasureCommand;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureService(unitOfMeasureRepository,unitOfMeasureCommand);

    }

    @Test
    public void listAllUoms() {
    }
}