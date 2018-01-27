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


public class PinchAndZoomActivity extends AppCompatActivity implements View.OnTouchListener {

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
        setContentView(R.layout.activity_pinch_and_zoom);

        mTimeUp=MediaPlayer.create(PinchAndZoomActivity.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);
        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);

        mtada=MediaPlayer.create(PinchAndZoomActivity.this,R.raw.tada);
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

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
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

                                        x = event.getRawX();
                                        y = event.getRawY();

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
                                                if (scale > 0.6) {
                                                    scalediff = scale;
                                                    Log.d("bhavya",scale+"");
                                                   if(scale>=2.5&&scale<=3&&x1!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x1=true;
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
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
                                                    if(scale>=2.5&&scale<=3&&x2!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x2=true;
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
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
                                                    if(scale>=0.2&&scale<=0.5&&x4!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x4=true;
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
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
                                                    if(scale>=0.2&&scale<=0.5&&x3!=true)
                                                    {
                                                        Log.d("555","855");
                                                        x3=true;
                                                        view.setScaleX(scale);
                                                        view.setScaleY(scale);
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
                Intent TapIntent=new Intent(PinchAndZoomActivity.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });
        mTimeUp= MediaPlayer.create(PinchAndZoomActivity.this,R.raw.timeup);
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

//    private float rotation(MotionEvent event) {
//        double delta_x = (event.getX(0) - event.getX(1));
//        double delta_y = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(delta_y, delta_x);
//        return (float) Math.toDegrees(radians);
//    }



}

