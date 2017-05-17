/**
 * 通用工具类
 *
 * @时间：2016-7-6
 */
package com.cqu.stuexpress.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.io.Serializable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CommonTools {

    public static final String PACKAGE_NAME = "com.cqu.stuexpress";

    public static final String USER_FILE_PATH = Environment.getExternalStorageDirectory() + "/" + PACKAGE_NAME; //用户文件
    private static final String KEY_FIRSTIN = "KEY_FIRSTIN";

    private Context mContext;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public CommonTools(Context context) {
        this.mContext = context;

        sp = mContext.getSharedPreferences("userinfo", mContext.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * Toast消息
     *
     * @param msg
     * @param duration
     */
    public void showInfo(String msg, int duration) {
        Toast.makeText(mContext, msg, duration).show();
    }

    public void showInfo(String msg) {
        showInfo(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 设置第一次登陆
     *
     * @param firstIn
     * @return
     */
    public CommonTools setFirstIn(boolean firstIn) {
        editor.putBoolean(KEY_FIRSTIN, firstIn);
        editor.commit();
        return this;
    }

    public boolean isFirstIn() {
        return sp.getBoolean(KEY_FIRSTIN, true);
    }


    /**
     * 启动activity
     *
     * @param activity
     */
    public void startActivity(Class<?> activity) {
        try {
            mContext.startActivity(new Intent(mContext, activity));
        } catch (Exception e) {
            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("异常！")
                    .setContentText("启动Activity失败！[e:" + e.toString() + "]")
                    .setConfirmText("确  定!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }
    }

    /**
     * 带参数的启动
     *
     * @param o        传递参数
     * @param activity 要启动的avtivity
     * @return 当前实例
     */
    public CommonTools startActivity(Serializable o, Class<?> activity) {
        try {
            //跳转到详细信息界面
            Bundle bundle = new Bundle();
            bundle.putSerializable(o.getClass().getName(), o);
            Intent intent = new Intent(mContext, activity);
            intent.putExtras(bundle);

            mContext.startActivity(intent);
        } catch (Exception e) {
            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("异常！")
                    .setContentText("启动Activity失败！[e:" + e.toString() + "]")
                    .setConfirmText("确  定!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }
        return this;
    }

    /**
     * 得到上一页面传递过来的参数
     *
     * @param o 已经实例化的类型传递参数
     * @return 上一页面传递过来的参数
     */
    public Serializable getParam(@NonNull Serializable o) {
        Bundle bundle = ((Activity) mContext).getIntent().getExtras();
        if (bundle == null)
            return "";
        return (Serializable) bundle.get(o.getClass().getName());
    }

    /**
     * 得到上一页面传递过来的参数
     *
     * @param c 要接收的类型
     * @return 上一页面传递过来的参数
     */
    public Serializable getParam(@NonNull Class c) {
        Bundle bundle = ((Activity) mContext).getIntent().getExtras();
        if (bundle == null)
            return "";
        return (Serializable) bundle.get(c.getName());
    }

    /**
     * 启动activity,得到返回结果
     *
     * @param activity 要启动的activity
     */
    public void startActivityForResult(Class<?> activity, int requestCode) {
        try {
            ((Activity) mContext).startActivityForResult(new Intent(mContext, activity), requestCode);
        } catch (Exception e) {
            startException(e);
        }
    }

    public CommonTools startActivityForResult(Serializable o, Class<?> activity, int requestCode) {
        try {
            //跳转到详细信息界面
            Bundle bundle = new Bundle();
            bundle.putSerializable(o.getClass().getName(), o);
            Intent intent = new Intent(mContext, activity);
            intent.putExtras(bundle);

            ((Activity) mContext).startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            startException(e);
        }
        return this;
    }

    private void startException(Exception e) {
        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("异常！")
                .setContentText("启动Activity失败！[e:" + e.toString() + "]")
                .setConfirmText("确  定!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}
