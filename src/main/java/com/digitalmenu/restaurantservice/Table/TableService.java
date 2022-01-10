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

    public RestaurantTable updateTable(RestaurantTable restaurantTable) {
        var foundRestaurantTable = tableRepository.findById(restaurantTable.getId())
                .orElseThrow(() -> new NoSuchElementFoundException("Table not found"));
        foundRestaurantTable.setIsActive(restaurantTable.getIsActive());
        foundRestaurantTable.setInUse(restaurantTable.getInUse());
        return tableRepository.save(foundRestaurantTable);
    }

    public void deleteTable(Integer id) {
        var restaurantTable = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Table not found"));
        if (restaurantTable.getInUse())
            throw new TableInUseException("Table is in use");

        tableRepository.deleteById(id);
    }

//    public boolean removeTable(Integer id) {
//        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
//        if(optionalTable.isPresent()) {
//            if (!optionalTable.get().getInUse()) {
//                RestaurantTable actualRestaurantTable = optionalTable.get();
//                tableRepository.delete(actualRestaurantTable);
//                return true;
//            }
//            throw new IllegalStateException("Table is being used!");
//        }
//        throw new IllegalStateException("Could not find any table with id " + id);
//    }
//    public boolean updateTable(Integer id, RestaurantTable restaurantTable) {
//        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
//        if(optionalTable.isPresent()) {
//            if (!optionalTable.get().getInUse()) {
//                RestaurantTable actualRestaurantTable = optionalTable.get();
//                actualRestaurantTable.setTableNumber(restaurantTable.getTableNumber());
//                actualRestaurantTable.setIsActive(false);
//                tableRepository.save(actualRestaurantTable);
//                return true;
//            }
//            throw new IllegalStateException("Table is being used!");
//        }
//        throw new IllegalStateException("Could not find any table with id " + id);
//    }
//
//    public boolean setActive (Integer id, Boolean state) {
//        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
//        if(optionalTable.isPresent()) {
//            if (!optionalTable.get().getInUse()) {
//                RestaurantTable actualRestaurantTable = optionalTable.get();
//                actualRestaurantTable.setIsActive(state);
//                tableRepository.save(actualRestaurantTable);
//                return true;
//            }
//            throw new IllegalStateException("Table is being used!");
//        }
//        throw new IllegalStateException("Could not find any table with id " + id);
//    }
//
//    public boolean setInUse (Integer id, Boolean state) {
//        Optional<RestaurantTable> optionalTable = tableRepository.findById(id);
//        if(optionalTable.isPresent()) {
//            RestaurantTable actualRestaurantTable = optionalTable.get();
//            actualRestaurantTable.setInUse(state);
//            tableRepository.save(actualRestaurantTable);
//            return true;
//        }
//        throw new IllegalStateException("Could not find any table with id " + id);
//    }
}
