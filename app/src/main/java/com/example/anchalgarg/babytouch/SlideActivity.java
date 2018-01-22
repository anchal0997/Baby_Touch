package com.example.anchalgarg.babytouch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.R.attr.x;

public class SlideActivity extends AppCompatActivity {

    Button mMenu;
    Button mStartStop;
    long starttime=0L;
    long time=0L;
    int min,secs;
    long timeSwap=0L;
    public int t=0;
    MediaPlayer mTimeUp=new MediaPlayer();
    MediaPlayer mtada=new MediaPlayer();
    long updateTime=0L;

    final Handler customHandler=new Handler();
    Runnable updateTimerThred=new Runnable() {
        @Override
        public void run() {
            time= SystemClock.uptimeMillis()-starttime;
            updateTime=timeSwap+time;
            secs=(int)(updateTime/1000);
            min=secs/60;
            secs%=60;
            mStopWatch.setText(""+min+":"+String.format("%2d",secs));
            customHandler.postDelayed(this,0);
            if(min>=3)
            {

                mTimeUp.start();
                mStartStop.setText("START");
                t=0;
                mMenu.setVisibility(View.VISIBLE);
                time=0;
                timeSwap+=time;
                customHandler.removeCallbacks(updateTimerThred);

            }
        }
    };

    TextView mStopWatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        mMenu=(Button)findViewById(R.id.menuBtn);
        mTimeUp=MediaPlayer.create(SlideActivity.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);

        mtada=MediaPlayer.create(SlideActivity.this,R.raw.tada);
        mStopWatch=(TextView)findViewById(R.id.stopWatch);
        final int[] x = {0};

        final SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.incrementProgressBy(10);
        seekBar.setMax(200);

        final SeekBar seekBar1=(SeekBar)findViewById(R.id.seekBar);
        seekBar1.setProgress(0);
        seekBar1.incrementProgressBy(10);
        seekBar1.setMax(200);

        seekBar.setEnabled(false);
        seekBar1.setEnabled(false);

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    seekBar.setEnabled(true);
                    seekBar1.setEnabled(true);
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if(progress>=190)
                            {
                                Log.d("joker","--"+progress);
                                x[0]++;
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });



                    seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if(progress>=190)
                            {
                                Log.d("joker","--"+progress);
                                x[0]++;
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    if(x[0] ==2)
                    {
                        Log.d("uuu","-----------");
                    }
                    mStartStop.setText("STOP");
                    mMenu.setVisibility(View.INVISIBLE);
                    starttime= SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThred,0);
                    t=1;
                }
                else
                {
                    seekBar.setEnabled(false);
                    seekBar1.setEnabled(false);


                    mStartStop.setText("START");
                    t=0;
                    mMenu.setVisibility(View.VISIBLE);
                    time=0;
                    timeSwap+=time;
                    customHandler.removeCallbacks(updateTimerThred);
                }
            }
        });


        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TapIntent=new Intent(SlideActivity.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });



    }
}
