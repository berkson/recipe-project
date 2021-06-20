package com.ximenes.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Berkson Ximenes
 * Date: 14/06/2021
 * Time: 07:25
 */
@Data
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
