package com.codecool.vendingmachine;


public class View {

    public void displayProducts(){
        StringBuilder sb = new StringBuilder();
        for (Product product : Product.values()) {
            sb.append(product.id)
                    .append(". ")
                    .append(product.name)
                    .append(": ")
                    .append(String.format("%.2f", product.price))
                    .append("\n");
        }
        System.out.println(sb.toString());
    }
}
