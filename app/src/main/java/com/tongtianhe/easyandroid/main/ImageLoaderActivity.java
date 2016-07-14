package com.tongtianhe.easyandroid.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.tongtianhe.easyandroid.R;
import com.tongtianhe.easyandroid.net.glide.MyGlide;
import com.tongtianhe.easyandroid.ui.base.adapter.CommonAdapter;
import com.tongtianhe.easyandroid.ui.base.adapter.ViewHolder;

import java.util.Arrays;

public class ImageLoaderActivity extends Activity {

    private ListView lviImages;

    private String[] images={
            "http://img5.imgtn.bdimg.com/it/u=1565071041,3313324638&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2107667889,3481088378&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3523972263,2350290783&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1801392296,676686563&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=505673594,2307652189&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=600724660,3787556632&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=589637177,885354154&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3158591551,3668412960&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=434966813,3575719167&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2452976618,2972453164&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=901515976,2422393355&fm=11&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3136922931,3152472455&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2348174296,1990284015&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2733501264,1113772155&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=499794475,3411774728&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3239570765,1873346292&fm=11&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2170208070,802455717&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=3819374188,3453305713&fm=11&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1117960194,146915286&fm=11&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1742496816,3576815532&fm=11&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3567446317,2036987129&fm=11&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2216675870,3880321361&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3692763151,756159530&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2636871349,3850390072&fm=11&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2079755011,3850571953&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2651613010,3690535511&fm=11&gp=0.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        lviImages = (ListView) findViewById(R.id.lvi_images);
        lviImages.setAdapter(new CommonAdapter<String>(this, R.layout.item_image_loader, Arrays.asList(images)) {
            @Override
            public void updateViews(ViewHolder holder, String itemData) {
                ImageView img=holder.getView(R.id.imgvi_loader);
                MyGlide.newDefIntance(ImageLoaderActivity.this).load(itemData).into(img);
            }
        });

    }
}
