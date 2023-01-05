package org.vokhlebalov.tacocloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.vokhlebalov.tacocloud.entities.Ingredient;
import org.vokhlebalov.tacocloud.entities.Taco;
import org.vokhlebalov.tacocloud.entities.TacoOrder;
import org.vokhlebalov.tacocloud.repositories.IngredientRepository;

import static org.vokhlebalov.tacocloud.entities.Ingredient.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Arrays.stream(Type.values()).forEach(
                type -> model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(
                            StreamSupport.stream(
                                    ingredientRepository.findAll().spliterator(),
                                    false
                            ).collect(Collectors.toList()),
                            type)
                )
        );
    }

    @ModelAttribute(name = "tacoOrder")

    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
