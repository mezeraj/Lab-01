package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startBt;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50000;
    private final long interval = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBt = this.findViewById(R.id.button);
        startBt.setOnClickListener(this);
        text = this.findViewById(R.id.timer);
        timeElapsedView = this.findViewById(R.id.timeElapsed);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime/1000));
    }

    @Override
    public void onClick(View v) {

        if(startBt.getText().equals("Reset")){

            startBt.setText("Start");
            text.setText("Time Remaining: 50");
            timeElapsedView.setText("Time Elapsed: ");
        }
        else if (!timerHasStarted){
            countDownTimer.start();
            timerHasStarted = true;
            startBt.setText("Stop");
        }
        else {
            startBt.setText("Reset");
            countDownTimer.cancel();
            timerHasStarted = false;
        }

    }
    class MyCountDownTimer extends CountDownTimer
    {
        MyCountDownTimer (long millisInFuture, long countDownInterval)
        {
            super (millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            text.setText("Time Remaining: " + millisUntilFinished/1000);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(timeElapsed/1000) +"."+String.valueOf(timeElapsed%1000));

        }

        @Override
        public void onFinish() {

            text.setText("Time's up!");
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(startTime));

        }
    }


}
