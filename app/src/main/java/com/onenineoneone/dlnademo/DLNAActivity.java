package com.onenineoneone.dlnademo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hpplay.bean.CastDeviceInfo;
import com.hpplay.callback.CastDeviceServiceCallback;
import com.hpplay.link.HpplayLinkControl;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/12.
 */

public class DLNAActivity extends AppCompatActivity {
    String url = "http://gcqq450f71eywn6bv7u.exp.bcevod.com/mda-hbqagik5sfq1jsai/mda-hbqagik5sfq1jsai.mp4";
    private HpplayLinkControl hpplayLinkControl;
    private Context mContext;
    private final String appKey = "77c523477046ac4d59ef7abe6c5d2932";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlna);
        mContext = this;
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hpplayLinkControl.showHpplayWindow((Activity) mContext,null);
            }
        });
        initDLNA();
    }

    /**
     * 初始化DLNA
     */
    private void initDLNA() {
        获取单例:
        hpplayLinkControl = HpplayLinkControl.getInstance();
        获取支持投屏码输入的单例:
//        HpplayLinkControl hpplayLinkControl = HpplayLinkControl.getInstance(ScreenCodeCallBack);
        hpplayLinkControl.initHpplayLink(mContext, appKey);

        hpplayLinkControl.castServiceDiscovery(mContext,
                new CastDeviceServiceCallback() {
                    @Override
                    public void onNoneCastDeviceService() { //没有搜索到设备时回调

                        Toast.makeText(mContext,"没有搜索到设备",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onCastDeviceServiceAvailable(List<CastDeviceInfo> list) {
                                                            //搜索到设备的结果集list
                        Toast.makeText(mContext,"搜索到设备"+list.toString(),Toast.LENGTH_SHORT);

                    }
                });




    }


}
