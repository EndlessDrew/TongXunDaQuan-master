package feicui.projectone.activity;



import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import feicui.projectone.R;
import feicui.projectone.adapter.PhoneTypeAdapter;
import feicui.projectone.entity.PhoneTypeEntity;
import feicui.projectone.sqlite.MyOpenHelper;
import feicui.projectone.sqlite.TypeEntry;

/**
 * @author Drew
 * 通讯录主页大全
 */
public class MainActivity extends BaseActivity {
    //表示MainActivity 电话类型的ListView
    ListView lv_phone_type;
    //声明电话类型的适配器
    PhoneTypeAdapter  adapter;
    //定义布尔类型的值,用来判断双击退出
    boolean is_exit=false;
    //第一次点击退出的时间戳
    long  l_firstClickTime;
    //第二次点击的时间戳
    long l_secondClickTime;
    //loading界面
    LinearLayout ll_loading;
    //电话类型适配器
   PhoneTypeAdapter ptAdapter;
    //SQLite帮助类
   MyOpenHelper mHlper;
    private static final int CALL_PHONE =0 ;//拨打电话



    @Override
    protected int setContent() {
        //加载主页面布局
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mHlper=new MyOpenHelper(this);
        lv_phone_type=(ListView)findViewById(R.id.lv_phone_type);
        ll_loading=(LinearLayout)findViewById(R.id.ll_loading);
        //启动初始化异步任务
        new InitTask().execute();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //双击退出
        doubleClickExit(keyCode, event);
        return true;
    }


    /**
     * 初始化电话类型的列表
     * 简单的说就是忘ListView 添加本地数据库中的电话类型
     */
    private void initList(){
        //获取可读的database对象，通过打开固定的路径的方式
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(TypeEntry.DATABASE_PATH +"/phone.db",null);
        //查询数据库，返回一个游标
        Cursor cursor = db.query(TypeEntry.TABLE_NAME, //表名
                new String[]{TypeEntry.COLUMNS_NAME_TYPE, TypeEntry.COLUMNS_NAME_SUBTABLE}, //COLUMN NAME
                null, //WHERE
                null, //Where args
                null, //GROUP BY
                null, //HAVING
                null //ORDER BY
        );
        //将游标往下移动到第一行
        cursor.moveToFirst();
        //准备好实体数据组
        final ArrayList<PhoneTypeEntity>entities=new ArrayList<PhoneTypeEntity>();
        do{
            //将游标中的数据遍历出来
            String typeName=cursor.getString(cursor.getColumnIndexOrThrow(TypeEntry.COLUMNS_NAME_TYPE));
            String subTable=cursor.getString(cursor.getColumnIndexOrThrow(TypeEntry.COLUMNS_NAME_SUBTABLE));
            //装载进实体类中
            PhoneTypeEntity entity=new PhoneTypeEntity();
            entity.setPhonetypename(typeName);
            entity.setSubTable(subTable);
            entities.add(entity);
        }while(cursor.moveToNext());
        ptAdapter =new PhoneTypeAdapter(this,entities);
        //给列表设置点击监听
        lv_phone_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //用来启动新界面的intent
                Intent intent;
                //判断从数据库中获取到的TypeName是什么字符
                switch (entities.get(position).getSubTable()) {

                    case TypeEntry.SUB_CATERING://订餐电话
                        //将子表的表名放入intent中传到下个Activity
                        intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                        intent.putExtra(TypeEntry.COLUMNS_NAME_SUBTABLE, TypeEntry.SUB_CATERING);
                        intent.putExtra("key","订餐电话");
                        intent.putExtra("Typename","Catering");
                        startActivity(intent);
                        break;
                    case TypeEntry.SUB_PUBLIC_SERVICE://公共服务
                        intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                        intent.putExtra(TypeEntry.COLUMNS_NAME_SUBTABLE, TypeEntry.SUB_PUBLIC_SERVICE);
                        intent.putExtra("key","公共服务");
                        intent.putExtra("Typename","PublicService");
                        startActivity(intent);
                        break;

                    case TypeEntry.SUB_EXPRESSAGE://快递服务
                        intent = new Intent(MainActivity.this, PhoneNumberActivity.class);
                        intent.putExtra(TypeEntry.COLUMNS_NAME_SUBTABLE, TypeEntry.SUB_EXPRESSAGE);
                        intent.putExtra("key","快递服务");
                        intent.putExtra("Typename","Expressage");
                        startActivity(intent);
                        break;

                }
            }
        });


    }

    /**
     * 双击退出函数
     */
    private  void doubleClickExit(int keyCode, KeyEvent event){
        //当用户第一次点击按钮时
        if(keyCode==KeyEvent.KEYCODE_BACK && is_exit==false){
            //设置第一次点击后的布尔值，改为true
            is_exit=true;
            l_firstClickTime=System.currentTimeMillis();
            //显示再次点击退出函数
            Toast.makeText(this,"再次点击退出",Toast.LENGTH_SHORT).show();
        }else if(keyCode==KeyEvent.KEYCODE_BACK && is_exit==true ){
            l_secondClickTime=System.currentTimeMillis();
            //时间差小于2000ms
            if(l_secondClickTime-l_firstClickTime<2000){
                finish();//结束程序
            }else{
                is_exit=false;//超过2000秒 重置第一次点击前的布尔值
                doubleClickExit(keyCode,event);//时间超过后 点击重新调用方法
            }
        }


    }


    /**
     * 从raw文件中导入随着APK一起发布的数据库
     * 简单的说其实是在导入本地的数据库
     */

    private void improtDatabase(){
        try{
            //创建数据的目录，若数据库不存则创建单层目录
            File  dirfile=new File(TypeEntry.DATABASE_PATH);
            if(!dirfile.exists()){
                dirfile.mkdir();
            }
            //创建将要被导入的数据库File对象
            File file=new File(TypeEntry.DATABASE_PATH,"phone.db");
            //判断文件是否存在，如果不存在，则创建该文件夹，存在的话就直接返回
            if(!file.exists()){
                file.createNewFile();
            }else {
                return;
            }
            //获得自带数据库的输入流
            InputStream ip=getResources().openRawResource(R.raw.phone);
            //创建将被导入的数据库输入流
            FileOutputStream fop=new FileOutputStream(file);
            //创建缓冲区
            byte[]buffer=new byte[1024];
            //将数据读入缓冲区

            while(ip.read(buffer)!=-1){
                //将缓冲区的数据写入输出流中
                fop.write(buffer);
                //重置缓冲，如果不刷新缓冲区 最后一次读取数据是会出现错误信息添加
                buffer=new byte[1024];
            }
            //关闭流
            fop.flush();
            ip.close();
            fop.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //异步初始化操作任务类
    class InitTask extends AsyncTask<Void, Void, Void>{
        //任务启动后在异步线程中执行的代码，不可以操作UI
        @Override
        protected Void doInBackground(Void... params) {
            //转移数据库文件
            improtDatabase();
            //装载ListView
            initList();
            return null;
        }
        //任务启动之前执行的代码，可以操作UI
        protected  void  onPreExecute(){
            //让loading界面显示
            ll_loading.setVisibility(View.VISIBLE);
        }
        //任务完成后可以执行的代码，可以操作UI
        protected  void onPostExecute(Void aVoid){
            //隐藏loading界面
            ll_loading.setVisibility(View.GONE);
            //给列表设置适配器
            lv_phone_type.setAdapter(ptAdapter);
        }
    }



    public void requestPermission(){
        //检查是否拥有打电话的权限
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    CALL_PHONE
            );
        }
    }


    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CALL_PHONE:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //申请第一个权限成功
                }else{
                    //失败后的操作
                    finish();
                }
                break;

        }
    }

}
