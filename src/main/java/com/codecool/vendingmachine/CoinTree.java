package com.codecool.vendingmachine;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoinTree {
    private Node root;
    private BigDecimal change;
    private List<Coin> coinList;
    private List<Map<Coin, Integer>> allPaths;


    public CoinTree(BigDecimal change) {
        this.change = change;
        this.root = new Node(change, new HashMap<>());
        coinList = Stream.of(Coin.values()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        this.allPaths = new ArrayList<>();
        createTree(root);
    }

    public List<Map<Coin, Integer>> getAllPaths() {
        return allPaths;
    }

    public void findAllPaths(){
        traverse(root);
    }

    private void traverse(Node node) {
        if (node.getRemainingChange().compareTo(BigDecimal.ZERO)==0 && !allPaths.contains(node.getCollectedCoins())) {
            allPaths.add(node.getCollectedCoins());
        }
        for (Node child : node.getChildren()) {
            traverse(child);
        }
    }

    private void createTree(Node node){
        for (Coin coin : coinList) {
            if (coin.value.compareTo(node.getRemainingChange()) <= 0) {
                Node childNode = node.insertNode(coin);
                createTree(childNode);
            }
        }
    }
}
