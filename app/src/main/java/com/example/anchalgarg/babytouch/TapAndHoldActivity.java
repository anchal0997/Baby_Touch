package com.example.anchalgarg.babytouch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageButton;

import static java.lang.System.currentTimeMillis;

public class TapAndHoldActivity extends AppCompatActivity {

    GifImageButton mBaloon1;
    GifImageButton mBaloon2;
    GifImageButton mBaloon3;
    GifImageButton mBaloon4;

    Button mStartStop;
    Button mMenu;
    long starttime=0L;
    long time=0L;
    int min,secs;
    WinningFragment frag = new WinningFragment();
    FrameLayout fr ;
    FragmentManager mananger;
    FragmentTransaction trans;
    int[] m = {0};

    long timeSwap=0L;
    MediaPlayer mTimeUp=new MediaPlayer();

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
                if(m[0]!=4)
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
        }
    };

    TextView mStopWatch;

    int time1;
    MediaPlayer mediaPlayer;
    public int t=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_and_hold);

        mBaloon1=(GifImageButton) findViewById(R.id.baloon1);
        mBaloon2=(GifImageButton) findViewById(R.id.baloon2);
        mBaloon3=(GifImageButton) findViewById(R.id.baloon3);
        mBaloon4=(GifImageButton) findViewById(R.id.baloon4);

        mStartStop=(Button)findViewById(R.id.strtStop);
        mMenu=(Button)findViewById(R.id.menuBtn);
        mStopWatch=(TextView)findViewById(R.id.stopWatch);


        mediaPlayer=new MediaPlayer();

        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    mBaloon1.setVisibility(View.VISIBLE);
                    mBaloon2.setVisibility(View.VISIBLE);
                    mBaloon3.setVisibility(View.VISIBLE);
                    mBaloon4.setVisibility(View.VISIBLE);
                    Log.d("aagya","-____--__");

                    mBaloon1.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            int X = (int) event.getRawX();
                            int Y = (int) event.getRawY();
                            switch (event.getAction()) {

                                case MotionEvent.ACTION_DOWN:
                                    time1=(int)currentTimeMillis();
                                    Log.d("jjj",""+time);
                                    break;
                                case MotionEvent.ACTION_MOVE:

                                    break;
                                case MotionEvent.ACTION_UP:
                                    int time2=(int)currentTimeMillis();
                                    Log.d("jjj",""+time2);
                                    int diff=time2-time1;
                                    if((diff/100.)>=1.5)
                                    {
                                        mBaloon1.setVisibility(View.INVISIBLE);
                                        m[0]++;
                                        mediaPlayer = MediaPlayer.create(TapAndHoldActivity.this, R.raw.pop);
                                        mediaPlayer.start();
                                    }
                                    if(m[0] ==4)
                                    {
                                        trans = mananger.beginTransaction();
                                        trans.add(R.id.fragment_container, frag);
                                        trans.commit();
                                        mStartStop.setVisibility(View.INVISIBLE);
                                        mMenu.setVisibility(View.INVISIBLE);
                                        Log.d("uuu","-----------");

                                    }
                                    break;

                            }
                            return false;
                        }
                    });
                    mBaloon2.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            int X = (int) event.getRawX();
                            int Y = (int) event.getRawY();
                            switch (event.getAction()) {

                                case MotionEvent.ACTION_DOWN:
                                    time1=(int)currentTimeMillis();
                                    Log.d("jjj",""+time1);
                                    break;
                                case MotionEvent.ACTION_MOVE:

                                    break;
                                case MotionEvent.ACTION_UP:
                                    int time2=(int)currentTimeMillis();
                                    Log.d("jjj",""+time2);
                                    int diff=time2-time1;
                                    if((diff/100.)>=1.5)
                                    {
                                        mBaloon2.setVisibility(View.INVISIBLE);
                                        m[0]++;
                                        mediaPlayer = MediaPlayer.create(TapAndHoldActivity.this, R.raw.pop);
                                        mediaPlayer.start();
                                    }
                                    if(m[0] ==4)
                                    {
                                        trans = mananger.beginTransaction();
                                        trans.add(R.id.fragment_container, frag);
                                        trans.commit();
                                        mStartStop.setVisibility(View.INVISIBLE);
                                        mMenu.setVisibility(View.INVISIBLE);
                                        Log.d("uuu","-----------");

                                    }
                                    break;

                            }
                            return false;
                        }
                    });
                    mBaloon3.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            int X = (int) event.getRawX();
                            int Y = (int) event.getRawY();
                            switch (event.getAction()) {

                                case MotionEvent.ACTION_DOWN:
                                    time1=(int)currentTimeMillis();
                                    Log.d("jjj",""+time1);
                                    break;
                                case MotionEvent.ACTION_MOVE:

                                    break;
                                case MotionEvent.ACTION_UP:
                                    int time2=(int)currentTimeMillis();
                                    Log.d("jjj",""+time2);
                                    int diff=time2-time1;
                                    if((diff/100.)>=1.5)
                                    {
                                        mBaloon3.setVisibility(View.INVISIBLE);
                                        m[0]++;
                                        mediaPlayer = MediaPlayer.create(TapAndHoldActivity.this, R.raw.pop);
                                        mediaPlayer.start();
                                    }
                                    if(m[0] ==4)
                                    {
                                        trans = mananger.beginTransaction();
                                        trans.add(R.id.fragment_container, frag);
                                        trans.commit();
                                        mStartStop.setVisibility(View.INVISIBLE);
                                        mMenu.setVisibility(View.INVISIBLE);
                                        Log.d("uuu","-----------");

                                    }
                                    break;

                            }
                            return false;
                        }
                    });
                    mBaloon4.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            int X = (int) event.getRawX();
                            int Y = (int) event.getRawY();
                            switch (event.getAction()) {

                                case MotionEvent.ACTION_DOWN:
                                    time1=(int)currentTimeMillis();
                                    Log.d("jjj",""+time1);
                                    break;
                                case MotionEvent.ACTION_MOVE:

                                    break;
                                case MotionEvent.ACTION_UP:
                                    int time2=(int)currentTimeMillis();
                                    Log.d("jjj",""+time2);
                                    int diff=time2-time1;
                                    if((diff/100.)>=1.5)
                                    {
                                        mBaloon4.setVisibility(View.INVISIBLE);
                                        m[0]++;
                                        mediaPlayer = MediaPlayer.create(TapAndHoldActivity.this, R.raw.pop);
                                        mediaPlayer.start();
                                    }

                                if(m[0] ==4)
                                {
                                    trans = mananger.beginTransaction();
                                    trans.add(R.id.fragment_container, frag);
                                    trans.commit();
                                    mStartStop.setVisibility(View.INVISIBLE);
                                    mMenu.setVisibility(View.INVISIBLE);
                                    Log.d("uuu","-----------");

                                }
                                    break;

                            }

                            return false;

                        }
                    });


                    if(m[0] ==4)
                    {
                        trans = mananger.beginTransaction();
                        trans.add(R.id.fragment_container, frag);
                        trans.commit();
                        mStartStop.setVisibility(View.INVISIBLE);
                        mMenu.setVisibility(View.INVISIBLE);
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

                Intent TapIntent=new Intent(TapAndHoldActivity.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });



    }

}
