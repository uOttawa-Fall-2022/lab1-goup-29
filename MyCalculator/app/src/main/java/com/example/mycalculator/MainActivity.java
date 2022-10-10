package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn_plus, btn5, btn6, btn7, btn8_minus, btn9, btn10, btn11, btn12_multiply,
            btn_clear, btn0, btn_dot, btn16_divide, btn_equal;
    TextView text_display;

    // This is to evaluate the math expression
    ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engine = new ScriptEngineManager().getEngineByName("rhino");

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8_minus = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btn10 = (Button) findViewById(R.id.button10);
        btn11 = (Button) findViewById(R.id.button11);
        btn12_multiply = (Button) findViewById(R.id.button12);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn0 = (Button) findViewById(R.id.btn0);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn16_divide = (Button) findViewById(R.id.button16);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        text_display = (TextView) findViewById(R.id.textview_input_display);

        setClickListeners();
    }

    private void setClickListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8_minus.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12_multiply.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn16_divide.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        checkError();
        switch (v.getId()) {
            case R.id.btn1:
                addNumber("1");
                break;
            case R.id.btn2:
                addNumber("2");
                break;
            case R.id.btn3:
                addNumber("3");
                break;
            case R.id.btn_plus:
                addNumber("+");
                break;
            case R.id.button5:
                addNumber("4");
                break;
            case R.id.button6:
                addNumber("5");
                break;
            case R.id.button7:
                addNumber("6");
                break;
            case R.id.button8:
                addNumber("-");
                break;
            case R.id.button9:
                addNumber("7");
                break;
            case R.id.button10:
                addNumber("8");
                break;
            case R.id.button11:
                addNumber("9");
                break;
            case R.id.button12:
                addNumber("*");
                break;
            case R.id.btn_clear:
                clear_display();
                break;
            case R.id.btn0:
                addNumber("0");
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            case R.id.button16:
                addNumber("/");
                break;
            case R.id.btn_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                    text_display.setText(result);
                } catch (RuntimeException | ScriptException e) {
                    text_display.setText("Error");
                }
                break;

        }
    }

   private void checkError() {
        if(text_display.getText().equals("Error")) {
            clear_display();
        }
   }

    private String evaluate(String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal = new BigDecimal(result);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    private void clear_display() {
        text_display.setText("");
    }
}