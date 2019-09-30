/**
 * Created by bhavyagupta on 20/03/18.
 */
package com.example.anchalgarg.babytouch;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.BoolRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PinchAndZoomEasy extends AppCompatActivity implements View.OnTouchListener {

    ImageButton mred_ball;
    ImageButton mCon;
    Boolean x1=false,x2=false,x3=false,x4=false;

    ImageButton mDrag1;
    ImageButton mDrop1;
    ImageButton mDrag2;
    ImageButton mDrop2;
    ImageButton mDrag3;
    ImageButton mDrop3;
    ImageButton mDrag4;
    ImageButton mDrop4;
    Button mMenu;
    WinningFragment frag = new WinningFragment();
    FrameLayout fr ;
    FragmentManager mananger;
    FragmentTransaction trans;
    Button mStartStop;
    long starttime=0L;
    long time=0L;
    int min,secs;
    long timeSwap=0L;

    MediaPlayer mTimeUp=new MediaPlayer();
    MediaPlayer mtada=new MediaPlayer();
    long updateTime=0L;
    ImageView im_move_zoom_rotate;
    int present=-1;
    Toolbar toolbar;
    float scalediff;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    public int t=0;
    int[] m = {0};

    Boolean[] flags = {false,false,false,false,false,false,false,false};
    TextView mStopWatch;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinch_and_zoom_easy);

        mTimeUp=MediaPlayer.create(PinchAndZoomEasy.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);
        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);

        mtada=MediaPlayer.create(PinchAndZoomEasy.this,R.raw.tada);
        mStopWatch=(TextView)findViewById(R.id.stopWatch);
        final int[] x = {0};


        mDrag1=(ImageButton)findViewById(R.id.image1);
        mDrop1=(ImageButton)findViewById(R.id.target1);
        mDrag2=(ImageButton)findViewById(R.id.image2);
        mDrop2=(ImageButton)findViewById(R.id.target2);
        mDrag3=(ImageButton)findViewById(R.id.image3);
        mDrop3=(ImageButton)findViewById(R.id.target3);
        mDrag4=(ImageButton)findViewById(R.id.image4);
        mDrop4=(ImageButton)findViewById(R.id.target4);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, 250);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 50;
        layoutParams.bottomMargin = -250;
        layoutParams.rightMargin = -250;
        final int[][] positions=new int[4][4];

        positions[0][0]=mDrop1.getLeft();
        positions[0][1]=mDrop1.getTop();
        positions[0][2]=mDrop1.getWidth()+positions[0][0];
        positions[0][3]=mDrop1.getBottom();
        positions[1][0]=mDrop2.getLeft();
        positions[1][1]=mDrop2.getTop();
        positions[1][2]=mDrop2.getRight();
        positions[1][3]=mDrop2.getBottom();
        positions[2][0]=mDrop3.getLeft();
        positions[2][1]=mDrop3.getTop();
        positions[2][2]=mDrop3.getRight();
        positions[2][3]=mDrop3.getBottom();
        positions[3][0]=mDrop4.getLeft();
        positions[3][1]=mDrop4.getTop();
        positions[3][2]=mDrop4.getRight();
        positions[3][3]=mDrop4.getBottom();
        Log.d("YAYA","p"+positions[0][0]+positions[0][1]+"  "+positions[0][2]);

        //int positions[4][2]={mDrop1.getLeft(),mDrop1.getTop()};

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    positions[0][0]=mDrop1.getLeft();
                    positions[0][1]=mDrop1.getTop();
                    positions[0][2]=mDrop1.getWidth()+positions[0][0];
                    positions[0][3]=mDrop1.getHeight()+positions[0][1];
                    positions[1][0]=mDrop2.getLeft();
                    positions[1][1]=mDrop2.getTop();
                    positions[1][2]=mDrop2.getWidth()+positions[1][0];
                    positions[1][3]=mDrop2.getHeight()+positions[1][1];
                    positions[2][0]=mDrop3.getLeft();
                    positions[2][1]=mDrop3.getTop();
                    positions[2][2]=mDrop3.getWidth()+positions[2][0];
                    positions[2][3]=mDrop3.getHeight()+positions[2][1];
                    positions[3][0]=mDrop4.getLeft();
                    positions[3][1]=mDrop4.getTop();
                    positions[3][2]=mDrop4.getWidth()+positions[3][0];
                    positions[3][3]=mDrop4.getHeight()+positions[3][1];
                    Log.d("YAYA-1","p"+positions[0][0]+positions[0][1]+"  "+positions[0][2]);
                    im_move_zoom_rotate=(ImageView)findViewById(R.id.image1);
                    // im_move_zoom_rotate.setLayoutParams(layoutParams);
                    im_move_zoom_rotate.setOnTouchListener(new View.OnTouchListener() {

                        RelativeLayout.LayoutParams parms;
                        int startwidth;
                        int startheight;
                        float dx = 0, dy = 0, x = 0, y = 0;
                        float angle = 0;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            final ImageView view = (ImageView) v;

                            ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:

                                    parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                    startwidth = parms.width;
                                    startheight = parms.height;
                                    dx = event.getRawX() - parms.leftMargin;
                                    dy = event.getRawY() - parms.topMargin;
                                    mode = DRAG;
                                    break;

                                case MotionEvent.ACTION_POINTER_DOWN:
                                    oldDist = spacing(event);
                                    if (oldDist > 10f) {
                                        mode = ZOOM;
                                    }

                                    // d = rotation(event);

                                    break;
                                case MotionEvent.ACTION_UP:

                                    break;

                                case MotionEvent.ACTION_POINTER_UP:
                                    mode = NONE;

                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (mode == DRAG) {
                                        Log.d("checking","still dragging");
                                        x = event.getRawX();
                                        y = event.getRawY();
                                        Log.d("ABAAO",""+x+" "+y);
                                        if(x>=positions[0][0]&&y>=positions[0][1]&&x<=positions[0][2]&&y<=positions[0][3])
                                        {
                                            Log.d("HELLO__","___");
                                            //mDrop1.setImageResource(R.drawable.red__ball);
                                            //mDrop1.setLayoutParams(
                                            //        new RelativeLayout.LayoutParams(mDrag1.getLayoutParams()));
                                            //mDrop1.setVisibility(View.INVISIBLE);
                                            flags[0] = true;
                                            float x = mDrop1.getX();
                                            float y= mDrop1.getY();
                                            check();
                                            m[0]++;
                                        }else{
                                            Log.d("still_checking","working");
                                            flags[0]= false;
                                        }


                                        parms.leftMargin = (int) (x - dx);
                                        parms.topMargin = (int) (y - dy);

                                        parms.rightMargin = 0;
                                        parms.bottomMargin = 0;
                                        parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                        parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                        view.setLayoutParams(parms);

                                        //   final View view1  = im_move_zoom_rotate;
                                        //  final  View view1=(View) event.getLocalState();
//                                        if(v.getId()==R.id.target1&&t==1)
//                                        {
//                                            Log.d("draged","___---");
//                                            mDrag1.setVisibility(View.INVISIBLE);
//                                            m[0]++;
//                                            mDrop1.setImageResource(R.drawable.red__ball);
//                                        }

                                    } else if (mode == ZOOM) {

                                        if (event.getPointerCount() == 2) {

//                                newRot = rotation(event);
//                                float r = newRot - d;
//                                angle = r;

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            float newDist = spacing(event);
                                            if (newDist > 10f) {
                                                float scale = newDist / oldDist * view.getScaleX();
                                                if (scale > 0.6) {
                                                    scalediff = scale;
                                                    if(x1!=true)
                                                    {
                                                        x1=true;
                                                        mDrag1.setScaleX(2);
                                                        mDrag1.setScaleY(2);
                                                        flags[1] = true;
                                                        //check();
                                                    }
                                                    if(x1!=true)
                                                    {
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
                                                    }

                                                }
                                            }

                                            //  view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            parms.leftMargin = (int) ((x - dx) + scalediff);
                                            parms.topMargin = (int) ((y - dy) + scalediff);

                                            parms.rightMargin = 0;
                                            parms.bottomMargin = 0;
                                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                            view.setLayoutParams(parms);


                                        }
                                    }
                                    break;
                            }

                            return true;

                        }
                    });



                    //im_move_zoom_rotate=(ImageView)findViewById(R.id.image1);
                    // im_move_zoom_rotate.setLayoutParams(layoutParams);
                    mDrag2.setOnTouchListener(new View.OnTouchListener() {

                        RelativeLayout.LayoutParams parms;
                        int startwidth;
                        int startheight;
                        float dx = 0, dy = 0, x = 0, y = 0;
                        float angle = 0;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            final ImageView view = (ImageView) v;

                            ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:

                                    parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                    startwidth = parms.width;
                                    startheight = parms.height;
                                    dx = event.getRawX() - parms.leftMargin;
                                    dy = event.getRawY() - parms.topMargin;
                                    mode = DRAG;
                                    break;

                                case MotionEvent.ACTION_POINTER_DOWN:
                                    oldDist = spacing(event);
                                    if (oldDist > 10f) {
                                        mode = ZOOM;
                                    }

//                        d = rotation(event);

                                    break;
                                case MotionEvent.ACTION_UP:

                                    break;

                                case MotionEvent.ACTION_POINTER_UP:
                                    mode = NONE;

                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (mode == DRAG) {

                                        x = event.getRawX();
                                        y = event.getRawY();
                                        if(x>=positions[1][0]&&y>=positions[1][1]&&x<=positions[1][2]&&y<=positions[1][3])
                                        {
                                            Log.d("HELLO__","___");
                                            flags[2] = true;
                                            check();

                                        }else{
                                            flags[2]= false;
                                        }

                                        parms.leftMargin = (int) (x - dx);
                                        parms.topMargin = (int) (y - dy);

                                        parms.rightMargin = 0;
                                        parms.bottomMargin = 0;
                                        parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                        parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                        view.setLayoutParams(parms);

                                    } else if (mode == ZOOM) {

                                        if (event.getPointerCount() == 2) {

//                                newRot = rotation(event);
//                                float r = newRot - d;
//                                angle = r;r

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            float newDist = spacing(event);
                                            if (newDist > 10f) {
                                                float scale = newDist / oldDist * view.getScaleX();
                                                if (scale > 0.6) {
                                                    scalediff = scale;
                                                    if(x2!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x2=true;
                                                        mDrag2.setScaleX(2);
                                                        mDrag2.setScaleY(2);
                                                        flags[3] = true;
                                                        check();
                                                    }
                                                    if(x2!=true)
                                                    {
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
                                                    }

                                                }
                                            }

//                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            parms.leftMargin = (int) ((x - dx) + scalediff);
                                            parms.topMargin = (int) ((y - dy) + scalediff);

                                            parms.rightMargin = 0;
                                            parms.bottomMargin = 0;
                                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                            view.setLayoutParams(parms);


                                        }
                                    }
                                    break;
                            }

                            return true;

                        }
                    });

                    mDrag4.setOnTouchListener(new View.OnTouchListener() {

                        RelativeLayout.LayoutParams parms;
                        int startwidth;
                        int startheight;
                        float dx = 0, dy = 0, x = 0, y = 0;
                        float angle = 0;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            final ImageView view = (ImageView) v;

                            ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:

                                    parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                    startwidth = parms.width;
                                    startheight = parms.height;
                                    dx = event.getRawX() - parms.leftMargin;
                                    dy = event.getRawY() - parms.topMargin;
                                    mode = DRAG;
                                    break;

                                case MotionEvent.ACTION_POINTER_DOWN:
                                    oldDist = spacing(event);
                                    if (oldDist > 10f) {
                                        mode = ZOOM;
                                    }

//                        d = rotation(event);

                                    break;
                                case MotionEvent.ACTION_UP:

                                    break;

                                case MotionEvent.ACTION_POINTER_UP:
                                    mode = NONE;

                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (mode == DRAG) {

                                        x = event.getRawX();
                                        y = event.getRawY();
                                        if(x>=positions[3][0] -10 &&y>=positions[3][1] && x<= 10 + positions[3][2]  &&y<= positions[3][3])
                                        {
                                            Log.d("HELLO__","___");
                                            flags[6] = true;
                                            Log.d("2_inside","inside  " + flags[7]);
                                            check();
                                            m[0]++;
                                        }else{
                                            Log.d("2_outside","outside  " + flags[7]);
                                            flags[6]= false;
                                        }

                                        parms.leftMargin = (int) (x - dx);
                                        parms.topMargin = (int) (y - dy);

                                        parms.rightMargin = 0;
                                        parms.bottomMargin = 0;
                                        parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                        parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                        view.setLayoutParams(parms);

                                    } else if (mode == ZOOM) {

                                        if (event.getPointerCount() == 2) {

//                                newRot = rotation(event);
//                                float r = newRot - d;
//                                angle = r;

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            float newDist = spacing(event);
                                            if (newDist > 10f) {
                                                float scale = newDist / oldDist * view.getScaleX();
                                                if (scale > 0) {
                                                    scalediff = scale;
                                                    if(x4!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x4=true;
                                                        mDrag4.setScaleX((float) 0.5);
                                                        mDrag4.setScaleY((float) 0.5);
                                                        flags[7] = true;
                                                        Log.d("2_scaler","" + flags[6]);
                                                        check();
                                                    }
                                                    if(x4!=true)
                                                    {
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
                                                    }

                                                }
                                            }

//                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                            x = event.getRawX();
                                            y = event.getRawY();


                                            parms.leftMargin = (int) ((x - dx) + scalediff);
                                            parms.topMargin = (int) ((y - dy) + scalediff);

                                            parms.rightMargin = 0;
                                            parms.bottomMargin = 0;
                                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                            view.setLayoutParams(parms);


                                        }
                                    }
                                    break;
                            }

                            return true;

                        }
                    });

                    mDrag3.setOnTouchListener(new View.OnTouchListener() {

                        RelativeLayout.LayoutParams parms;
                        int startwidth;
                        int startheight;
                        float dx = 0, dy = 0, x = 0, y = 0;
                        float angle = 0;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            final ImageView view = (ImageView) v;

                            ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);
                            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                                case MotionEvent.ACTION_DOWN:

                                    parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                                    startwidth = parms.width;
                                    startheight = parms.height;
                                    dx = event.getRawX() - parms.leftMargin;
                                    dy = event.getRawY() - parms.topMargin;
                                    mode = DRAG;
                                    break;

                                case MotionEvent.ACTION_POINTER_DOWN:
                                    oldDist = spacing(event);
                                    if (oldDist > 10f) {
                                        mode = ZOOM;
                                    }

//                        d = rotation(event);

                                    break;
                                case MotionEvent.ACTION_UP:

                                    break;

                                case MotionEvent.ACTION_POINTER_UP:
                                    mode = NONE;

                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (mode == DRAG) {

                                        x = event.getRawX();
                                        y = event.getRawY();
                                        Log.d("tri_pos",""+x+" "+y);
                                        Log.d("tri_1",""+positions[3][0]+" "+positions[3][1]+" "+positions[3][2]+positions[3][3]);
                                        if(x>=positions[2][0] -10 &&y>=positions[2][1]-10 &&x<=positions[2][2] +10 &&y<=10 + positions[2][3])
                                        {
                                            Log.d("HELLO__","___");
                                            flags[4] = true;
                                            Log.d("3_inside","" + flags[4]);
                                            check();
                                            m[0]++;
                                        }else{
                                            Log.d("3_outside","" + flags[4]);
                                            flags[4] = false;
                                        }

                                        parms.leftMargin = (int) (x - dx);
                                        parms.topMargin = (int) (y - dy);

                                        parms.rightMargin = 0;
                                        parms.bottomMargin = 0;
                                        parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                        parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                        view.setLayoutParams(parms);

                                    } else if (mode == ZOOM) {

                                        if (event.getPointerCount() == 2) {

//                                newRot = rotation(event);
//                                float r = newRot - d;
//                                angle = r;

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            float newDist = spacing(event);
                                            if (newDist > 10f) {
                                                float scale = newDist / oldDist * view.getScaleX();
                                                if (scale > 0) {
                                                    scalediff = scale;
                                                    if(x3!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x3=true;
                                                        mDrag3.setScaleX((float) 0.6);
                                                        mDrag3.setScaleY((float) 0.6);
                                                        Log.d("3_scaler", "flags[4]");
                                                        flags[5] = true;
                                                        check();
                                                    }
                                                    if(x3!=true)
                                                    {
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
                                                    }

                                                }
                                            }

                                            //  view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                            x = event.getRawX();
                                            y = event.getRawY();

                                            parms.leftMargin = (int) ((x - dx) + scalediff);
                                            parms.topMargin = (int) ((y - dy) + scalediff);

                                            parms.rightMargin = 0;
                                            parms.bottomMargin = 0;
                                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                            view.setLayoutParams(parms);

                                        }
                                    }
                                    break;
                            }

                            return true;

                        }
                    });
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
                    mDrag1.setOnTouchListener(null);
                    mDrag2.setOnTouchListener(null);
                    mDrag3.setOnTouchListener(null);
                    mDrag4.setOnTouchListener(null);
                    mStartStop.setText("START");

                    t=0;
                    mMenu.setVisibility(View.VISIBLE);
                    time=0;
                    timeSwap+=time;
                    customHandler.removeCallbacks(updateTimerThred);
                }

            }
        });


        mMenu=(Button)findViewById(R.id.menuBtn);
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TapIntent=new Intent(PinchAndZoomEasy.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });
        mTimeUp= MediaPlayer.create(PinchAndZoomEasy.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);

       /* mDrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                present=1;
                v.setOnTouchListener(new MyScaleGestures(getApplicationContext()));
            }
        });
        mDrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setOnTouchListener(new MyScaleGestures(getApplicationContext()));
                present=2;
            }
        });
        mDrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setOnTouchListener(new MyScaleGestures(getApplicationContext()));
                present=3;
            }
        });
        mDrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setOnTouchListener(new MyScaleGestures(getApplicationContext()));
                present=4;
            }
        });*/


    }
    public void saveImage(String path, String imgname, Bitmap image){
        try{
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path+imgname);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedOutputStream stream = new BufferedOutputStream(fos);

            image.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            stream.flush();
            stream.close();
        }
        catch(FileNotFoundException e){

        }
        catch(IOException e){

        }
    }
    int mActivePointerId;
    public boolean onTouch(View v, MotionEvent event) {
        mActivePointerId = event.getPointerId(0);

        // ... Many touch events later...

        // Use the pointer ID to find the index of the active pointer
        // and fetch its position
        int pointerIndex = event.findPointerIndex(mActivePointerId);
        // Get the pointer's current position
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);
        Log.d("ss","ff"+x+" "+y);
        return false;
    }

    /*public class MyScaleGestures implements View.OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {
        private View view;
        private ScaleGestureDetector gestureScale;
        private float scaleFactor = 1;
        Boolean inScale;

        public MyScaleGestures(Context c){ gestureScale = new ScaleGestureDetector(c, this); }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            this.view = view;
            gestureScale.onTouchEvent(event);
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = (scaleFactor < 1 ? 1 : scaleFactor); // prevent our view from becoming too small //
            scaleFactor = ((float)((int)(scaleFactor * 100))) / 100; // Change precision to help with jitter when user just rests their fingers //
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            inScale = true;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) { inScale = false; }


    }*/
    private void init() {

        im_move_zoom_rotate = (ImageView) findViewById(R.id.image1);
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void check(){

        if(flags[0] && flags[1]){

            // myImgView.setImageDrawable(getResources().getDrawable(R.drawable.monkey, getApplicationContext().getTheme()));
            mDrop1.setImageResource(R.drawable.red__ball);
//            mDrag1.setLayoutParams(
//                    new RelativeLayout.LayoutParams(mDrop1.getLayoutParams()));

//            mDrop1.setLayoutParams(
//                    new RelativeLayout.LayoutParams(mDrop1.getLayoutParams()));
            //mDrop1.setX(x);
            //mDrop1.setY(y);
            //       mDrop1.setVisibility(View.INVISIBLE);
            //mDrag1.setVisibility(View.INVISIBLE);
            mDrag1.setOnTouchListener(null);
            mDrag1.setVisibility(View.INVISIBLE);
        }

        if(flags[2] && flags[3]){
            mDrop2.setImageResource(R.drawable.star);
            mDrag2.setOnTouchListener(null);
            mDrag2.setVisibility(View.INVISIBLE);
        }

        if(flags[4] && flags[5]){
            mDrop3.setImageResource(R.drawable.rect_red);
            mDrag3.setOnTouchListener(null);
            mDrag3.setVisibility(View.INVISIBLE);
        }

        if(flags[6] && flags[7]){
            mDrop4.setImageResource(R.drawable.tri_red);
//            mDrop4.setLayoutParams(
//                    new RelativeLayout.LayoutParams(mDrop4.getLayoutParams()));
            Log.d("2_","should work");
            mDrag4.setVisibility(View.INVISIBLE);
            mDrag4.setOnTouchListener(null);
        }
        Boolean flag = true;
        for(int i = 0;i<7;i++)
            if(!flags[i])
                flag = false;

        if(flag){
            trans = mananger.beginTransaction();
            trans.add(R.id.fragment_container, frag);
            trans.commit();
            mStartStop.setVisibility(View.INVISIBLE);
            mMenu.setVisibility(View.INVISIBLE);
        }

    }
//    private float rotation(MotionEvent event) {
//        double delta_x = (event.getX(0) - event.getX(1));
//        double delta_y = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(delta_y, delta_x);
//        return (float) Math.toDegrees(radians);
//    }



}


