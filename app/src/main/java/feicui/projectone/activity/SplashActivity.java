package feicui.projectone.activity;


import android.content.Intent;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import feicui.projectone.R;

/**
 * @author Drew
 * 开屏动画页面
 */
public class SplashActivity extends BaseActivity {
    //开屏页图片
    ImageView iv_splash;
    //透明度动画
    AlphaAnimation alphaAnim;

    @Override
    protected int setContent() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        iv_splash=(ImageView)findViewById(R.id.iv_splash);

    }

    @Override
    protected void setListener() {

    }

    /**
     *
     */
    /**
     * @description 加载动画
     */
    private void initAnimation() {
        //淡入动画
        alphaAnim = new AlphaAnimation(0.0f, 1.0f);
        //设置动画时间
        alphaAnim.setDuration(3000);
        //设置动画播放后的监听
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转到主业
                Log.i("aaa","1111");
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                //关闭开屏页
                Log.i("aaa","222");
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

       //开启动画
        iv_splash.startAnimation(alphaAnim);

   }

    /**
     * 当前页是否获得焦点 true表示已经获得
     * false表示还没有获得
     *
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            initAnimation();
        }
    }


}
