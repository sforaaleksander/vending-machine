package com.codecool.vendingmachine.model;

import com.codecool.vendingmachine.model.tree.CoinTree;
import com.codecool.vendingmachine.input.Input;
import com.codecool.vendingmachine.view.View;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


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

    public void start() {
        chooseProduct();
        if(chosenProduct==null) return;
        balance.setRequired(chosenProduct.price);
        while (!balance.isPaid()){
            collectCoins();
            view.displayUserBalance(balance);
        }
        giveBackChange();
    }

    private void giveBackChange() {
        BigDecimal change = balance.calculateChange();
        Map<Coin, Integer> changeCoins = getCombinationByTree(change);
        view.printGiveChange(changeCoins);
    }


    private Map<Coin, Integer> getCombinationByTree(BigDecimal change) {
        CoinTree coinTree = new CoinTree(change, balance);
        return coinTree.getCombinationByTree(coinTree.getRoot());
    }

    private List<Map<Coin, Integer>> sortCombinations(List<Map<Coin, Integer>> possibleChangeCombinations) {
        return possibleChangeCombinations
                .stream()
                .sorted(Comparator.comparing(e -> e.values()
                        .stream()
                        .mapToInt(Number::intValue)
                        .sum())).collect(Collectors.toList());
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
