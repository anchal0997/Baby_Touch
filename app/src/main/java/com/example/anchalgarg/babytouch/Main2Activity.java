package com.example.anchalgarg.babytouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Main2Activity extends AppCompatActivity {
    GridView gdView;
    String []categories={"Tap","Tap on moving objects","Tap and hold","Slide","Drag and drop","Pinch and zoom","Rotate"};
    int []images={R.drawable.tap,R.drawable.taponmoving,R.drawable.tapandhold,R.drawable.slide,R.drawable.draganddrop,R.drawable.pinchandzoom,R.drawable.rotate};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gdView=(GridView)findViewById(R.id.gridView);
        CustomAdapter adapter=new CustomAdapter(this,categories,images);

        gdView.setAdapter(adapter);
        gdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position)
                {
                    case 0:
                        Intent TapIntent=new Intent(Main2Activity.this,TapActivity.class);
                        TapIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent);
                        break;

                    case 1:
                        Intent TapIntent1=new Intent(Main2Activity.this,TapOnMovingActivity.class);
                        TapIntent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent1);
                        break;
                    case 2:
                        Intent TapIntent2=new Intent(Main2Activity.this,TapAndHoldActivity.class);
                        TapIntent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent2);
                        break;
                    case 3:
                        Intent TapIntent3=new Intent(Main2Activity.this,SlideActivity.class);
                        TapIntent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent3);
                        break;
                    case 4:
                        Intent TapIntent4=new Intent(Main2Activity.this,DragAndDropActivity.class);
                        TapIntent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent4);
                        break;
                    case 5:
                        Intent TapIntent5=new Intent(Main2Activity.this,PinchAndZoomActivity.class);
                        TapIntent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent5);
                        break;
                    case 6:
                        Intent TapIntent6=new Intent(Main2Activity.this,pratice.class);
                        TapIntent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(TapIntent6);
                        break;

                }

            }
        });
    }
}
