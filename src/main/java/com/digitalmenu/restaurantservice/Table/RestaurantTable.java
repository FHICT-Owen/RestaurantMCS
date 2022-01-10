package com.digitalmenu.restaurantservice.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable {
    @Id
    @SequenceGenerator(
            name = "table_sequence",
            sequenceName = "table_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "table_sequence"
    )
    private Integer id;
    @NotBlank
    @Column(nullable = false)
    private Integer tableNumber;
    @NotBlank
    @Column(nullable = false)
    private Integer restaurantId;
    @NotBlank
    @Column(nullable = false)
    private Boolean isActive;
    @NotBlank
    @Column(nullable = false)
    private Boolean inUse;
}
