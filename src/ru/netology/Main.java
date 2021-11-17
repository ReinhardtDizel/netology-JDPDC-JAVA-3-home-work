package ru.netology;

import ru.netology.Service.MarketService;

public class Main {

    public static void main(String[] args) {

        MarketService myMarket = new MarketService();
        myMarket.run();
    }
}