package com.ximenes.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Berkson Ximenes
 * Date: 14/06/2021
 * Time: 11:24
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "recipes")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
