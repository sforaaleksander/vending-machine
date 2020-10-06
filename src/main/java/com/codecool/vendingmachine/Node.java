package com.codecool.vendingmachine;

import java.math.BigDecimal;
import java.util.List;

public class Node {
    private BigDecimal remainingChange;
    private List<Node> children;
    private List<Coin> collectedCoins;

    public Node(BigDecimal remainingChange, List<Coin> collectedCoins) {
        this.remainingChange = remainingChange;
        this.collectedCoins = collectedCoins;
    }

    public BigDecimal getRemainingChange() {
        return remainingChange;
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<Coin> getCollectedCoins() {
        return collectedCoins;
    }

    public void insertNode(Coin coin){
        BigDecimal change = this.remainingChange.subtract(coin.value);
        List<Coin> coinList = this.collectedCoins;
        coinList.add(coin);
        Node node = new Node(change, coinList);
        children.add(node);
    }

}
