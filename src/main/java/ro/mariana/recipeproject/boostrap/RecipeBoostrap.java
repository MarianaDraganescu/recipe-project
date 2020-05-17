package ro.mariana.recipeproject.boostrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ro.mariana.recipeproject.model.*;
import ro.mariana.recipeproject.repositories.CategoryRepository;
import ro.mariana.recipeproject.repositories.RecipeRepository;
import ro.mariana.recipeproject.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class RecipeBoostrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBoostrap(CategoryRepository categoryRepository,
                          RecipeRepository recipeRepository,
                          UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");

    }

    private List<RecipeEntity> getRecipes(){

        List<RecipeEntity> recipes = new ArrayList<>();

        //get Uom
        Optional<UnitOfMeasureEntity> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        Optional<UnitOfMeasureEntity> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected Uom not found!");
        }

        //get optionals
        UnitOfMeasureEntity eachUom = eachUomOptional.get();
        UnitOfMeasureEntity teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasureEntity tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasureEntity pintUom = pintUomOptional.get();
        UnitOfMeasureEntity pinchUom = pinchUomOptional.get();
        UnitOfMeasureEntity dashUom = dashUomOptional.get();
        UnitOfMeasureEntity ounceUom = ounceUomOptional.get();
        UnitOfMeasureEntity cupUom = cupUomOptional.get();


        //get categories
        Optional<CategoryEntity> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("No category found!");
        }

        Optional<CategoryEntity> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("No category found!");
        }

        CategoryEntity americanCategory = americanCategoryOptional.get();
        CategoryEntity mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac--->Recipe nr.1
        RecipeEntity guacRecipe = new RecipeEntity();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirection("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        NotesEntity guacNotes = new NotesEntity();
        guacNotes.setNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new IngredientEntity("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new IngredientEntity("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        guacRecipe.addIngredient(new IngredientEntity("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new IngredientEntity("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new IngredientEntity("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new IngredientEntity("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new IngredientEntity("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new IngredientEntity("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        recipes.add(guacRecipe);

        //Yummy Tacos
        RecipeEntity tacosRecipe = new RecipeEntity();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirection("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        NotesEntity tacoNotes = new NotesEntity();
        tacoNotes.setNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacosRecipe.setNotes(tacoNotes);


        tacosRecipe.addIngredient(new IngredientEntity("Ancho Chili Powder", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("Sugar", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("Salt", new BigDecimal(".5"), teaspoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new IngredientEntity("finely grated orange zestr", new BigDecimal(1), tablespoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("boneless chicken thighs", new BigDecimal(4), tablespoonUom));
        tacosRecipe.addIngredient(new IngredientEntity("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new IngredientEntity("packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new IngredientEntity("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new IngredientEntity("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new IngredientEntity("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipe.addIngredient(new IngredientEntity("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new IngredientEntity("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new IngredientEntity("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new IngredientEntity("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;
    }

}
