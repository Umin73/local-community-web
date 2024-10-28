package com.example.backend.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    public final CategorySevice categoryService;

    public CategoryController(CategorySevice categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/category/{categoryId}", produces = "application/json; charset=utf-8")
    public String getCategoryNameById(@PathVariable("categoryId") Long categoryId) {
        return categoryService.getCategoryNameById(categoryId);
    }
}
