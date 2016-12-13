package com.tongtianhe.easyandroid.ui.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by free on 16/7/6.
 */
public abstract class CommonAdapter<E> extends BaseAdapter {

    private Context mContext;
    private int mLayoutId;
    private List<E> mItems;

    public CommonAdapter(Context context, int layoutId, List<E> items) {
        mContext = context;
        mLayoutId = layoutId;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems==null?0:mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems==null?null:mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder(mContext, mLayoutId, parent);
            holder.getConvertView().setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        updateViews(holder, (E) getItem(position));
        return holder.getConvertView();
    }

    /**
     * 刷新view数据
     * @param holder
     */
    public abstract void updateViews(ViewHolder holder, E itemData);
}
