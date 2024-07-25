//package com.example.foundation.category;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/category/{categoryId}")
//public class CategoryController {
//
//    @Autowired
//    public CategorySevice categoryService;
//
//    @GetMapping("/posts/{categoryId}")
//    public String getCategoryNameById(@PathVariable("categoryId") Long categoryId) {
//        String name = categoryService.getCategoryNameById(categoryId);
//        return name;
//    }
//}
