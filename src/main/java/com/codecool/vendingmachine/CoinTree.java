package com.codecool.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoinTree {
    private Node root;
    private BigDecimal change;
    private List<Coin> coinList;
    private List<List<Coin>> allPaths;


    public CoinTree(BigDecimal change) {
        this.change = change;
        this.root = new Node(change, new ArrayList<>());
        coinList = Stream.of(Coin.values()).collect(Collectors.toList());
        this.allPaths = new ArrayList<>();
    }


    public boolean getAllPaths(){
        return traverse(root);
    }

    private boolean traverse(Node node) {
        if (node.getRemainingChange().compareTo(BigDecimal.ZERO)==0) {
            allPaths.add(node.getCollectedCoins());
            return true;
        }
        for (Node child : node.getChildren()) {
            traverse(child);
        }
        return false;
    }


}
