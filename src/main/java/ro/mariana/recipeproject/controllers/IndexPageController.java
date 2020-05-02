package ro.mariana.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.mariana.recipeproject.model.CategoryEntity;
import ro.mariana.recipeproject.model.UnitOfMeasureEntity;
import ro.mariana.recipeproject.repositories.CategoryRepository;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexPageController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexPageController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/"})
    public String getIndex(){

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasureEntity> optionalUnitOfMeasureEntity = unitOfMeasureRepository.findByDescription("cup");

        System.out.println("Category ID is:" + optionalCategoryEntity.get().getId());
        System.out.println("Uom ID is:" + optionalUnitOfMeasureEntity.get().getId());

        return "index";
    }
}
