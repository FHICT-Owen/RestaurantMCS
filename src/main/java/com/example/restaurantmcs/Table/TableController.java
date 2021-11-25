package com.example.restaurantmcs.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/table")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) { this.tableService = tableService; }

    @GetMapping("/{tableId}")
    public Optional<RestaurantTable> getTable(@PathVariable("tableId") Integer tableId) {
        return tableService.getTable(tableId); }

    @GetMapping("/tables/{restaurantId}")
    public List<RestaurantTable> getTables(@PathVariable("restaurantId") Integer id) { return tableService.getTablesByRestaurantId(id); }

    @PostMapping
    public ResponseEntity<RestaurantTable> createTable(@RequestBody RestaurantTable restaurantTable) {
        tableService.createTable(restaurantTable);
        return new ResponseEntity<>(restaurantTable, HttpStatus.CREATED);
    }

    @PutMapping("/{tableId}/update")
    public ResponseEntity<RestaurantTable> updateTable(@RequestBody RestaurantTable restaurantTable, @PathVariable("tableId") Integer id) {
        boolean success = tableService.updateTable(id, restaurantTable);
        if (success)
            return new ResponseEntity<>(restaurantTable, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{tableId}/activity")
    public ResponseEntity<RestaurantTable> setActiveTable(@RequestParam Boolean state, @PathVariable("tableId") Integer id) {
        boolean success = tableService.setActive(id, state);
        if (success)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{tableId}/use")
    public ResponseEntity<RestaurantTable> setInUseTable(@RequestParam Boolean state, @PathVariable("tableId") Integer id) {
        boolean success = tableService.setInUse(id, state);
        if (success)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
