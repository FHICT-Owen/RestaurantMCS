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
    public ResponseEntity<RestaurantTable> createTable(@RequestBody @Valid RestaurantTable restaurantTable) {
        return new ResponseEntity<>(tableService.createTable(restaurantTable), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RestaurantTable> updateTable(@RequestBody @Valid RestaurantTable restaurantTable) {
        return new ResponseEntity<>(tableService.updateTable(restaurantTable), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{tableId}")
    public void deleteTable(@PathVariable("tableId") Integer id) {
        tableService.deleteTable(id);
    }
//    @PutMapping("/{tableId}/activity")
//    public ResponseEntity<RestaurantTable> setActiveTable(@RequestParam Boolean state, @PathVariable("tableId") Integer id) {
//        boolean success = tableService.setActive(id, state);
//        if (success)
//            return new ResponseEntity<>(HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("/{tableId}/use")
//    public ResponseEntity<RestaurantTable> setInUseTable(@RequestParam Boolean state, @PathVariable("tableId") Integer id) {
//        boolean success = tableService.setInUse(id, state);
//        if (success)
//            return new ResponseEntity<>(HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
