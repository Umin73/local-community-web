package com.example.backend.category;

import com.example.backend.comment.Comment;
import com.example.backend.comment.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CategorySevice {
    public final CategoryRepository categoryRepository;

    public CategorySevice(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String getCategoryNameById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Invalid category ID"));
        return category.getName();
    }
}

