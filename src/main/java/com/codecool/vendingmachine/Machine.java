package com.codecool.vendingmachine;

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
