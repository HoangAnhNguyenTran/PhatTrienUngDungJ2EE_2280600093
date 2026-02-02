package BaiTapBuoi4.controller;

import jakarta.validation.Valid;
import BaiTapBuoi4.model.Category;
import BaiTapBuoi4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // List category
    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/index"; // category/index.html
    }

    // Form create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    // Handle create
    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("category") Category category,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "category/create";
        }

        categoryService.add(category);
        return "redirect:/categories";
    }

    // Form edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Category category = categoryService.get(id);
        if (category == null) {
            return "error/404";
        }
        model.addAttribute("category", category);
        return "category/edit";
    }

    // Handle edit
    @PostMapping("/edit")
    public String edit(
            @Valid @ModelAttribute("category") Category category,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "category/edit";
        }

        categoryService.update(category);
        return "redirect:/categories";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
