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

    public CoinTree(BigDecimal change) {
        this.change = change;
        this.root = new Node(change, new ArrayList<>());
        coinList = Stream.of(Coin.values()).collect(Collectors.toList());
    }




}
