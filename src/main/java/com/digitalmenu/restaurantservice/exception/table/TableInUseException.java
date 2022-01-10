package com.digitalmenu.restaurantservice.exception.table;

public class TableInUseException extends RuntimeException {

    public TableInUseException(String message) {
        super(message);
    }
}
