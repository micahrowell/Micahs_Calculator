package com.example.micahj.micahs_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private ImageButton one;
    private ImageButton two;
    private ImageButton three;
    private ImageButton four;
    private ImageButton five;
    private ImageButton six;
    private ImageButton seven;
    private ImageButton eight;
    private ImageButton nine;
    private ImageButton zero;
    private ImageButton point;
    private ImageButton clear;
    private ImageButton plus;
    private ImageButton minus;
    private ImageButton multiply;
    private ImageButton divide;
    private ImageButton power;      // Don't forget to implement!!
    private ImageButton back;
    private ImageButton equals;
    // The input TextView will display what the user has input
    private TextView input;
    // The results TextView is the result of all the maths
    private TextView results;

    private class Calc {
        public String viewInput;            // Displays the input to the user
        public String viewResults;          // Displays the math results to the user

        public boolean lastWasOperator;     // This is a flag to determine if the last input was an
        // operator, used for the equals button

        public ArrayList<String> input;     // All numbers and operators are appended to this array
        public ArrayList<String> output;    // All numbers are stored here in shuntYardAlg()
        public Stack<String> operStack;     // All operators are stored here in shuntYardAlg()
        public Stack<Double> nums;

        private boolean isNum(String s){
            switch(s){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    return true;
                default:
                    return false;
            }
        }

        private int precedence(String s){
            switch(s){
                case "^":
                    return 3;
                case "*":
                case "/":
                    return 2;
                case "+":
                case "-":
                    return 1;
                default:
                    return 0;
            }
        }

        private String associative(String s){
            if (s.equals("^")) return "right";
            return "left";
        }

        private void shuntYardAlg(){
            for(int i = 0; i < input.size(); i++){
                String in = input.get(i);
                if(isNum(in)){
                    output.add(in);
                } else {
                    while(!operStack.isEmpty()){
                        if(associative(in).equals("left") && precedence(in) <= precedence(operStack.peek())){
                            output.add(operStack.pop());
                        }
                        if(associative(in).equals("right") && precedence(in) < precedence(operStack.peek())){
                            output.add(operStack.pop());
                        }
                    }
                    operStack.push(in);
                }
                if(i == input.size() - 1){
                    while(!operStack.isEmpty())
                        output.add(operStack.pop());
                }
            }
        }

        private void RPN(){
            shuntYardAlg();
            for(int i = 0; i < output.size(); i++){
                String s = output.get(i);
                if(isNum(s))
                    nums.push(Double.parseDouble(s));
                else{
                    Double  d1 = nums.pop(),
                            d2 = nums.pop();

                    switch (s){
                        case "+": nums.push(d1 + d2);
                            break;
                        case "-": nums.push(d2 - d1);
                            break;
                        case "*": nums.push(d2 * d1);
                            break;
                        case "/": nums.push(d2 / d1);
                            break;
                    }
                }
            }
        }
    }

    Calc math = new Calc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (ImageButton) findViewById(R.id.button_1);
        two = (ImageButton) findViewById(R.id.button_2);
        three = (ImageButton) findViewById(R.id.button_3);
        four = (ImageButton) findViewById(R.id.button_4);
        five = (ImageButton) findViewById(R.id.button_5);
        six = (ImageButton) findViewById(R.id.button_6);
        seven = (ImageButton) findViewById(R.id.button_7);
        eight = (ImageButton) findViewById(R.id.button_8);
        nine = (ImageButton) findViewById(R.id.button_9);
        zero = (ImageButton) findViewById(R.id.button_0);
        point = (ImageButton) findViewById(R.id.point);
        clear = (ImageButton) findViewById(R.id.clear_button);
        plus = (ImageButton) findViewById(R.id.plus_button);
        minus = (ImageButton) findViewById(R.id.minus_button);
        multiply = (ImageButton) findViewById(R.id.mult_button);
        divide = (ImageButton) findViewById(R.id.divide_button);
        // power = (ImageButton) findViewbyID(R.id.power_button);
        back = (ImageButton) findViewById(R.id.back_button);
        equals = (ImageButton) findViewById(R.id.equals);
        input = (TextView) findViewById(R.id.input_value);
        results = (TextView) findViewById(R.id.results);


        math.viewInput = "";
        math.viewResults = "";
        math.lastWasOperator = false;
        math.input = new ArrayList<>();
        math.output = new ArrayList<>();
        math.operStack = new Stack<>();
        math.nums = new Stack<>();


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button("0");
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_button(".");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper_button("+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper_button("-");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper_button("*");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oper_button("/");
            }
        });

        // power.setOnClickListener(v) // oper_button("^");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(math.viewInput.isEmpty()){
                    Toast.makeText(MainActivity.this, "Invalid Operation!", Toast.LENGTH_SHORT).show();
                } else {
                    if(math.viewInput.substring(math.viewInput.length() - 1).equals(" ")){
                        math.viewInput = math.viewInput.substring(0, math.viewInput.length() - 3);
                    } else {
                        math.viewInput = math.viewInput.substring(0, math.viewInput.length() - 1);
                    }
                    math.input.remove(math.input.size() - 1);
                    input.setText(math.viewInput);
                }
            }
        });

        // This button should be pretty self-explanatory, it clears all values everywhere
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                math.input.clear();
                math.output.clear();
                math.operStack.clear();
                math.viewInput = "";
                math.viewResults = "";
                input.setText("");
                results.setText("");
            }
        });

        // The equals button calls the functions in the Math class that do all the math
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(math.lastWasOperator){
                    Toast.makeText(MainActivity.this, "Enter a number after the operator", Toast.LENGTH_LONG).show();
                } else {
                    math.RPN();
                    results.setText(Double.toString(math.nums.peek()));
                }
            }
        });
    }

    private void num_button(String s){
        math.input.add(s);
        math.viewInput = math.viewInput.concat(s);
        input.setText(math.viewInput);
        math.lastWasOperator = false;
    }

    private void oper_button(String s){
        if(math.lastWasOperator){
            Toast.makeText(MainActivity.this, "Invalid Operation!", Toast.LENGTH_LONG).show();
        } else {
            math.input.add(s);
            math.viewInput = math.viewInput.concat(" ").concat(s).concat(" ");
            math.lastWasOperator = true;
        }
        input.setText(math.viewInput);
    }
}