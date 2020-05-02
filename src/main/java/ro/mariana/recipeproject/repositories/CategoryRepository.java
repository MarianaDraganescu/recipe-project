package ro.mariana.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.recipeproject.model.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {

    Optional<CategoryEntity> findByDescription(String description);
}
