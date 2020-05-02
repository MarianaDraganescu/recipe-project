package ro.mariana.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.recipeproject.model.UnitOfMeasureEntity;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasureEntity,Long> {

    Optional<UnitOfMeasureEntity> findByDescription(String description);
}
