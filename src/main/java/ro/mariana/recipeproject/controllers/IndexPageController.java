package ro.mariana.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.mariana.recipeproject.services.RecipeService;

@Controller
public class IndexPageController {

    private final RecipeService recipeService;

    public IndexPageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/"})
    public String getIndex(Model model){
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
