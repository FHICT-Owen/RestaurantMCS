package com.example.restaurantmcs.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/table")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) { this.tableService = tableService; }

    @GetMapping("/{tableNumber}")
    public Optional<RestaurantTable> getTableByNumber(@RequestParam Integer restaurantId, @PathVariable("tableNumber") Integer tableNumber) {
        System.out.println(restaurantId);
        System.out.println(tableNumber);
        return tableService.getTableByNumber(restaurantId, tableNumber); }

    @GetMapping
    public List<RestaurantTable> getTablesByRestaurantId(@RequestParam Integer id) { return tableService.getTablesByRestaurantId(id); }

    @PostMapping
    public void createTable(@RequestBody RestaurantTable restaurantTable) { tableService.createTable(restaurantTable); }

    @PutMapping("/{tableId}/update")
    public ResponseEntity<RestaurantTable> updateTable(@RequestBody RestaurantTable restaurantTable, @PathVariable("tableId") Integer id) {
        boolean success = tableService.updateTable(id, restaurantTable);
        if (success)
            return new ResponseEntity<>(restaurantTable, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{tableId}/activity")
    public ResponseEntity<RestaurantTable> setActiveTable(@RequestBody Boolean state, @PathVariable("tableId") Integer id) {
        boolean success = tableService.setActive(id, state);
        if (success)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{tableId}/use")
    public ResponseEntity<RestaurantTable> setInUseTable(@RequestBody Boolean state, @PathVariable("tableId") Integer id) {
        boolean success = tableService.setInUse(id, state);
        if (success)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
