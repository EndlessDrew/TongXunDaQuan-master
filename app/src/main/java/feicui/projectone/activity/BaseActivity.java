package feicui.projectone.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activity的基类
 *一些基本你的载入方法
 * Created by z on 2016/9/1.
 */
public  abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //初始化Activity
        setContentView(setContent());
        //加载布局
        initView();
        //加载监听事件
        setListener();
    }

    /**
     * 设置当前页的布局文件
     * @return
     */
    protected  abstract  int setContent();
    /**
     * 加载控件
     */
    protected  abstract  void  initView();

    /**
     * 设置监听
     */
    protected  abstract  void setListener();


}