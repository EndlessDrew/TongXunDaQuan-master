package feicui.projectone.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import feicui.projectone.R;
import feicui.projectone.sqlite.TypeEntry;

public class UpdateActivity extends BaseActivity {
    EditText et_update_name;
    EditText et_update_number;


    @Override
    protected int setContent() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {
        et_update_name =(EditText)findViewById(R.id.et_update_name);
        et_update_number=(EditText)findViewById(R.id.et_update_number);
        Intent intent =getIntent();
        String name=  intent.getStringExtra("name");
        Log.i("aaa",name);
        String number =intent.getStringExtra("number");
        Log.i("aaa",number);
//        String type = intent.getStringExtra("typeName");
//        Log.i("aaa",type);
        et_update_name.setText(name);
        et_update_number.setText(number);
    }

    @Override
    protected void setListener() {

    }
    public void update(View view){
        String name = et_update_name.getText().toString().trim();
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(TypeEntry.DATABASE_PATH + "/phone.db",null);


    }
}
