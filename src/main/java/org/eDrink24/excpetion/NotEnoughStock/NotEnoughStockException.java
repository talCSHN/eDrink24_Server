package org.eDrink24.excpetion.NotEnoughStock;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String message) {
        super(message);
    }
}
