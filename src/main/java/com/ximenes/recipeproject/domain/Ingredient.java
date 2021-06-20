package com.ximenes.recipeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Berkson Ximenes
 * Date: 14/06/2021
 * Time: 07:10
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = "recipe")
@NoArgsConstructor
@Entity
public class Ingredient {

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    // Does not have cascade. if we delete an ingredient
    // we dont want to delete a unit of measure
    // Relation Ingredient -> UnitOfMeasure, only.
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    /*
     Relation Ingredient -> Recipe
     Many ingredients to one recipe
     */
    @ManyToOne
    private Recipe recipe;

}
