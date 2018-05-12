package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.resource;

/**
 * Created by This PC on 9/23/2017.
 */

public class customlistview extends ArrayAdapter<String>
{

    private String[] services;
    private String[] desc;
    private Integer[] imgid;
    private Activity context;
    public customlistview(Activity context,String[] services,String[] desc ,Integer[] imgid)
    {

        super(context,R.layout.listviewlayout,services);
        this.context=context;
        this.services=services;
        this.desc=desc;
        this.imgid=imgid;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View r=convertView;
        viewholder viewHolder=null;
        if(r==null)
        {
            LayoutInflater layoutinflator=context.getLayoutInflater();
            r=layoutinflator.inflate(R.layout.listviewlayout,null,true);
            viewHolder=new viewholder(r);
            r.setTag(viewHolder);



        }
        else
        {
            viewHolder=(viewholder) r.getTag();
        }
        viewHolder.iv.setImageResource(imgid[position]);
        viewHolder.tv1.setText(services[position]);
        viewHolder.tv2.setText(desc[position]);
        return r;
    }

    class viewholder
    {
        TextView tv1,tv2;
        ImageView iv;
        viewholder(View v)
        {
            tv1=(TextView)v.findViewById(R.id.tvservices);
            tv2=(TextView)v.findViewById(R.id.tvdesc);
            iv=(ImageView)v.findViewById(R.id.imageView);
        }
    }
}
