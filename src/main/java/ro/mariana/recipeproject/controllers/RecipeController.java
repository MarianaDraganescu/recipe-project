package ro.mariana.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.mariana.recipeproject.dto.RecipeCommand;
import ro.mariana.recipeproject.services.RecipeServiceImpl;
@Controller
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes/{id}")
    public String getRecipeById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipes/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeform";
    }

    //se recomanda a se folosi doar substantive nu si verbe in link,si subs la plural

    @RequestMapping("/recipes/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipes")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/show/" + savedCommand.getId();

    }
}
