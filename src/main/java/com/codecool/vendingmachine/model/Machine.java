package com.codecool.vendingmachine.model;

import com.codecool.vendingmachine.input.Input;
import com.codecool.vendingmachine.view.View;

public class Machine {
    private Purchase purchase;
    private Run run;

    public void init(){
        Run run = new Run();
        View view = new View();
        Input input = new Input();
        while (run.isRun()) {
            new Purchase(view, input, run).start();
        }
    }
}
