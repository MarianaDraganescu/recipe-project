package ro.mariana.recipeproject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.mariana.recipeproject.converters.UnitOfMeasureEntityToUnitOfMeasureCommand;
import ro.mariana.recipeproject.dto.UnitOfMeasureCommand;
import ro.mariana.recipeproject.model.UnitOfMeasureEntity;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceTest {

    private UnitOfMeasureService unitOfMeasureService;

    private UnitOfMeasureEntityToUnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureEntityToUnitOfMeasureCommand();

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureService(unitOfMeasureRepository,unitOfMeasureCommand);

    }

    @Test
    public void listAllUoms() {
            Set<UnitOfMeasureEntity> unitOfMeasureEntitySet = new HashSet<>();
            UnitOfMeasureEntity uom1 = new UnitOfMeasureEntity();
            uom1.setId(1L);
            unitOfMeasureEntitySet.add(uom1);

            UnitOfMeasureEntity uom2 = new UnitOfMeasureEntity();
            uom2.setId(2L);
            unitOfMeasureEntitySet.add(uom2);

            when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureEntitySet);

            Set<UnitOfMeasureCommand> uomsCommands = unitOfMeasureService.listAllUoms();

            assertEquals(2,uomsCommands.size());
            verify(unitOfMeasureRepository).findAll();
    }
}