package com.tongtianhe.easyandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tongtianhe.easyandroid.utils.AppUtils;

/**
 * 检测应用是处于前台还是后台
 */
public class RunningDetectActivity extends Activity{

    private static final String TAG=RunningDetectActivity.class.getSimpleName();
    private boolean isRunOnBack=false;

    private static final String KEY_ACTIVE="active";

    private TextView mTxtForward, mTxtBehind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            isRunOnBack=savedInstanceState.getBoolean(KEY_ACTIVE);
        }
        setContentView(R.layout.activity_running_detect);
        mTxtForward= (TextView) findViewById(R.id.txt_forword);
        mTxtBehind= (TextView) findViewById(R.id.txt_behind);
        mTxtBehind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RunningDetectActivity.this, "behind", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!AppUtils.isAppOnForeground(this)) {
            isRunOnBack = true;
            Toast.makeText(RunningDetectActivity.this, "进入后台", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_ACTIVE, isRunOnBack);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(isRunOnBack){
            isRunOnBack=false;
            Toast.makeText(RunningDetectActivity.this, "回到前台", Toast.LENGTH_SHORT).show();
        }
    }
}
