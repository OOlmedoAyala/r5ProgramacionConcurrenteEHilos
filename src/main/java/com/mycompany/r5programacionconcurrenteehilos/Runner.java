/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.r5programacionconcurrenteehilos;

/**
 *
 * @author HP-TRABAJO
 */
public class Runner {
    private final String name;
    private final int speed;

    public Runner(String name) {
        this.name = name;
        this.speed = (int) (Math.random() * 31);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
