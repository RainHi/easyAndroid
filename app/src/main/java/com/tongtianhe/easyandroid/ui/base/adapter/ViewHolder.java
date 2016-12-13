package com.tongtianhe.easyandroid.ui.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by free on 16/7/6.
 */
public class ViewHolder {

    private View convertView;
    private SparseArray<View> views=new SparseArray<View>();

    public ViewHolder(Context context, int layoutId, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(layoutId, parent, false);
    }


    public View getConvertView(){
        return convertView;
    }


    public <T extends View> T getView(int viewId){
        View v =views.get(viewId);
        if(v==null){
            v=convertView.findViewById(viewId);
            views.put(viewId, v);
        }
        return (T)v;
    }

    public void setText_TextView(int viewId, String text){
        TextView textView=getView(viewId);
        textView.setText(text);
    }



}
