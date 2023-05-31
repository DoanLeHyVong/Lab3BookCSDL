package doanlehyvong.demo.controller;

import doanlehyvong.demo.model.Category;
import doanlehyvong.demo.services.BookService;
import doanlehyvong.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("category", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm (@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editCategory (@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/category";
    }


    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}

