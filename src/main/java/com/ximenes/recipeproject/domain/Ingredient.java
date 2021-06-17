package com.ximenes.recipeproject.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Berkson Ximenes
 * Date: 14/06/2021
 * Time: 07:10
 */
@Entity
public class Ingredient {

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
