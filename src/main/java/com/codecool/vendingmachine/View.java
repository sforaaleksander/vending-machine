package com.codecool.vendingmachine;


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
}
