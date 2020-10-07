package com.codecool.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private BigDecimal remainingChange;
    private List<Node> children;
    private Map<Coin, Integer> collectedCoins;

    public Node(BigDecimal remainingChange, Map<Coin, Integer> collectedCoins) {
        this.remainingChange = remainingChange;
        this.collectedCoins = collectedCoins;
        this.children = new ArrayList<>();
    }

    public BigDecimal getRemainingChange() {
        return remainingChange;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Map<Coin, Integer> getCollectedCoins() {
        return collectedCoins;
    }

    public Node insertNode(Coin coin){
        BigDecimal change = this.remainingChange.subtract(coin.value);
        Map<Coin, Integer> coinMap = new HashMap<>(collectedCoins);
        coinMap.merge(coin, 1, Integer::sum);
        Node node = new Node(change, coinMap);
        this.children.add(node);
        return node;
    }

}
