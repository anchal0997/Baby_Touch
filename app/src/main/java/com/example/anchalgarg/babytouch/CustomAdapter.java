package com.example.anchalgarg.babytouch;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by anchalgarg on 23/05/17.
 */

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    String []categories;
    int[] images;

    public CustomAdapter(Context mContext,String []categories,int[] images)
    {
        this.mContext=mContext;
        this.categories=categories;
        this.images=images;

    }


    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int position) {
        return categories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.menu_cells,null);

        }
        TextView catText= (TextView) convertView.findViewById(R.id.category);
        GifImageView mBtn=(GifImageView) convertView.findViewById(R.id.MenuBtn);
        catText.setText(categories[position]);
        mBtn.setImageResource(images[position]);

        return convertView;
    }
}
