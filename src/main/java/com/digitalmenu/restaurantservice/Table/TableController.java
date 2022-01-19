package com.digitalmenu.restaurantservice.Table;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/table")
public class TableController {

    private final TableService tableService;

    @GetMapping("/{tableId}")
    public RestaurantTable getTable(@PathVariable("tableId") Integer tableId) {
        return tableService.getTableById(tableId);
    }

    @GetMapping("/tables/{restaurantId}")
    public List<RestaurantTable> getTables(@PathVariable("restaurantId") Integer id) {
        return tableService.getTablesByRestaurantId(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('access:table')")
    public ResponseEntity<RestaurantTable> createTable(@RequestBody @Valid RestaurantTable restaurantTable) {
        return new ResponseEntity<>(tableService.createTable(restaurantTable), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('access:table')")
    public ResponseEntity<RestaurantTable> updateTable(@RequestBody @Valid RestaurantTable table) {
        tableService.updateTable(table);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("inuse/{tableId}")
    public ResponseEntity<RestaurantTable> setTableInUse(@PathVariable("tableId") Integer id) {
        tableService.setTableInUse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{tableId}")
    @PreAuthorize("hasAuthority('access:table')")
    public void deleteTable(@PathVariable("tableId") Integer id) {
        tableService.deleteTable(id);
    }
}
