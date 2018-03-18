package com.example.anchalgarg.babytouch;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class RotateEasyActivity extends AppCompatActivity {
    ImageButton mDrag1;
    ImageButton mDrop1;
    ImageButton mDrag2;
    ImageButton mDrop2;
    ImageButton mDrag3;
    ImageButton mDrop3;
    ImageButton mDrag4;
    ImageButton mDrop4;
    WinningFragment frag = new WinningFragment();
    FrameLayout fr ;
    FragmentManager mananger;
    FragmentTransaction trans;

    Button mMenu;
    int[] m = {0};

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_easy);

        mDrag1=(ImageButton)findViewById(R.id.image1);
        mDrop1=(ImageButton)findViewById(R.id.target1);
        mDrag2=(ImageButton)findViewById(R.id.image2);
        mDrop2=(ImageButton)findViewById(R.id.target2);
        mDrag3=(ImageButton)findViewById(R.id.image3);
        mDrop3=(ImageButton)findViewById(R.id.target3);
        mDrag4=(ImageButton)findViewById(R.id.image4);
        mDrop4=(ImageButton)findViewById(R.id.target4);

        mMenu=(Button)findViewById(R.id.menuBtn);
        mTimeUp=MediaPlayer.create(RotateEasyActivity.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);
        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);



        mtada=MediaPlayer.create(RotateEasyActivity.this,R.raw.tada);
        mStopWatch=(TextView)findViewById(R.id.stopWatch);
        final int[] x = {0};

        mDrag1.setOnLongClickListener(longClickListener);
        mDrag2.setOnLongClickListener(longClickListener);
        mDrag3.setOnLongClickListener(longClickListener);
        mDrag4.setOnLongClickListener(longClickListener);

        mDrop1.setOnDragListener(dragListener);
        mDrop2.setOnDragListener(dragListener);
        mDrop3.setOnDragListener(dragListener);
        mDrop4.setOnDragListener(dragListener);

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    mDrag1.setVisibility(View.VISIBLE);
                    mDrag2.setVisibility(View.VISIBLE);
                    mDrag3.setVisibility(View.VISIBLE);
                    mDrag4.setVisibility(View.VISIBLE);
                    mDrop1.setImageResource(R.drawable.white_ball);
                    mDrop2.setImageResource(R.drawable.white_star);
                    mDrop3.setImageResource(R.drawable.tri_white);
                    mDrop4.setImageResource(R.drawable.white_tria);

                    mDrag1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag1.setRotation(mDrag1.getRotation() -50);

                        }
                    });
                    mDrag2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag2.setRotation(mDrag2.getRotation() +20);

                        }
                    });
                    mDrag3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag3.setRotation(mDrag3.getRotation() -40);

                        }
                    });
                    mDrag4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag4.setRotation(mDrag4.getRotation() +80);

                        }
                    });
                    Log.d("aagya","-____--__");



                    if(m[0] ==4)
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

                    mStartStop.setText("START");
                    t=0;
                    mMenu.setVisibility(View.VISIBLE);
                    time=0;
                    timeSwap+=time;
                    customHandler.removeCallbacks(updateTimerThred);
                }
            }
        });
        Log.d("hello",""+m[0]);
        if(m[0] ==4)
        {

            Log.d("uuu","-----------");
            trans = mananger.beginTransaction();
            trans.add(R.id.fragment_container, frag);
            trans.commit();


        }

        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TapIntent=new Intent(RotateEasyActivity.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });
    }
    View.OnLongClickListener longClickListener=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data=ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder=new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);
            return true;
        }
    };

    View.OnDragListener dragListener=new View.OnDragListener()
    {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent= event.getAction();
            switch (dragEvent)
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final  View view=(View) event.getLocalState();
                    if(view.getId()==R.id.image1&&v.getId()==R.id.target1&&t==1)
                    {
                        Log.d("draged","___---");
                        mDrag1.setVisibility(View.INVISIBLE);
                        m[0]++;
                        mDrop1.setImageResource(R.drawable.red__ball);
                    }
                    else if(view.getId()==R.id.image2&&v.getId()==R.id.target2&&t==1)
                    {
                        Log.d("draged","___---");
                        mDrag2.setVisibility(View.INVISIBLE);
                        m[0]++;
                        mDrop2.setImageResource(R.drawable.star);
                    }
                    else if(view.getId()==R.id.image3&&v.getId()==R.id.target3&&t==1)
                    {
                        Log.d("draged","___---");
                        mDrag3.setVisibility(View.INVISIBLE);
                        m[0]++;
                        mDrop3.setImageResource(R.drawable.rect_red);
                    }
                    else if(view.getId()==R.id.image4&&v.getId()==R.id.target4&&t==1)
                    {
                        Log.d("draged","___---");
                        mDrag4.setVisibility(View.INVISIBLE);
                        m[0]++;
                        mDrop4.setImageResource(R.drawable.tri_red);
                    }
                    Log.d("hello",""+m[0]);
                    if(m[0] ==4)
                    {

                        Log.d("uuu","-----------");
                        trans = mananger.beginTransaction();
                        trans.add(R.id.fragment_container, frag);
                        trans.commit();
                        mStartStop.setVisibility(View.INVISIBLE);
                        mMenu.setVisibility(View.INVISIBLE);
                        mtada.start();

                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;



            }
            return true;
        }
    };
}
