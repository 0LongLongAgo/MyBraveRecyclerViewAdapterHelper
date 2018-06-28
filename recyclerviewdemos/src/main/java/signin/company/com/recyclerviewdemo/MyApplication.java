/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2015, Allen, china, shanghai
**                          All Rights Reserved
**
**                          
**                         
**-----------------------------------版本信息------------------------------------
** 版    本: V0.1
**
**------------------------------------------------------------------------------
********************************End of Head************************************\
*/
package signin.company.com.recyclerviewdemo;

import android.app.Application;

import signin.company.com.recyclerviewdemo.statusview.LoadingAndRetryManager;
import signin.company.com.recyclerviewdemo.util.Utils;


/**
 * 文 件 名: MyApplication
 * 创 建 人: Allen
 * 创建日期: 16/12/24 15:33
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends Application {
    private static MyApplication appContext;

    public static MyApplication getInstance() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext =this;
        Utils.init(this);
        initStatusLayout();
//        if (BuildConfig.DEBUG) {
//            Logger
//                    .init("BaseRecyclerViewAdapter")                 // default PRETTYLOGGER or use just init()
//                    .methodCount(3)                 // default 2
//                    .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//                    .methodOffset(2)                // default 0
//            ; //default AndroidLogAdapter
//
//
//        }
    }

    private void initStatusLayout() {
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;
    }
}
