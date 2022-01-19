package com.digitalmenu.restaurantservice.Table;

import com.digitalmenu.restaurantservice.exception.common.ElementAlreadyExistsException;
import com.digitalmenu.restaurantservice.exception.common.NoSuchElementFoundException;
import com.digitalmenu.restaurantservice.exception.table.TableInUseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public RestaurantTable getTableById(Integer id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Table not found"));
    }

    public List<RestaurantTable> getTablesByRestaurantId(Integer restaurantId) {
        return tableRepository.findTablesByRestaurantId(restaurantId);
    }

    public RestaurantTable createTable(RestaurantTable restaurantTable) {

        if (tableRepository.existsRestaurantTableByTableNumberAndRestaurantId(
                restaurantTable.getTableNumber(), restaurantTable.getRestaurantId()
        )) throw new ElementAlreadyExistsException(
                    "Table with number: "
                            + restaurantTable.getTableNumber()
                            + " already exists in this restaurant"
        );

        return tableRepository.save(restaurantTable);
    }

    public void updateTable(RestaurantTable table) {
        if (!tableRepository.existsById(table.getId()))
            throw new NoSuchElementFoundException("Table not found");
        table.setInUse(false);
        tableRepository.save(table);
    }

    public void setTableInUse(Integer id) {
        var table = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Table not found"));
        table.setInUse(true);
        tableRepository.save(table);
    }

    public void deleteTable(Integer id) {
        var restaurantTable = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Table not found"));
        if (restaurantTable.getInUse())
            throw new TableInUseException("Table is in use");

        tableRepository.deleteById(id);
    }
}
