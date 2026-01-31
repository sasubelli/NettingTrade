package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> messages = new ArrayList<>();
        messages.add("02022083100010");
        messages.add("12022082900200");
        messages.add("12022083150000");

        List<Trade> trades = new ArrayList<>();
        for (String msg : messages) {
            trades.add(Netting.parseTrade(msg));
        }

        System.out.println("Parsed Trades:");
        trades.forEach(System.out::println);

        List<Trade> netted = Netting.netTrades(trades);

        System.out.println("\nNetting Result:");
        for (Trade t : netted) {
            System.out.println(t + " | message format: " + Netting.formatMessage(t));
        }
    }
}