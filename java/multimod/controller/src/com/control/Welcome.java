package com.control;

import com.dto.DaoDemo;
import com.service.TestServices;

public class Welcome {
    public static void main(String[] args) {
        System.out.println("This is controller");

        DaoDemo daoDemo = new DaoDemo();
        daoDemo.print();

        TestServices testServices = new TestServices();
        testServices.print();
    }
}
