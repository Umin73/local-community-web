package com.example.foundation.category;

import com.example.foundation.post.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategorySevice {
    public CategoryRepository categoryRepository;
    public String getCategoryNameById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found"));
        return category.getName();
    }
}
