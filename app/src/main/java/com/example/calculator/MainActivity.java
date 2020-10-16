package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String equation = "";                                   //for temporarily storing result strings at different point of time
    private EditText screen;                                        //for storing result
    private EditText input;                                         //for storing user result, shown above result
    final ArrayList<Character> op= new ArrayList<>();               //for storing operators, to pop off if more than 2 elements are inserted simultaneously
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = findViewById(R.id.one);                        //instantiating each button by their id given in xml file
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button zero = findViewById(R.id.zero);
        Button equals = findViewById(R.id.equals);
        Button minus = findViewById(R.id.subtract);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button plus = findViewById(R.id.plus);
        Button dot = findViewById(R.id.dot);
        Button del = findViewById(R.id.DEL);
        Button dblZero = findViewById(R.id.doubleZero);
        screen = findViewById(R.id.usr_result);
        input = findViewById(R.id.usrInput);

        op.add('+');
        op.add('*');
        op.add('/');
        op.add('-');

        one.setOnClickListener(new View.OnClickListener() {                                         //adding onclick listener to each button and appending it to screen if certain condition match outs
        @Override
        public void onClick(View view) {
            screen.setText(screen.getText() + "1");
        }
        });
        two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                screen.setText(screen.getText() + "2");
        }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "0");
            }
        });
        dblZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText(screen.getText() + "00");
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = screen.getText().toString();
                if(equation.length() != 0){
                    while(op.contains(equation.charAt(equation.length()-1))) {          //in case of something like "32+-" + "+", we want to keep terminating until number comes
                        equation = equation.substring(0, equation.length()-1);
                    }
                    screen.setText(equation + '+');
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = screen.getText().toString();
                if(equation.charAt(equation.length()-1) != '-') {
                    screen.setText(screen.getText() + "-");
                }
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = screen.getText().toString();
                if(equation.length() != 0){
                    while(op.contains(equation.charAt(equation.length()-1))) {
                        equation = equation.substring(0, equation.length()-1);
                    }
                    screen.setText(equation + '*');
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = screen.getText().toString();
                if(equation.length() != 0){
                    while(op.contains(equation.charAt(equation.length()-1))) {
                        equation = equation.substring(0, equation.length()-1);
                    }
                    screen.setText(equation + '/');
                }
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = screen.getText().toString();
                //if('.' != equation.charAt(equation.length() - 1)) {
                if(checkForMultiDots(screen.getText().toString())){                         //check for multi dots function for checking if current input has multiple dots
                    screen.setText(screen.getText() + ".");
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation = screen.getText().toString();
                if (!equation.equals("")){
                    screen.setText(screen.getText().toString().substring(0, equation.length() - 1));        //pop last element using substring
                }
            }
        });

        del.setOnLongClickListener(new View.OnLongClickListener() {                                 //long click del if you want to clear whole screen line
            @Override
            public boolean onLongClick(View view) {
                screen.setText(null);
                return true;
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempStr = screen.getText().toString();
                input.setText(tempStr);
                if(op.contains(tempStr.charAt(tempStr.length()-1)))                                 //if last char in parsed string is a operator, we dont need it, pop
                    tempStr = tempStr.substring(0, tempStr.length()-1);
                Double tmpDbl = eval(tempStr);                                                      //returns converted string in double format
                tmpDbl = (double)Math.round(tmpDbl*100.0)/100.0;                                    //for rounding off to 2 digits
                if(tmpDbl%1 == 0)                                                                   //if double is an integer, we dont print after decimal formats
                    screen.setText(Integer.toString((int) Math.round(eval(tempStr))));
                else screen.setText(Double.toString(tmpDbl));                                       //else simply print double after conversion to string
            }
        });
        }

        private boolean checkForMultiDots(String str) {
            int i=0;
            String res = fetchLastNumber(str);                                                       //for fetching last number in the given string
            for(; i<res.length()-1; i++) {                                                          //if any number in the given string is a decimal we return false
                if(str.charAt(i) == '.')
                    return false;
            }
            return true;                                                                            //else return true
        }

        private String fetchLastNumber(String str) {
            String res = "";
            for(int i=str.length()-1; i>=0 && !op.contains(str.charAt(i)); i--) {                   //while i is in range and given char doesnt belong to operators, add char to res
                res += (str.charAt(i));
            }
            return res;
        }

    public static double eval(final String str) {                                                   //evaluate given string
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}