package com.ximenes.recipeproject.domain;

import javax.persistence.*;

/**
 * Created by Berkson Ximenes
 * Date: 14/06/2021
 * Time: 07:25
 */
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String des) {
        this.description = description;
    }
}
