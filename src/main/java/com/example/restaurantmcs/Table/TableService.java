package com.example.restaurantmcs.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) { this.tableRepository = tableRepository; }

    public List<RestaurantTable> getTablesByRestaurantId(Integer restaurantId) {
        List<RestaurantTable> restaurantTables = tableRepository.findTablesByRestaurantId(restaurantId);
        if (restaurantTables.isEmpty())
            throw new IllegalStateException("No tables found with restaurantId " + restaurantId);
        return restaurantTables;
    }

    public void createTable(RestaurantTable restaurantTable) {
        Optional<RestaurantTable> tableByNumber = tableRepository.findTableByTableNumberAndRestaurantId(restaurantTable.getTableNumber(), restaurantTable.getRestaurantId());
        if (tableByNumber.isPresent())
            throw new EntityExistsException("Number already taken for that restaurant!");
        tableRepository.save(restaurantTable);
    }

    public boolean updateTable(Integer id, RestaurantTable restaurantTable) {
        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
        if(optionalTable.isPresent()) {
            if (!optionalTable.get().getInUse()) {
                RestaurantTable actualRestaurantTable = optionalTable.get();
                actualRestaurantTable.setTableNumber(restaurantTable.getTableNumber());
                actualRestaurantTable.setIsActive(false);
                tableRepository.save(actualRestaurantTable);
                return true;
            }
            throw new IllegalStateException("Table is being used!");
        }
        throw new IllegalStateException("Could not find any table with id " + id);
    }

    public boolean setActive (Integer id, Boolean state) {
        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
        if(optionalTable.isPresent()) {
            if (!optionalTable.get().getInUse()) {
                RestaurantTable actualRestaurantTable = optionalTable.get();
                actualRestaurantTable.setIsActive(state);
                tableRepository.save(actualRestaurantTable);
                return true;
            }
            throw new IllegalStateException("Table is being used!");
        }
        throw new IllegalStateException("Could not find any table with id " + id);
    }

    public boolean setInUse (Integer id, Boolean state) {
        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
        if(optionalTable.isPresent()) {
            RestaurantTable actualRestaurantTable = optionalTable.get();
            actualRestaurantTable.setInUse(state);
            tableRepository.save(actualRestaurantTable);
            return true;
        }
        throw new IllegalStateException("Could not find any table with id " + id);
    }

    public Optional<RestaurantTable> getTableByNumber(Integer restaurantId, Integer tableNumber) {
        return tableRepository.findTableByTableNumberAndRestaurantId(tableNumber, restaurantId);
    }
}
