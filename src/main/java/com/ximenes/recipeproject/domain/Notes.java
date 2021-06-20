package com.ximenes.recipeproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by berkson
 * Date: 13/06/2021
 * Time: 21:40
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob // Creates a large object string on database.
    private String recipeNotes;

}
