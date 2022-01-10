package com.digitalmenu.restaurantservice.Ingredients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @SequenceGenerator(
            name = "ingredient_sequence",
            sequenceName = "ingredient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingredient_sequence"
    )
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private Integer restaurantId;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name_NL;
    @NotNull
    @Column(nullable = false)
    private Boolean isAllergen;
    @NotNull
    @Column(nullable = false)
    private Boolean isInStock;
}
