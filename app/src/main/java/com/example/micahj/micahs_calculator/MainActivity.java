package com.example.micahj.micahs_calculator;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends Activity {

    private ImageButton one,
                        two,
                        three,
                        four,
                        five,
                        six,
                        seven,
                        eight,
                        nine,
                        zero,
                        point,
                        clear,
                        plus,
                        minus,
                        multiply,
                        divide,
                      //  power,       Don't forget to implement!!
                        back,
                        equals;

    private TextView viewInput,     // The input TextView will display what the user has input
                     results;       // The results TextView is the result of all the maths

    private String numInput,            // Stores the number user is currently inputting
                   userInput,           // Stores the total input from the user
                   viewResults;         // Displays the math results to the user

    private boolean lastWasOperator;    // This is a flag to determine if the last input was an
                                        // operator, used for the equals button

    private ArrayList<String> input,     // All numbers and operators are appended to this array
                              output;    // All numbers are stored here in shuntYardAlg()

    private Stack<String> operStack;     // All operators are stored here in shuntYardAlg()
    private Stack<Double> nums;

    private boolean isOper(String s){
        switch(s){
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
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
        if(s.equals("^")) return "right";

        return "left";
    }

    // Here I take the user's input and turn it into Reverse Polish Notation
    private void shuntYardAlg(){
        for(int i = 0; i < input.size(); i++){
            String in = input.get(i);
            if(isOper(in)){
                while(!operStack.isEmpty()){
                    if(associative(in).equals("left") && precedence(in) <= precedence(operStack.peek())){
                        output.add(operStack.pop());
                    }
                    else if(associative(in).equals("right") && precedence(in) < precedence(operStack.peek())){
                        output.add(operStack.pop());
                    }
                    else
                        break;
                }
                operStack.push(in);
            } else {
                output.add(in);
            }

            if(i == input.size() - 1){
                while(!operStack.isEmpty())
                    output.add(operStack.pop());
            }
        }
    }

    // This function evaluates the input after it has been put into Reverse Polish Notation
    private void RPN(){
        shuntYardAlg();
        for(int i = 0; i < output.size(); i++){
            String s = output.get(i);
            if(isOper(s)){
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
                    case "^":   // To be implemented at a later date
                        break;
                }
            } else {
                nums.push(Double.parseDouble(s));
            }
        }
    }

    private void num_button(String s){
        numInput = numInput.concat(s);
        userInput = userInput.concat(s);
        viewInput.setText(userInput);
        lastWasOperator = false;
    }

    private void oper_button(String s){
        if(lastWasOperator){
            Toast.makeText(MainActivity.this, "Invalid Operation!", Toast.LENGTH_LONG).show();
        } else {
            input.add(numInput);
            numInput = "";
            input.add(s);
            userInput = userInput.concat(" ").concat(s).concat(" ");
            lastWasOperator = true;
        }
        viewInput.setText(userInput);
    }

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
        viewInput = (TextView) findViewById(R.id.input_value);
        results = (TextView) findViewById(R.id.results);


        userInput = "";
        numInput = "";
        viewResults = "";
        lastWasOperator = false;
        input = new ArrayList<>();
        output = new ArrayList<>();
        operStack = new Stack<>();
        nums = new Stack<>();


        Typeface digital_font = Typeface.createFromAsset(getAssets(), "fonts/DS-DIGI.TTF");

        viewInput.setTypeface(digital_font);
        results.setTypeface(digital_font);


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

        // power.setOnClickListener()      oper_button("^");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userInput.isEmpty()){
                    Toast.makeText(MainActivity.this, "Invalid Operation!", Toast.LENGTH_SHORT).show();
                } else {
                    if(userInput.substring(viewInput.length() - 1).equals(" ")){
                        userInput = userInput.substring(0, viewInput.length() - 3);
                    } else if(isOper(userInput.substring(viewInput.length() - 1))){
                        userInput = userInput.substring(0, viewInput.length() - 1);
                        input.remove(input.size()-1);
                    } else {
                        userInput = userInput.substring(0, viewInput.length() - 1);
                    }
                    //input.remove(input.size() - 1);
                    viewInput.setText(userInput);
                }
            }
        });

        // This button should be pretty self-explanatory, it clears all values everywhere
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.clear();
                output.clear();
                operStack.clear();
                userInput = "";
                numInput = "";
                viewResults = "";
                viewInput.setText("");
                results.setText("");
            }
        });

        // The equals button does the thing
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastWasOperator){
                    Toast.makeText(MainActivity.this, "Enter a number after the operator", Toast.LENGTH_LONG).show();
                } else if(userInput.isEmpty()){
                    // intentionally left blank
                } else {
                    input.add(numInput);
                    numInput = "";
                    RPN();
                    viewResults = Double.toString(nums.peek());
                    results.setText(viewResults);
                }
            }
        });
    }
}