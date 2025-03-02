package com.NoamMor.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, gamebtn, takanon;
    Context context;
    TextView tv1;
    SeekBar sb1;
    Switch sw;
    final int START_GAME = 1950;
    ImageView iv;
    ImageView iv1;
    SharedPreferences sp;

    Button btnSave;

    EditText etFname,etLname;

    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        tv1 = findViewById(R.id.tv1);
        context = this;
        initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == START_GAME) {
            if (resultCode == RESULT_OK) {
                int i = data.getIntExtra("num_guesses", -1);
                String str = data.getStringExtra("user_name");
                Toast.makeText(this, "game finished counter = " + i + " userName: " + str, Toast.LENGTH_LONG).show();
            }
        }

        if (resultCode == RESULT_OK && data != null) {
            boolean isChecked = data.getBooleanExtra("checkbox_checked", false);
            if (isChecked) {
                gamebtn.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initViews() {
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b1.setOnClickListener(view -> Toast.makeText(context, "b1", Toast.LENGTH_SHORT).show());
        b2.setOnClickListener(view -> Toast.makeText(context, "b2", Toast.LENGTH_SHORT).show());

        sb1 = findViewById(R.id.sb1);
        sb1.setProgress(100);
        sb1.setMax(100);

        sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener((compoundButton, b) -> {});

        iv = findViewById(R.id.iv);
        iv1 = findViewById(R.id.iv1);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float alpha = (float) i / 100;
                float alpha1 = 1 - alpha;
                iv.setAlpha(alpha);
                iv1.setAlpha(alpha1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(view -> {
            Intent intent = new Intent(context, LinearActivty.class);
            startActivity(intent);
        });

        gamebtn = findViewById(R.id.gamebtn);
        gamebtn.setVisibility(View.GONE);
        gamebtn.setOnClickListener(view -> {
            Intent intent = new Intent(context, gameActivity.class);
            startActivityForResult(intent, START_GAME);
        });

        takanon = findViewById(R.id.takanon);
        takanon.setOnClickListener(view -> {
            Intent intent = new Intent(context, takanonActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        btnSave=(Button)findViewById(R.id.btnSubmit);

        btnSave.setOnClickListener(this);

        etFname=(EditText)findViewById(R.id.etFname);

        etLname=(EditText)findViewById(R.id.etLname);

        sp=getSharedPreferences("details1",0);

        tvDisplay=(TextView)findViewById(R.id.tvDisplay);

        String strfname = sp.getString("fname",null);

        String strlname=sp.getString("lname",null);

        if(strlname!=null&&strfname!=null)

        {

            tvDisplay.setText("welcome " + strfname + " " + strlname);

        }

    }



    @Override

    public void onClick(View v) {



        if(btnSave==v)

        {

            SharedPreferences.Editor editor=sp.edit();

            editor.putString("fname",etFname.getText().toString());

            editor.putString("lname",etLname.getText().toString());

            editor.commit();

        }
}}






