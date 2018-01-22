package com.example.anchalgarg.babytouch;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements Animation.AnimationListener{

    private Button mPlay;
    private Button mOption;
    private Button mAbout;
    RelativeLayout mRelativeLayout;
    Animation animationzoom;
    ImageButton mredBall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay=(Button)findViewById(R.id.playBtn);
        mOption=(Button)findViewById(R.id.options);
        mAbout=(Button)findViewById(R.id.abtBtn);
        mredBall=(ImageButton) findViewById(R.id.red_ball);
        animationzoom=  AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        mredBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mredBall.startAnimation(animationzoom);
            }
        });


        mRelativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent PlayIntent=new Intent(MainActivity.this,Main2Activity.class);
                PlayIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(PlayIntent);
            }
        });
        mOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final option_frg mOptions=new option_frg();
                final FragmentManager mananger;
                final FragmentTransaction[] trans = new FragmentTransaction[1];
                mananger=getSupportFragmentManager();
                mOptions.show(mananger,"Options");
                mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans[0] = mananger.beginTransaction();
                        trans[0].remove(mOptions);
                        trans[0].commit();
                    }
                });

            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(getBaseContext(),"ANIMATION STOPPED..",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
