package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Netting {

    public static Trade parseTrade(String message) {
        int dir = Character.getNumericValue(message.charAt(0));
        Direction direction = (dir == 0) ? Direction.BUY : Direction.SELL;

        String date = message.substring(1, 9);
        int amount = Integer.parseInt(message.substring(9));

        return new Trade(direction, date, amount);
    }

    public static List<Trade> netTrades(List<Trade> trades) {

        Map<String, List<Trade>> grouped =
                trades.stream().collect(Collectors.groupingBy(t -> t.tradeDate));

        List<Trade> results = new ArrayList<>();

        for (String date : grouped.keySet()) {

            int buySum = 0;
            int sellSum = 0;

            for (Trade t : grouped.get(date)) {
                if (t.direction == Direction.BUY) {
                    buySum += t.amount;
                } else {
                    sellSum += t.amount;
                }
            }

            int net = sellSum - buySum;

            Direction resultDirection = (net >= 0) ? Direction.SELL : Direction.BUY;
            int netAmount = Math.abs(net);

            results.add(new Trade(resultDirection, date, netAmount));
        }

        return results;
    }

    public static String formatMessage(Trade t) {
        String dir = (t.direction == Direction.BUY) ? "0" : "1";
        return dir + t.tradeDate + String.format("%d", t.amount);
    }


}