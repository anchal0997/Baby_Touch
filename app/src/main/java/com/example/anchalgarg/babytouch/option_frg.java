package com.example.anchalgarg.babytouch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class option_frg extends android.support.v4.app.DialogFragment {


    Button mSubmit;
    TextView mResponse;
    Button mYes;

    Button mNo;
    RadioButton mEAsy,mDiff;
    TextView mRes;
    public static Boolean x=true;
    Main2Activity ma;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.optionlayout, container, false);
        mSubmit=(Button)rootView.findViewById(R.id.submit);
        mEAsy=(RadioButton)rootView.findViewById(R.id.easy);
        mDiff=(RadioButton)rootView.findViewById(R.id.difficult);
        mResponse=(TextView)rootView.findViewById(R.id.textView5);
        mYes=(Button) rootView.findViewById(R.id.yes);
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        mNo=(Button)rootView.findViewById(R.id.no);
        mRes=(TextView)rootView.findViewById(R.id.textView7);
        mRes.setVisibility(View.INVISIBLE);
        mResponse.setVisibility(View.INVISIBLE);
        MainActivity.x1=true;
        mEAsy.setChecked(true);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubmit.setVisibility(View.INVISIBLE);
                mResponse.setVisibility(View.VISIBLE);
            }
        });
        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRes.setVisibility(View.VISIBLE);
                mRes.setText("Yayaya");


            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(checkedId==mEAsy.getId()){
                    //ma.x=true;
                    MainActivity.x1=true;
                }
                else if(checkedId==mDiff.getId())
                {
                    Log.d("hell","yeah");
                 //   ma.x=false;
                    MainActivity.x1=false;
                }
            }
        });
        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRes.setText("OOPS");
                mRes.setVisibility(View.VISIBLE);

            }
        });

        return rootView;
    }


}