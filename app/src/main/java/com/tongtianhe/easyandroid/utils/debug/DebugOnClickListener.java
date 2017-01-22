package com.tongtianhe.easyandroid.utils.debug;

import android.util.Log;
import android.view.View;

import com.tongtianhe.easyandroid.R;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/1/21.
 */
public class DebugOnClickListener implements View.OnClickListener, Runnable{

    private View.OnClickListener originListener;

    private static final ExecutorService executor= Executors.newSingleThreadExecutor();
    private View clickView;

    public DebugOnClickListener(View.OnClickListener originListener) {
        this.originListener = originListener;
    }

    @Override
    public void onClick(View v) {
        clickView=v;
        executor.execute(this);
        if (originListener != null) {
            originListener.onClick(v);
        }
    }

    private void logId(View v){
        try {
            if (v != null) {
                for (Field fieldId : R.id.class.getFields()) {
                    if (fieldId.getType() == int.class && v.getId() == fieldId.getInt(R.id.class)) {
                        Log.e(DebugUtil.DEBUG_TAG, v.getClass().getSimpleName() + "'id:  R.id." + fieldId.getName());
                        return;
                    }
                }
                Log.e(DebugUtil.DEBUG_TAG, v.getClass().getSimpleName() + "'id:  null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        logId(clickView);
    }
}
