package com.example.restaurantmcs.Table;

import javax.persistence.*;

@Entity
@Table
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
    private Integer tableNumber;
    private Integer restaurantId;
    private Boolean isActive;
    private Boolean inUse;

    public RestaurantTable() {
    }

    public RestaurantTable(Integer id, Integer tableNumber, Integer restaurantId, Boolean isActive, Boolean inUse) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.restaurantId = restaurantId;
        this.isActive = isActive;
        this.inUse = inUse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }
}
