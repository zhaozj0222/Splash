package com.example.splash;
import android.app.Activity;  
import android.content.Context;  
import android.content.Intent;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.graphics.Canvas;  
import android.graphics.Paint;  
import android.os.AsyncTask;
import android.os.Bundle;  
import android.os.Handler;  
import android.view.View;  
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

public class SplashActivity extends Activity {
	 
    private static final int FAILURE = 0; // 失败
    private static final int SUCCESS = 1; // 成功
    private static final int OFFLINE = 2; // 如果支持离线阅读，进入离线模式
 
    private TextView mVersionNameText;
    private static final int SHOW_TIME_MIN = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
 
        mVersionNameText = (TextView) findViewById(R.id.version_name);
        mVersionNameText.setText("v1.0");
 
       
        new AsyncTask<Void, Void, Integer>() {
 
            @Override
            protected Integer doInBackground(Void... params) {
                
                int result;
                long startTime = System.currentTimeMillis();
                result = loadingCache();
                long loadingTime = System.currentTimeMillis() - startTime;
                if (loadingTime < SHOW_TIME_MIN) {
                    try {
                        Thread.sleep(SHOW_TIME_MIN - loadingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return result;
    
            }
 
            @Override
            protected void onPostExecute(Integer result) {
            	// ... ...
                Intent intent = new Intent();
                intent.setClassName(SplashActivity.this, getString(R.string.splash_out_activity));
                startActivity(intent);
                finish();
                //两个参数分别表示进入的动画,退出的动画
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            };
        }.execute(new Void[]{});
    }
 
    private int loadingCache() {
       // //if (BaseApplication.mNetWorkState == NetworkUtils.NETWORN_NONE) {
       //     return OFFLINE;
        //}
        
        return SUCCESS;
    }
 
 
}