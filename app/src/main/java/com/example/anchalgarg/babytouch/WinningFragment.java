package com.example.anchalgarg.babytouch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.anchalgarg.babytouch.Main2Activity;
import com.example.anchalgarg.babytouch.MainActivity;
import com.example.anchalgarg.babytouch.R;


public class WinningFragment extends Fragment {

    MainActivity p;
    Main2Activity p2;
    Button mPlayAgain;
    RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_winning_message, container, false);
//        mPlayAgain=(Button)view.findViewById(R.id.playAgain);
//        mPlayAgain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //p.play();
//                p.onCreate(null);
//            }
//        });
        /*
        relativeLayout= (RelativeLayout) view.findViewById(R.id.lay);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                p.removerules();
                return true;
            }
        });
        */
        return view;
    }

}
