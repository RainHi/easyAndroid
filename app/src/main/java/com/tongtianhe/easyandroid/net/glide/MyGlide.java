package com.tongtianhe.easyandroid.net.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.tongtianhe.easyandroid.R;

/**
 * Created by free on 16/7/14.
 */
public class MyGlide {

    private Context mContext;
    private String imageUrl;
    private ImageView targetImageView;
    private int loadingImageRes=-1;

    public MyGlide(Context context) {
        mContext = context;
    }

    public static MyGlide newIntance(Context context){
        return new MyGlide(context);
    }

    public static MyGlide newDefIntance(Context context){
        MyGlide rt=new MyGlide(context);
        rt.loadingImageRes= R.drawable.icon_loading;
        return rt;
    }


    public MyGlide load(String imageUrl){
        this.imageUrl=imageUrl;
        return this;
    }

    public void into(ImageView targetImageView){
        this.targetImageView=targetImageView;
        DrawableTypeRequest request=Glide.with(mContext).load(imageUrl);
        if(loadingImageRes>0){
            request.placeholder(loadingImageRes);
        }
        request.into(this.targetImageView);
    }
}
