package com.simpleCalculator.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPanel extends JPanel implements ActionListener {
    JLabel result ;
    JTextField textField;

    JButton power;
    JLabel l1, l2;
    ButtonGroup gp;
    ButtonPanel buttonPanel;
    boolean isOn = false;
    public  DisplayPanel(){


        result = new JLabel("");
        result.setHorizontalAlignment(SwingConstants.TRAILING);
        result.setFont(new Font("Calibri", Font.BOLD, 25));
        result.setBackground(Color.BLUE);
        result.setBounds(5,5,360,40);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.TRAILING);
        textField.setBounds(5,45,360,40);
        textField.setFont(new Font("Calibri", Font.BOLD,25));
        textField.setBackground(Color.BLACK);


        power = new JButton("POWER");
        power.setBounds(270,100,90,30);
        power.addActionListener(this);
        power.setBackground(Color.RED);
        power.setFont(new Font("Calibri", Font.BOLD, 15));
        power.setFocusable(false);
        this.add(power);

        this.add(result);
        this.add(textField);

        this.setPreferredSize(new Dimension(360,140));
        this.setLayout(null);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == power && !isOn){
            isOn = true;

            textField.setBackground(Color.BLUE);
            result.setText("0");
            textField.setText("0");
            buttonPanel.setEnabled(true);
            buttonPanel.setFocusable(true);

        }else{
            isOn = false;
            int len  = buttonPanel.sb.length();
            buttonPanel.sb.delete(0,len);
            textField.setText("");
            textField.setBackground(Color.BLACK);
            result.setText("");
            buttonPanel.setEnabled(false);
            buttonPanel.setFocusable(false);
        }
    }

    public void setButton(ButtonPanel buttonPanel){
        this.buttonPanel = buttonPanel;
        this.buttonPanel.setEnabled(false);
    }


}
