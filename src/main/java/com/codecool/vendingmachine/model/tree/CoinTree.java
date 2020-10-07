package com.codecool.vendingmachine.model.tree;

import com.codecool.vendingmachine.model.Balance;
import com.codecool.vendingmachine.model.Coin;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoinTree {
    private Node root;
    private BigDecimal change;
    private List<Coin> coinList;
    private List<Map<Coin, Integer>> allPaths;
    private Balance balance;


    public CoinTree(BigDecimal change, Balance balance) {
        this.change = change;
        this.root = new Node(change, new HashMap<>());
        coinList = Stream.of(Coin.values()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        this.allPaths = new ArrayList<>();
        this.balance = balance;
//        createTree(root);
    }

    public List<Map<Coin, Integer>> getAllPaths() {
        return allPaths;
    }

    public void findAllPaths() {
        traverse(root);
    }

    private void traverse(Node node) {
        if (node.getRemainingChange().compareTo(BigDecimal.ZERO) == 0 && !allPaths.contains(node.getCollectedCoins())) {
            allPaths.add(node.getCollectedCoins());
        }
        for (Node child : node.getChildren()) {
            traverse(child);
        }
    }

//    private void createTree(Node node) {
//        for (Coin coin : coinList) {
//            if (coin.value.compareTo(node.getRemainingChange()) <= 0) {
//                Node childNode = node.insertNode(coin);
//                createTree(childNode);
//            }
//        }
//    }

    public Map<Coin, Integer> getCombinationByTree(Node node) {
        for (Coin coin : coinList) {
            if (coin.value.compareTo(node.getRemainingChange()) <= 0) {
                Node childNode = node.insertNode(coin);
                getCombinationByTree(childNode);
            }
            if (node.getRemainingChange().compareTo(BigDecimal.ZERO) == 0 && isCombinationAvailable(node.getCollectedCoins())) {
                return node.getCollectedCoins();
            }
        }
        throw new ArithmeticException("Could not give back the change");
    }

    private boolean isCombinationAvailable(Map<Coin, Integer> coins) {
        Map<Coin, Integer> availableCoins = this.balance.getCoins();
        for (Coin coin : coins.keySet()) {
            if (coins.get(coin).compareTo(availableCoins.get(coin)) < 0 ) {
                return false;
            }
        }
        return true;
    }

    public Node getRoot() {
        return root;
    }
}

