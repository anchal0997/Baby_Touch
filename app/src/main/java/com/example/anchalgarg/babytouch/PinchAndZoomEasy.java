package com.example.anchalgarg.babytouch;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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

public class PinchAndZoomEasy extends AppCompatActivity {

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
    Boolean inScale=false;

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
        setContentView(R.layout.activity_pinch_and_zoom_easy);

        mDrag1=(ImageButton)findViewById(R.id.image1);
        mDrop1=(ImageButton)findViewById(R.id.target1);
        mDrag2=(ImageButton)findViewById(R.id.image2);
        mDrop2=(ImageButton)findViewById(R.id.target2);
        mDrag3=(ImageButton)findViewById(R.id.image3);
        mDrop3=(ImageButton)findViewById(R.id.target3);
        mDrag4=(ImageButton)findViewById(R.id.image4);
        mDrop4=(ImageButton)findViewById(R.id.target4);

        mMenu=(Button)findViewById(R.id.menuBtn);
        mTimeUp=MediaPlayer.create(PinchAndZoomEasy.this,R.raw.timeup);
        mStartStop=(Button)findViewById(R.id.strtStop);
        mananger=getSupportFragmentManager();
        fr = (FrameLayout) findViewById(R.id.fragment_container);



        mtada=MediaPlayer.create(PinchAndZoomEasy.this,R.raw.tada);
        mStopWatch=(TextView)findViewById(R.id.stopWatch);
        final int[] x = {0};



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

                    mDrag1.setOnTouchListener(new MyScaleGestures(mDrag1.getContext()));

                    mDrag1.setOnLongClickListener(longClickListener);
                    mDrag2.setOnLongClickListener(longClickListener);
                    mDrag3.setOnLongClickListener(longClickListener);
                    mDrag4.setOnLongClickListener(longClickListener);

                    mDrop1.setOnDragListener(dragListener);
                    mDrop2.setOnDragListener(dragListener);
                    mDrop3.setOnDragListener(dragListener);
                    mDrop4.setOnDragListener(dragListener);

                    Log.d("aagya","-____--__");
                    /*mDrag1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag1.setScaleX(2);
                            mDrag1.setScaleY(2);
                        }
                    });*/
                    mDrag2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag2.setScaleX(2);
                            mDrag2.setScaleY(2);
                        }
                    });
                    mDrag3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag3.setScaleX((float) 0.6);
                            mDrag3.setScaleY((float) 0.6);
                        }
                    });
                    mDrag4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDrag4.setScaleX((float) 0.5);
                            mDrag4.setScaleY((float) 0.5);
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

                    mStartStop.setText("START");
                    t=0;
                    mMenu.setVisibility(View.VISIBLE);
                    time=0;
                    timeSwap+=time;
                    customHandler.removeCallbacks(updateTimerThred);
                    mDrag1.setOnLongClickListener(null);
                    mDrag2.setOnLongClickListener(null);
                    mDrag3.setOnLongClickListener(null);
                    mDrag4.setOnLongClickListener(null);

                    mDrop1.setOnDragListener(null);
                    mDrop2.setOnDragListener(null);
                    mDrop3.setOnDragListener(null);
                    mDrop4.setOnDragListener(null);
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

                Intent TapIntent=new Intent(PinchAndZoomEasy.this,Main2Activity.class);
                TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(TapIntent);

            }
        });


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

    public class MyScaleGestures implements View.OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {
        private View view;
        private ScaleGestureDetector gestureScale;
        private float scaleFactor = 1;

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
        public void onScaleEnd(ScaleGestureDetector detector) { inScale = false;
            view.setScaleX(2);
            view.setScaleY(2);
        }
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

