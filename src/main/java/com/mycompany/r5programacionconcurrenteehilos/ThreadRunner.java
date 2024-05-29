/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.r5programacionconcurrenteehilos;

import javax.swing.JTextArea;

/**
 *
 * @author HP-TRABAJO
 */
import javax.swing.JTextArea;

public class ThreadRunner extends Thread {
    private Runner runner;
    private JTextArea resultArea;
    private long startTime;
    private static int position = 1;

    public ThreadRunner(Runner runner, JTextArea resultArea) {
        this.runner = runner;
        this.resultArea = resultArea;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(runner.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;

        synchronized (resultArea) {
            resultArea.append(position + ". " + runner.getName() + " - Tiempo: " + totalTime + " segundos\n");
            position++;
        }
    }

    public static void resetPosition() {
        position = 1;
    }
}