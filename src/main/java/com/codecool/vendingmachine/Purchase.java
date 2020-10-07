package com.codecool.vendingmachine;

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
        giveBackChange();
    }

    private void giveBackChange() {
        BigDecimal change = balance.calculateChange();
//        List<Map<Coin, Integer>> possibleChangeCombinations = getPossibleChangeCombinations(change);
        List<Map<Coin, Integer>> possibleChangeCombinations = getPossibleChangeCombinationsByTree(change);

        List<Map<Coin, Integer>> sortedPossibilities = sortCombinations(possibleChangeCombinations);
        printCombinations(sortedPossibilities);
        if (sortedPossibilities.isEmpty()) {
            spitBackInsertedCoins();
            view.transactionCanceled();
            return;
        }
        view.printGiveChange(sortedPossibilities.get(0));
    }

    private List<Map<Coin, Integer>> getPossibleChangeCombinationsByTree(BigDecimal change) {
        CoinTree coinTree = new CoinTree(change);
        coinTree.findAllPaths();
        System.out.println("NUMBER OF COMBINATIONS: " + coinTree.getAllPaths().size());
        return coinTree.getAllPaths();
    }

    private List<Map<Coin, Integer>> sortCombinations(List<Map<Coin, Integer>> possibleChangeCombinations) {
        return possibleChangeCombinations
                .stream()
                .sorted(Comparator.comparing(e -> e.values()
                        .stream()
                        .mapToInt(Number::intValue)
                        .sum())).collect(Collectors.toList());
    }

    private void spitBackInsertedCoins() {

    }


    private void printCombinations(List<Map<Coin, Integer>> possibleChangeCombinations) {
        for (Map<Coin, Integer> map : possibleChangeCombinations) {
            System.out.println("COMBINATION: ");
            for (Map.Entry<Coin, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("\n\n\n");
        }
    }

    private List<Map<Coin, Integer>> getPossibleChangeCombinations(BigDecimal change) {
        List<Map<Coin, Integer>> combinations = new ArrayList<>();
        BigDecimal tempChange;
        Balance tempBalance;
        List<Coin> coins = balance.getCoins().keySet()
                                .stream()
                                .sorted(Comparator
                                        .comparingDouble(e->e.value.doubleValue()))
                                .sorted(Comparator.reverseOrder())
                                .collect(Collectors.toList());
        for (Iterator<Coin> it = coins.iterator(); it.hasNext();) {
            tempChange = new BigDecimal(String.valueOf(change));
            tempBalance = balance;
            it.next();
            Map<Coin, Integer> changeCoins = new HashMap<>();
            for (Coin coin : coins) {
                while (tempChange.compareTo(BigDecimal.ZERO) > 0
                        && coin.value.compareTo(tempChange) <= 0
                        && tempBalance.getCoins().get(coin) > 0) {
                    changeCoins.merge(coin, 1, Integer::sum);
                    tempChange = tempChange.subtract(coin.value);
                    tempBalance.getCoins().merge(coin, -1, Integer::sum);
                }
            }
            if (!changeCoins.isEmpty() && !combinations.contains(changeCoins) && isCombinationEqualsChange(changeCoins, change)) {
                combinations.add(changeCoins);
            }
            it.remove();
        }
        return combinations;
    }

    private boolean isCombinationEqualsChange(Map<Coin, Integer> changeCoins, BigDecimal change) {
        double totalFromCombination = changeCoins.entrySet()
                .stream().mapToDouble(e->e.getKey().value.multiply(new BigDecimal(e.getValue().toString())).doubleValue()).sum();
        return change.doubleValue() == totalFromCombination;
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
