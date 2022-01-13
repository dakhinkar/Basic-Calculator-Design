package com.simpleCalculator.GUI;

import com.simpleCalculator.Cal.Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class ButtonPanel extends JPanel implements ActionListener, KeyListener{
    JButton[] numButtons;
    JButton[] operatorButton;
    StringBuilder sb;
    DisplayPanel displayPanel;
    public boolean flag = true;

    Map<Integer, Character> inputMap;
    Map<Character, JButton> outMap;

    public ButtonPanel(){
        sb = new StringBuilder();
        inputMap = new HashMap<>();
        outMap = new HashMap<>();

        setInputMap();
        setAllKey();
        setOutMap();
        addButton();

        this.setEnabled(false);
        this.setLayout(new GridLayout(5,4));
        this.setPreferredSize(new Dimension(360,360));
        this.setVisible(true);
    }

    private void setAllKey(){
        numButtons = new JButton[]{new JButton("0"), new JButton("1"),
                new JButton("2"), new JButton("3"),new JButton("4"), new JButton("5"),
                new JButton("6"), new JButton("7"),new JButton("8"), new JButton("9")
        };
        operatorButton = new JButton[]{new JButton("BACK"), new JButton("C"),new JButton("CE"),
                new JButton("*"),
                new JButton("/"),
                new JButton("-"),
                new JButton("+"),
                new JButton("00"),
                new JButton("."),
                new JButton("="),
        };
        for (JButton numButton : numButtons) {
            numButton.setFont(new Font("Calibri", Font.BOLD, 20));
            numButton.addActionListener(this);
            numButton.addKeyListener(this);
        }
        for (JButton numButton : operatorButton) {
            numButton.setFont(new Font("Calibri", Font.BOLD, 20));
            numButton.addActionListener(this);
            numButton.addKeyListener(this);
        }
    }

    private void addButton(){
        int i = 0;
        int j=1;
        for(i = 0; i<4; i++){
            this.add(operatorButton[i]);
        }
        for (j = 7; j<=9; j++){
            this.add(numButtons[j]);
        }
        this.add(operatorButton[i++]);
        for (j = 4; j<=6; j++){
            this.add(numButtons[j]);
        }
        this.add(operatorButton[i++]);
        for (j = 1; j<=3; j++){
            this.add(numButtons[j]);
        }
        this.add(operatorButton[i++]);
        this.add(operatorButton[i++]);
        this.add(numButtons[0]);
        this.add(operatorButton[i++]);
        this.add(operatorButton[i++]);
    }

    private void setInputMap(){
        int j = 0;
        char [] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 48 ; i<=57; i++){
            inputMap.put(i, num[j++] );
        }
        j= 0;
        for (int i = 96 ; i<=105; i++){
            inputMap.put(i,  num[j++] );
        }
        inputMap.put(110,'.');
        inputMap.put(46,'.');
        inputMap.put(111,'/');
        inputMap.put(47,'.');
        inputMap.put(106,'*');
        inputMap.put(107,'+');
        inputMap.put(109,'-');
        inputMap.put(45,'-');
        inputMap.put(10,'=');
        inputMap.put(8,'d');
        inputMap.put(37,'<');
        inputMap.put(39,'>');
        inputMap.put(61,'=');
    }

    private void setOutMap(){
        int i = 0;
        char [] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for(JButton button : numButtons){
            outMap.put(num[i],button);
            i++;
        }
        i = 0;
        char[] oper = {'d', '`', '`', '*', '/', '-', '+' , 'z', '.', '='};
        for(JButton button : operatorButton){
            outMap.put(oper[i++], button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int len = sb.length();
        JButton button = (JButton) e.getSource();
        switch (button.getText()){
            case "BACK":
                if(len >0){
                    sb.deleteCharAt(len-1);
                }

                setText();
                break;
            case "CE":
                sb.delete(0,len);
                setText();
                break;
            case "C":
                sb.delete(0,len);
                setText();
                result(displayPanel.textField.getText());
                break;
            case "=":
                result(displayPanel.textField.getText());
                break;
            default:
                String s  = button.getText();
                if(s.charAt(0) == '-' && sb.length() == 0){
                    sb.append("0");
                }
                sb.append(s);
                setText();
                break;
        }
     //   System.out.println(sb.toString());
    }


    public void setEnabled(boolean isEnable){
        for(JButton button : numButtons){
            button.setEnabled(isEnable);
            button.setFocusable(true);
        }
        for(JButton button : operatorButton){
            button.setEnabled(isEnable);
            button.setFocusable(true);
        }

    }

    public void setText(){
        String str = sb.toString();
        if(str == null || str.length()<= 0){
            displayPanel.textField.setText("0");
           // sb.append("0");
        }else{
            displayPanel.textField.setText(sb.toString());
        }

    }

    private void result(String str){
        Solution s = new Solution();
        double res = s.calculate(str, this);
        if (!flag) {
            displayPanel.result.setText("Syntax Error");
            flag = true;
        } else {
            displayPanel.result.setText("" + res);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        JButton button = outMap.get(inputMap.get(code));
        button.doClick();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
