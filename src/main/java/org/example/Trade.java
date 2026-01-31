package org.example;

public class Trade {
    Direction direction;
    String tradeDate;
    int amount;

    Trade(Direction direction, String tradeDate, int amount) {
        this.direction = direction;
        this.tradeDate = tradeDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return direction + " | " + tradeDate + " | " + amount;
    }

}
