package feicui.projectone.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



import feicui.projectone.R;
import feicui.projectone.sqlite.TypeEntry;

public class AddNumberActivity extends BaseActivity {
    EditText et_name;
    EditText et_number;


    @Override
    protected int setContent() {
        return R.layout.activity_add_number;
    }

    @Override
    protected void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
    }

    @Override
    protected void setListener() {

    }

    /**
     * 点击按钮添加数据
     * @param view
     */
    public  void  commit(View view ){
        Intent intent = getIntent();
       String typeName= intent.getStringExtra("Typename");
        Log.i("aaa",typeName+"aaaa");
        //获取数据
        String name =  et_name.getText().toString().trim();
        String number = et_number.getText().toString().trim();
        //打开数据库
        ContentValues contentValues =new ContentValues();
        contentValues.put("phoneName",name);
        contentValues.put("phoneNumber",number);
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(TypeEntry.DATABASE_PATH + "/phone.db",null);
        db.insert(typeName,null,contentValues);
        Toast.makeText(AddNumberActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
    }

}