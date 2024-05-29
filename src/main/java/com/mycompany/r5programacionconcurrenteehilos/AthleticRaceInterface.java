/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.r5programacionconcurrenteehilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author HP-TRABAJO
 */
public class AthleticRaceInterface extends JFrame {
    private JTextField nameField;
    private JTextArea registeredArea;
    private JTextArea resultArea;
    private Runner[] runners;
    private int runnerCount;

    public AthleticRaceInterface() {
        setTitle("Athletic Race");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JPanel registerPanel = new JPanel();
        registerPanel.add(new JLabel("Registrar corredor:"));
        nameField = new JTextField(15);
        registerPanel.add(nameField);
        JButton registerButton = new JButton("Registrar");
        registerPanel.add(registerButton);
        add(registerPanel);

        JPanel registeredPanel = new JPanel();
        registeredPanel.add(new JLabel("Corredores registrados:"));
        registeredArea = new JTextArea(6, 40);
        registeredArea.setEditable(false);
        JScrollPane registeredScroll = new JScrollPane(registeredArea);
        registeredPanel.add(registeredScroll);
        add(registeredPanel);

        JPanel resultPanel = new JPanel();
        resultPanel.add(new JLabel("Resultados:"));
        resultArea = new JTextArea(6, 40);
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultPanel.add(resultScroll);

        JButton startButton = new JButton("Iniciar carrera");
        JButton resetButton = new JButton("Reiniciar");
        JButton exitButton = new JButton("Terminar");

        resultPanel.add(startButton);
        resultPanel.add(resetButton);
        resultPanel.add(exitButton);
        add(resultPanel);

        runners = new Runner[5];
        runnerCount = 0;

        registerButton.addActionListener((ActionEvent e) -> {
            if (runnerCount < 5) {
                String name1 = nameField.getText().trim();
                if (!name1.isEmpty()) {
                    runners[runnerCount] = new Runner(name1);
                    registeredArea.append(name1 + "\n");
                    runnerCount++;
                    nameField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se ha alcanzado el máximo de 5 corredores.");
            }
        });

        startButton.addActionListener((ActionEvent e) -> {
            if (runnerCount == 5) {
                resultArea.setText("");
                ThreadRunner.resetPosition();
                for (Runner runner : runners) {
                    ThreadRunner threadRunner = new ThreadRunner(runner, resultArea);
                    threadRunner.start();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe registrar 5 corredores antes de iniciar la carrera.");
            }
        });

        resetButton.addActionListener((ActionEvent e) -> {
            nameField.setText("");
            registeredArea.setText("");
            resultArea.setText("");
            runners = new Runner[5];
            runnerCount = 0;
            ThreadRunner.resetPosition();
        });

        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AthleticRaceInterface().setVisible(true);
        });
    }
}
