package com.tongtianhe.easyandroid.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tongtianhe.easyandroid.R;
import com.tongtianhe.easyandroid.ui.base.adapter.CommonAdapter;
import com.tongtianhe.easyandroid.ui.base.adapter.ViewHolder;

import java.util.Arrays;

/**
 * Created by free on 16/7/14.
 */
public class MainActivity extends Activity{

    private String[] items={"ui框架", "图片加载"};
    private ListView lviMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lviMain = (ListView) findViewById(R.id.lvi_main);
        lviMain.setAdapter(new CommonAdapter<String>(this, R.layout.item_main, Arrays.asList(items)) {
            @Override
            public void updateViews(ViewHolder holder, String itemData) {
                holder.setText_TextView(R.id.txt_main_item, itemData);
            }
        });
        setListeners();
    }

    private void setListeners() {
        lviMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, UiContainerActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(MainActivity.this, ImageLoaderActivity.class));
                        break;
                }
            }
        });
    }


}
