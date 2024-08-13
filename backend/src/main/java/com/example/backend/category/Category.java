package com.example.backend.category;

import com.example.backend.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    public String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
}
