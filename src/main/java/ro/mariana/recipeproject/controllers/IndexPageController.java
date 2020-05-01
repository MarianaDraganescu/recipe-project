package ro.mariana.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {

    @RequestMapping({"","/"})
    public String getIndex(){

        return "index";
    }
}
