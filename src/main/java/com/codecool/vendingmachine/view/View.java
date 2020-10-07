package com.codecool.vendingmachine.view;


import com.codecool.vendingmachine.model.Balance;
import com.codecool.vendingmachine.model.Coin;
import com.codecool.vendingmachine.model.Product;

import java.util.Map;

public class View {
    String welcomeHeader = "Welcome to the Vending Machine! \nSelect the product by inputting its number:\nOr press zero to exit";
    String balanceInsertedInfo = "You have inserted: ";
    String balanceRemainingInfo = "Remaining: ";

    private void displayWelcomeHeader() {
        System.out.println(welcomeHeader);
    }

    private void displayProducts() {
        StringBuilder sb = new StringBuilder();
        for (Product product : Product.values()) {
            sb.append(product.id)
                    .append(". ")
                    .append(product.name)
                    .append(": ")
                    .append(String.format("%.2f", product.price))
                    .append("zl")
                    .append("\n");
        }
        System.out.println(sb.toString());
    }

    public void displayUserBalance(Balance balance) {
        System.out.println(balanceInsertedInfo + String.format("%.2f", balance.getInserted()));
        System.out.println(balanceRemainingInfo + String.format("%.2f", (balance.getRequired().subtract(balance.getInserted()))));
    }

    public void mainDisplay() {
        displayWelcomeHeader();
        displayProducts();
    }

    public void printGiveChange(Map<Coin, Integer> map) {
        for (Map.Entry<Coin, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void transactionCanceled() {
        System.out.println("Could not give back change for the given product.");
        System.out.println("Giving you back the coins.");
    }
}
