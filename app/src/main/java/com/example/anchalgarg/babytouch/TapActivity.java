package com.example.anchalgarg.babytouch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TapActivity extends AppCompatActivity {
    MediaPlayer mtada=new MediaPlayer();
    Button mMenu;
    Button mStartStop;
    ImageButton mRedBall;
    long starttime=0L;
    long time=0L;
    int[] m = {0};
    int min,secs;
    ImageButton mRedBall1;
    ImageButton mRedBall2;
    ImageButton mRedBall3;

    WinningFragment frag = new WinningFragment();
    FrameLayout fr ;
    FragmentManager mananger;
    FragmentTransaction trans;


    long timeSwap=0L;
    MediaPlayer mTimeUp=new MediaPlayer();

    long updateTime=0L;
    final Handler customHandler=new Handler();
    Runnable updateTimerThred=new Runnable() {
        @Override
        public void run() {
            time=SystemClock.uptimeMillis()-starttime;
            updateTime=timeSwap+time;
             secs=(int)(updateTime/1000);
             min=secs/60;
            secs%=60;
            mStopWatch.setText(""+min+":"+String.format("%2d",secs));
            customHandler.postDelayed(this,0);
            if(min>=3)
            {
                if(m[0]!=4) {
                    mTimeUp.start();
                    mStartStop.setText("START");
                    t = 0;
                    mMenu.setVisibility(View.VISIBLE);
                    time = 0;
                    timeSwap += time;
                    customHandler.removeCallbacks(updateTimerThred);
                }
            }
        }
    };

    TextView mStopWatch;
    public int t=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        mMenu=(Button)findViewById(R.id.menuBtn);
        mRedBall=(ImageButton)findViewById(R.id.red_ball);
        mRedBall1=(ImageButton)findViewById(R.id.red_ball1);
        mRedBall2=(ImageButton)findViewById(R.id.redball2);
        mRedBall3=(ImageButton)findViewById(R.id.red_ball3);
        mTimeUp=MediaPlayer.create(TapActivity.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);
        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);

        mtada=MediaPlayer.create(TapActivity.this,R.raw.tada);
        mStopWatch=(TextView)findViewById(R.id.stopWatch);

        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    mRedBall.setVisibility(View.VISIBLE);
                    mRedBall1.setVisibility(View.VISIBLE);
                    mRedBall2.setVisibility(View.VISIBLE);
                    mRedBall3.setVisibility(View.VISIBLE);
                    Log.d("aagya","-____--__");
                    MediaPlayer finalMtada = new MediaPlayer();
                    finalMtada=MediaPlayer.create(TapActivity.this,R.raw.tada);
                    final MediaPlayer finalMtada1 = finalMtada;
                    mRedBall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall.setVisibility(View.INVISIBLE);
                            if(m[0] ==4)
                            {
                                trans = mananger.beginTransaction();
                                trans.add(R.id.fragment_container, frag);
                                trans.commit();
                                mStartStop.setVisibility(View.INVISIBLE);
                                mMenu.setVisibility(View.INVISIBLE);
                                Log.d("uuu","-----------");
                            }
                            Log.d("HELLO",""+m[0]);

                        }
                    });
                    mRedBall1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall1.setVisibility(View.INVISIBLE);
                            if(m[0] ==4)
                            {
                                trans = mananger.beginTransaction();
                                trans.add(R.id.fragment_container, frag);
                                trans.commit();
                                mStartStop.setVisibility(View.INVISIBLE);
                                mMenu.setVisibility(View.INVISIBLE);
                                Log.d("uuu","-----------");
                            }
                            Log.d("HELLO",""+m[0]);

                        }
                    });
                    mRedBall2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall2.setVisibility(View.INVISIBLE);
                            if(m[0] ==4)
                            {
                                trans = mananger.beginTransaction();
                                trans.add(R.id.fragment_container, frag);
                                trans.commit();
                                mStartStop.setVisibility(View.INVISIBLE);
                                mMenu.setVisibility(View.INVISIBLE);
                                Log.d("uuu","-----------");
                            }
                            Log.d("HELLO",""+m[0]);

                        }
                    });
                    mRedBall3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall3.setVisibility(View.INVISIBLE);
                            if(m[0] ==4)
                            {
                                trans = mananger.beginTransaction();
                                trans.add(R.id.fragment_container, frag);
                                trans.commit();
                                mStartStop.setVisibility(View.INVISIBLE);
                                mMenu.setVisibility(View.INVISIBLE);
                                Log.d("uuu","-----------");
                            }
                            Log.d("HELLO",""+m[0]);

                        }
                    });
                    Log.d("HELLO",""+m[0]);

                    if(m[0] ==4)
                    {
                        trans = mananger.beginTransaction();
                        trans.add(R.id.fragment_container, frag);
                        trans.commit();
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

        ViewGroup.LayoutParams vg_lp = mRedBall.getLayoutParams();

        // Make sure we are in RelativeLayout
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width=metrics.widthPixels;
        int height=metrics.heightPixels;

        Random rand = new Random();
        int random_width = rand.nextInt(width/2) + 2*vg_lp.width;

        int random_height = rand.nextInt(height/2) + 2*vg_lp.height;
        Log.d("height11",""+random_width+" "+random_height);
        if (vg_lp instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
            rl_lp.leftMargin =  random_width;
            rl_lp.topMargin =  random_height;
            mRedBall.setLayoutParams(rl_lp);
        }
         vg_lp = mRedBall1.getLayoutParams();
        random_width = random_width+2*vg_lp.width;
        if (vg_lp instanceof RelativeLayout.LayoutParams) {

            RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
            rl_lp.leftMargin =  random_width;
            rl_lp.topMargin =  random_height;
            mRedBall.setLayoutParams(rl_lp);
        }

        vg_lp = mRedBall2.getLayoutParams();
        random_width = random_width-2*vg_lp.width;
        random_height = random_height + 2*vg_lp.height;
        if (vg_lp instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
            rl_lp.leftMargin =  random_width;
            rl_lp.topMargin =  random_height;
            mRedBall.setLayoutParams(rl_lp);
        }
        vg_lp = mRedBall3.getLayoutParams();

        random_width = random_width+2*vg_lp.width;
        if (vg_lp instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
            rl_lp.leftMargin =  random_width;
            rl_lp.topMargin =  random_height;
            mRedBall.setLayoutParams(rl_lp);
        }



        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TapIntent=new Intent(TapActivity.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });

    }
}
