package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();


//  Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

//        cheeses.put("cheddar", "Yay! I did it!");

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    // Request path: /cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");

        return "cheese/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
        Cheese newCheese = new Cheese(cheeseName, cheeseDescription);
        cheeses.add(newCheese);

        // Redirect to /cheese
        return "redirect:";
    }

    // Request path: /cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {

        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeseNames", cheeses);

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam String cheeseName) {

        // Search ArrayList for cheese object and remove it
        for (int i = 0; i < cheeses.size(); i++) {
            Cheese singleCheese = cheeses.get(i);
            if (singleCheese.getName().contains(cheeseName)) {
                cheeses.remove(i);
                break;
            }
        }

        // Redirect to /cheese
        return "redirect:";
    }
}
