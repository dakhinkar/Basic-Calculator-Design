package com.simpleCalculator.GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyFrame extends JFrame  implements KeyListener{
    DisplayPanel displayPanel;
    ButtonPanel buttonPanel;
    ImageIcon icon;

    public MyFrame(){
        buttonPanel = new ButtonPanel();
        buttonPanel.setBounds(5,150,360,360);
        this.add(buttonPanel);



        displayPanel = new DisplayPanel();
        displayPanel.setBounds(5,5,380,140);
        this.add(displayPanel);
        bTd();
        buttonPanel.displayPanel = displayPanel; // set button to display interface


        icon = new ImageIcon("C:\\Users\\prani\\Desktop\\ACCIO\\DSA Project\\Simple Calculator\\src\\calculator.png");


        this.setTitle("Calculator");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(390, 555);

        this.addKeyListener(this);
        this.setVisible(true);

    }


    public void bTd(){ // Button panel to display panel connection
        displayPanel.setButton(buttonPanel);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }
}
