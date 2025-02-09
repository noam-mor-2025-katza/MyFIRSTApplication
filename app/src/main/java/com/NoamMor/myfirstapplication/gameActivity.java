package com.NoamMor.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class gameActivity extends AppCompatActivity {

    // Declare all your views and variables
    EditText yournum;
    Button ok, send,restart,tomain;
    TextView result;
    Context context;
    int gamenum, count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        context=this;
        initViews();
    }

    private void initViews() {
        // Initialize views
        yournum = findViewById(R.id.yourNumber);  // The EditText for user's guess
        ok = findViewById(R.id.okbtn); // The "OK" button
        send = findViewById(R.id.numbtn);  // The "Send" button
        result = findViewById(R.id.result);  // The result TextView
        restart = findViewById(R.id.restartbtn);
        tomain=findViewById(R.id.tomainbtn);

        // Set up the "Send" button click listener to generate the random number
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected range from the RadioGroup
                RadioGroup radioGroup = findViewById(R.id.radioGroupRange);
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                // Check if a radio button is selected
                if (selectedRadioButtonId == -1) {
                    result.setText("Please select a range.");
                    return;
                }
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    radioButton.setEnabled(false); // Disable the RadioButton
                }


                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String range = selectedRadioButton.getText().toString();

                // Initialize the range based on the selection
                int first = 0;
                int second = 0;
                if (range.equals("1-10")) {
                    first = 1;
                    second = 10;
                } else if (range.equals("1-100")) {
                    first = 1;
                    second = 100;
                } else if (range.equals("1-1000")) {
                    first = 1;
                    second = 1000;
                }


                gamenum = (int) (Math.random() * ((second - first) + 1)) + first;

                // Initialize the attempt counter
                count = 0;

                // Inform the user that the game has started
                result.setText("A random number has been generated. Try to guess it!");
            }
        });

        // Set up the "OK" button click listener to check user's guess
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userInput = yournum.getText().toString();
                if (userInput.isEmpty()) {
                    result.setText("Please enter a number.");
                    return;
                }


                int your = Integer.parseInt(userInput);

                count++;
                if (gamenum == 0)
                    result.setText("select a range plz");
                if (gamenum != 0) {
                    if (your == gamenum) {
                        result.setText("Correct! You won in " + count + " tries.");
                      Intent result1 =new Intent();
                      result1.putExtra("num_guesses",count);
                        result1.putExtra("user_name","noam");
                        setResult(RESULT_OK,result1);
                        finish();
                    } else if (your > gamenum) {
                        result.setText("Wrong. The number is too big.");
                    } else {
                        result.setText("Wrong. The number is too small.");
                    }
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Result will appear here");


                yournum.setText("");


                count = 0;


                gamenum = 0;
                RadioGroup radioGroup = findViewById(R.id.radioGroupRange);
                radioGroup.clearCheck();

            }
        });
        tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, MainActivity.class);
                startActivity(intent);
            }
            });
        }
    }
