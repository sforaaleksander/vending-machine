package com.codecool.vendingmachine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Purchase {
    Balance balance;
    Product chosenProduct;
    View view;
    Run run;
    Input input;

    public Purchase(View view, Input input, Run run) {
        this.balance = new Balance();
        this.view = view;
        this.input = input;
        this.run = run;
    }

    public Product getChosenProduct() {
        return chosenProduct;
    }

    public void setChosenProduct(Product chosenProduct) {
        this.chosenProduct = chosenProduct;
    }

    public void start() {
        chooseProduct();
        if(chosenProduct==null) return;
        balance.setRequired(chosenProduct.price);
        while (!balance.isPaid()){
            collectCoins();
            view.displayUserBalance(balance);
        }
//        for (Map.Entry<Coin, Integer> e : balance.getCoins().entrySet()) {
//            System.out.println(e.getKey() + ":  " + e.getValue());
//        }
        giveBackChange();
    }

    private void giveBackChange() {
        BigDecimal change = balance.getInserted().subtract(balance.getRequired());
        Map<Coin, Integer> changeCoins = new HashMap<>();
        while (change.compareTo(BigDecimal.ZERO) > 0) {
            for (Coin coin : balance.getCoins().keySet()) {
                if (coin.value.compareTo(change) <= 0 && balance.getCoins().get(coin) > 0) {
                    changeCoins.merge(coin, 1, Integer::sum);
                    change = change.subtract(coin.value);
                    balance.getCoins().merge(coin, -1, Integer::sum);
                }
            }
        }
        for (Map.Entry<Coin, Integer> e : changeCoins.entrySet()) {
            System.out.println("Giving back change: ");
            System.out.println(e.getKey() + ":  " + e.getValue());
        }
    }

    private void collectCoins() {
        String stringCoin = "";
        while (!Coin.strings.contains(stringCoin)) {
            stringCoin = input.gatherInput("Insert a coin");
        }
        System.out.println("inserted a correct coin");
        BigDecimal[] properties = Coin.getCoinByString(stringCoin);
        Coin coin = Coin.getCoinByProperties(properties[0], properties[1]);
        balance.insert(coin.value);
        balance.getCoins().merge(coin, 1, Integer::sum);
    }

    private void chooseProduct(){
        view.mainDisplay();
        int productNo = input.gatherIntInput("Choose product: ", Product.values().length);
        if (productNo == 0) {
            this.run.setRun(false);
            return;
        }
        chosenProduct = Product.values()[productNo - 1];
    }
}
