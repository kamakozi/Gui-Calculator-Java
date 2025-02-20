package kama.at.Calculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;

@SpringBootApplication
public class Calculator {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int width = 500;
        int height = 500;

        JFrame frame = new JFrame("Calculator");

        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(width,height);
        frame.setLayout(new BorderLayout());

        JLabel displayText = new JLabel("0", SwingConstants.RIGHT);
        displayText.setFont(new Font("Arial" , Font.BOLD, 25));
        displayText.setOpaque(true);
        displayText.setBackground(Color.GRAY);
        displayText.setPreferredSize(new Dimension(width, 50));

        frame.add(displayText, BorderLayout.NORTH);

        JPanel buttonFrame = new JPanel();

        buttonFrame.setLayout(new GridLayout(4,4));

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btn0 = new JButton("0");
        JButton btnClear = new JButton("C");
        JButton btnPlus = new JButton("+");
        JButton btnMinus = new JButton("-");
        JButton btnDivide = new JButton("/");
        JButton btnMultiply = new JButton("*");
        JButton btnEqual = new JButton("=");

        buttonFrame.add(btn1);
        buttonFrame.add(btn2);
        buttonFrame.add(btn3);
        buttonFrame.add(btnClear);
        buttonFrame.add(btn4);
        buttonFrame.add(btn5);
        buttonFrame.add(btn6);
        buttonFrame.add(btnPlus);
        buttonFrame.add(btn7);
        buttonFrame.add(btn8);
        buttonFrame.add(btn9);
        buttonFrame.add(btnMinus);
        buttonFrame.add(btn0);
        buttonFrame.add(btnDivide);
        buttonFrame.add(btnMultiply);
        buttonFrame.add(btnEqual);

        btnClear.setBackground(Color.RED);
        btnClear.setOpaque(true);
        btnClear.setContentAreaFilled(true);  // ✅ Fix: Force background color

        btnEqual.setBackground(Color.GREEN);
        btnEqual.setOpaque(true);
        btnEqual.setContentAreaFilled(true);

        btnPlus.setBackground(Color.BLUE);
        btnPlus.setOpaque(true);
        btnPlus.setContentAreaFilled(true);

        btnMinus.setBackground(Color.BLUE);
        btnMinus.setOpaque(true);
        btnMinus.setContentAreaFilled(true);

        btnDivide.setBackground(Color.BLUE);
        btnDivide.setOpaque(true);
        btnDivide.setContentAreaFilled(true);

        btnMultiply.setBackground(Color.BLUE);
        btnMultiply.setOpaque(true);
        btnMultiply.setContentAreaFilled(true);


        frame.add(buttonFrame, BorderLayout.CENTER);


        StringBuilder firstNumber = new StringBuilder();
        StringBuilder secondNumber = new StringBuilder();
        String[] operator = new String[1];
        AtomicBoolean numbertwo = new AtomicBoolean(false);




        JButton[] numberButtons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (int i = 0; i < numberButtons.length; i++) {
            final int num = i;
            numberButtons[i].addActionListener(e -> {
                if (!numbertwo.get()) {
                    if (displayText.getText().equals("0")) {
                        displayText.setText(String.valueOf(num));
                        firstNumber.setLength(0);
                    } else {
                        displayText.setText(displayText.getText() + num);
                    }
                    firstNumber.append(num);
                } else {
                    if (displayText.getText().equals("0")) {
                        displayText.setText(String.valueOf(num));
                        secondNumber.setLength(0);
                    } else {
                        displayText.setText(displayText.getText() + num);
                    }
                    secondNumber.append(num);
                }
            });
        }
        btnMultiply.addActionListener(e -> {
            if(displayText.getText().isEmpty()){
                displayText.setText("0");
            }else{
                displayText.setText("0");
                numbertwo.set(true);
                operator[0] = "*";
            }
        });
        btnMinus.addActionListener(e -> {
            if(displayText.getText().isEmpty()){
                displayText.setText("0");
            }else{
                displayText.setText("0");
                numbertwo.set(true);
                operator[0] = "-";
            }
        });
        btnDivide.addActionListener(e -> {
            if(displayText.getText().isEmpty()){
                displayText.setText("0");
            }else{
                displayText.setText("0");
                numbertwo.set(true);
                operator[0] = "/";
            }
        });
        btnPlus.addActionListener(e -> {
            if(displayText.getText().isEmpty()){
                displayText.setText("0");
            }else{
                displayText.setText("0");
                numbertwo.set(true);
                operator[0] = "+";
            }
        });
        btnClear.addActionListener(e -> {
            displayText.setText("0");
            firstNumber.setLength(0);
            secondNumber.setLength(0);
            operator[0] = null;
            numbertwo.set(false);
        });
        btnEqual.addActionListener(e -> {
            if (firstNumber.length() == 0 || secondNumber.length() == 0 || operator[0] == null) {
                displayText.setText("Error: Missing input");
                return;
            }

            int firsty = Integer.parseInt(firstNumber.toString());
            int sectly = Integer.parseInt(secondNumber.toString());
            int result = 0;

            switch (operator[0]) {
                case "+":
                    result = firsty + sectly;
                    break;
                case "-":
                    result = firsty - sectly;
                    break;
                case "*":
                    result = firsty * sectly;
                    break;
                case "/":
                    if (sectly == 0) {
                        displayText.setText("Error: Can't divide by 0");
                        return;
                    }
                    result = firsty / sectly;
                    break;
            }


            displayText.setText("Total: " + result);


            firstNumber.setLength(0);
            secondNumber.setLength(0);
            operator[0] = null;
            firstNumber.append(result);
            numbertwo.set(false);
        });
        





        frame.setVisible(true);





    }

}
