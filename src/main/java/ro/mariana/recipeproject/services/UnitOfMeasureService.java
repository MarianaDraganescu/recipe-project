package ro.mariana.recipeproject.services;

import org.springframework.stereotype.Service;
import ro.mariana.recipeproject.converters.UnitOfMeasureEntityToUnitOfMeasureCommand;
import ro.mariana.recipeproject.dto.UnitOfMeasureCommand;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureEntityToUnitOfMeasureCommand unitOfMeasureCommand;

    public UnitOfMeasureService(UnitOfMeasureRepository unitOfMeasureRepository,
                                UnitOfMeasureEntityToUnitOfMeasureCommand unitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureCommand = unitOfMeasureCommand;
    }

    public Set<UnitOfMeasureCommand> listAllUoms() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(unitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
