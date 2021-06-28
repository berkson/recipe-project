package com.ximenes.recipeproject.domain;

import com.ximenes.recipeproject.enums.Difficulty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by berkson
 * Date: 13/06/2021
 * Time: 21:36
 */
@Data
@ToString(exclude = {"ingredients", "notes", "categories"})
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    /*This says that recipe owns the relationship and
     mappedBy says that this recipe will be stored on
     the ingredient property 'recipe'
     use mappedBy on Many relationships
     Relation Recipe -> Ingredients
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob // Creates a bytearray or blob on the database.
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    //if you delete the recipe will delete all notes. this is the owner of relationship
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    // @JoinTable Necessary to create only one table that references
    // recipes ids and category ids.
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
