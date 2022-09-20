package com.example.lab4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Cashier {
//    private List<change> moneys ;


    @RequestMapping(value = "/getChange/{money}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("money") double money) {
        Change cash = new Change();
        cash.setB1000(Math.floorDiv((int) money, 1000));
        money %= 1000;
        cash.setB500(Math.floorDiv((int) money, 500));
        money %= 500;
        cash.setB100(Math.floorDiv((int) money, 100));
        money %= 100;
        cash.setB20(Math.floorDiv((int) money, 20));
        money %= 20;
        cash.setB10(Math.floorDiv((int) money, 10));
        money %= 10;
        cash.setB5(Math.floorDiv((int) money, 5));
        money %= 5;
        cash.setB1(Math.floorDiv((int) money, 1));

        return cash;
    }

}
