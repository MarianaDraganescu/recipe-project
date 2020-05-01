package ro.mariana.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.recipeproject.model.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {
}
