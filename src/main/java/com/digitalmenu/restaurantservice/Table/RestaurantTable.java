package com.digitalmenu.restaurantservice.Table;

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
    @NotNull
    @Column(nullable = false)
    private Integer tableNumber;
    @NotNull
    @Column(nullable = false)
    private Integer restaurantId;
    @NotNull
    @Column(nullable = false)
    private Boolean isActive;
    @NotNull
    @Column(nullable = false)
    private Boolean inUse;
}
