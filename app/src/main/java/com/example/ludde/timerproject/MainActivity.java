package com.example.ludde.timerproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button mStart, mStop, mSwitch;
    private ChessTimer mChessTimer;

    private TextView mPlayerOne, mPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mStart = (Button)findViewById(R.id.startButton);
        this.mStop = (Button)findViewById(R.id.stopButton);
        this.mSwitch = (Button)findViewById(R.id.switchButton);

        this.mPlayerOne = (TextView)findViewById(R.id.playerOne);
        this.mPlayerTwo = (TextView)findViewById(R.id.playerTwo);


        this.mChessTimer = new ChessTimer(new ChessTimer.ChessTimerListener() {
            @Override
            public void onPlayerOneTimeUpdate(long time) {
                int sec = (int)time/1000;
                int millisec = (int)time%1000;
                int minute = 0;
                if(sec > 60)
                {
                    minute = sec / 60;
                    sec = sec%60;
                }
                mPlayerOne.setText(String.valueOf(minute) + ":" + String.valueOf(sec) + ":" + String.valueOf(millisec));
                mPlayerOne.setBackgroundColor(Color.parseColor("#00ff00"));
                mPlayerTwo.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void onPlayerTwoTimeUpdate(long time) {
                int sec = (int)time/1000;
                int millisec = (int)time%1000;
                int minute = 0;
                if(sec > 60)
                {
                    minute = sec / 60;
                    sec = sec%60;
                }
                mPlayerTwo.setText(String.valueOf(minute) + ":" + String.valueOf(sec) + ":" + String.valueOf(millisec));
                mPlayerOne.setBackgroundColor(Color.TRANSPARENT);
                mPlayerTwo.setBackgroundColor(Color.parseColor("#00ff00"));
            }
        }, this);

        this.mStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mChessTimer.start();
            }
        });

        this.mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChessTimer.stop();
            }
        });

        this.mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChessTimer.switchPlayer();
            }
        });
    }
}
