package com.example.umpirebuddy20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_strikes, txt_balls, txt_outs;
    Button btn_strikes, btn_balls, btn_outs, btn_reset, btn_about, btn_exit;
    AlertDialog.Builder outBuilder, walkBuilder;
    int strikes, balls, outs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_strikes = findViewById(R.id.txt_strikes);
        txt_balls = findViewById(R.id.txt_balls);
        txt_outs = findViewById(R.id.txt_outs);
        btn_strikes = findViewById(R.id.btn_strikes);
        btn_balls = findViewById(R.id.btn_balls);
        btn_outs = findViewById(R.id.btn_outs);
        btn_reset = findViewById(R.id.btn_reset);
        btn_about = findViewById(R.id.btn_about);
        btn_exit = findViewById(R.id.btn_exit);
        outBuilder = new AlertDialog.Builder(this);
        walkBuilder = new AlertDialog.Builder(this);

        strikes = 0;
        balls = 0;
        outs = 0;

        outBuilder.setTitle(R.string.out_dialog_title)
                .setMessage(R.string.out_dialog_msg)
                .setCancelable(true);

        walkBuilder.setTitle(R.string.walk_dialog_title)
                .setMessage(R.string.walk_dialog_msg)
                .setCancelable(true);

        final AlertDialog outAlert = outBuilder.create();
        final AlertDialog walkAlert = walkBuilder.create();

        btn_strikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_strikes.setText(String.valueOf(++strikes));

                if (strikes == 3) {
                    freezeRecording();
                    outAlert.show();
                }
            }
        });

        btn_balls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_balls.setText(String.valueOf(++balls));

                if (balls == 4) {
                    freezeRecording();
                    walkAlert.show();
                }
            }
        });

        btn_outs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_outs.setText(String.valueOf(++outs));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strikes = 0;
                balls = 0;
                outs = 0;

                txt_strikes.setText(String.valueOf(strikes));
                txt_balls.setText(String.valueOf(balls));
                txt_outs.setText(String.valueOf(outs));

                unfreezeRecording();
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void freezeRecording() {
        btn_strikes.setEnabled(false);
        btn_balls.setEnabled(false);
        btn_outs.setEnabled(false);
    }

    private void unfreezeRecording() {
        btn_strikes.setEnabled(true);
        btn_balls.setEnabled(true);
        btn_outs.setEnabled(true);
    }
}
