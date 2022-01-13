package com.simpleCalculator.Cal;

import com.simpleCalculator.GUI.ButtonPanel;

import java.util.Stack;

public class Solution {

    public double calculate(String str , ButtonPanel panel){
        Stack<Double> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        double currNum = 0.0;
        char oper = '+';
        int len  = str.length();
        for(int i = 0; i<len ; i++){
            char c = str.charAt(i);
            if(i<len-1 && !Character.isDigit(c) ){
                char ch =  str.charAt(i+1);
                if(ch == '*' || ch == '/' || ch == '.'){
                    panel.flag = false;
                    return 0;
                }

            }
            if(Character.isDigit(c) || c == '.'){
                sb.append(c);
               // currNum = currNum *10 + (c - '0');
            }

            if((!Character.isDigit(c) && c != '.') || i == len-1){
                try {
                    currNum = Double.parseDouble(sb.toString());
                }catch (NumberFormatException e){
                    panel.flag = false;
                    System.err.println(e.getMessage());
                }


                if(oper == '+'){
                    st.push(currNum);
                }else if(oper == '-'){
                    st.push(-currNum);
                }else if(oper == '*'){
                    st.push(st.pop() * currNum);
                }else if(oper == '/'){
                    st.push(st.pop() / currNum);
                }
                sb.delete(0,sb.length());
                oper = c;
            }
        }

        double result = 0;
        while (!st.empty()){
            result += st.pop();
        }
        return result;
    }


}
