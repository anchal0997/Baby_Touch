package com.example.anchalgarg.babytouch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TapOnMovingActivity extends AppCompatActivity {
    ImageButton mRedBall;
    ImageButton mRedBall1;
    ImageButton mRedBall2;
    ImageButton mRedBall3;
    Handler handler ;
    Runnable run;
    Handler handler1 ;
    Runnable run1;
    RelativeLayout relativeLayout;
    Button mMenu;
    Button mStartStop;
    long starttime=0L;
    long time=0L;
    int min,secs;
    long timeSwap=0L;
    TextView mStopWatch;
    MediaPlayer mTimeUp=new MediaPlayer();
    MediaPlayer mYipee=new MediaPlayer();
    long updateTime=0L;
    public int t=0;

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
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_on_moving);
        relativeLayout=(RelativeLayout)findViewById(R.id.relative_layout);
        mRedBall=(ImageButton)findViewById(R.id.redBall);
        mRedBall1=(ImageButton)findViewById(R.id.redBall1);
        mRedBall2=(ImageButton)findViewById(R.id.redBall2);
        mRedBall3=(ImageButton)findViewById(R.id.redBall3);
        mStopWatch=(TextView)findViewById(R.id.stop_Watch);
        mMenu=(Button)findViewById(R.id.menuBtn);
        mTimeUp=MediaPlayer.create(TapOnMovingActivity.this,R.raw.timeup);
        mYipee=MediaPlayer.create(TapOnMovingActivity.this,R.raw.cheer);
        mStartStop=(Button)findViewById(R.id.strtStop);
        final int[] m = {0};

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
                    finalMtada=MediaPlayer.create(TapOnMovingActivity.this,R.raw.tada);
                    final MediaPlayer finalMtada1 = finalMtada;
                    mRedBall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall.setVisibility(View.INVISIBLE);
                            Log.d("JOO",""+m[0]);
                            if(m[0] ==4)
                            {
                                Log.d("JAY",""+"----");
                                m[0]=0;
                                mYipee.start();
                            }
                        }
                    });
                    mRedBall1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall1.setVisibility(View.INVISIBLE);
                            Log.d("JOO",""+m[0]);
                            if(m[0] ==4)
                            {
                                Log.d("JAY",""+"----");
                                m[0]=0;
                                mYipee.start();
                            }
                        }
                    });
                    mRedBall2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            Log.d("JOO",""+m[0]);
                            mRedBall2.setVisibility(View.INVISIBLE);
                            if(m[0] ==4)
                            {
                                Log.d("JAY",""+"----");
                                m[0]=0;
                                mYipee.start();
                            }
                        }
                    });
                    mRedBall3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m[0]++;
                            finalMtada1.start();
                            mRedBall3.setVisibility(View.INVISIBLE);
                            Log.d("JOO",""+m[0]);
                            if(m[0] ==4)
                            {
                                Log.d("JAY",""+"----");
                                mYipee.start();
                                m[0]=0;
                            }
                        }
                    });




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


        DisplayMetrics dm = new DisplayMetrics();
        handler = new Handler();
        handler1 = new Handler();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width=dm.widthPixels;
        final int height=dm.heightPixels;
        run = new Runnable(){
            @Override
            public void run() {
                Animator animator = ObjectAnimator.ofFloat(mRedBall, View.X,0 ,width).setDuration(5000);
                Animator animator1 = ObjectAnimator.ofFloat(mRedBall, View.Y,0 ,height).setDuration(5000);
                Log.d("acha1",""+mRedBall);
                Log.d("achaa1",""+animator);
                animator.start();
                animator1.start();
                handler.postDelayed(this,5300);

            }
        };
        handler.post(run);
        run1 = new Runnable(){
            @Override
            public void run() {
                Animator animator = ObjectAnimator.ofFloat(mRedBall1, View.X,0 ,width).setDuration(2500);
                //Animator animator1 = ObjectAnimator.ofFloat(mRedBall1, View.Y,0 ,height).setDuration(5000);
                Log.d("acha",""+mRedBall);
                Log.d("achaa",""+animator);
                animator.start();
               // animator1.start();
                handler1.postDelayed(this,3500);

            }
        };
        handler1.post(run1);
        run = new Runnable(){
            @Override
            public void run() {
                Animator animator = ObjectAnimator.ofFloat(mRedBall2, View.X,width,-mRedBall2.getWidth()).setDuration(5000);
                Animator animator1 = ObjectAnimator.ofFloat(mRedBall2, View.Y,0 ,height).setDuration(5000);
                Log.d("acha1",""+mRedBall2);
                Log.d("achaa1",""+animator);
                animator.start();
                animator1.start();
                handler.postDelayed(this,5100);

            }
        };
        handler.post(run);
        run = new Runnable(){
            @Override
            public void run() {
                //Animator animator = ObjectAnimator.ofFloat(mRedBall3, View.X,width,-mRedBall2.getWidth()).setDuration(5000);
                Animator animator1 = ObjectAnimator.ofFloat(mRedBall3, View.Y,height ,-mRedBall3.getWidth()).setDuration(2500);
                Log.d("acha1",""+mRedBall2);
               // Log.d("achaa1",""+animator);
              //  animator.start();
                animator1.start();
                handler.postDelayed(this,4100);

            }
        };
        handler.post(run);

        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TapIntent=new Intent(TapOnMovingActivity.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });


    }
}
