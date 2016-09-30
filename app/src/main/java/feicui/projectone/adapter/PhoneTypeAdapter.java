package feicui.projectone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import feicui.projectone.R;
import feicui.projectone.entity.PhoneTypeEntity;

/**
 * Created by z on 2016/9/1.
 */
public class PhoneTypeAdapter extends BaseAdapter {
    ArrayList<PhoneTypeEntity>mdata;
    Context mcontext;

    public PhoneTypeAdapter(Context context,ArrayList<PhoneTypeEntity>data){
        mcontext=context;
        mdata=data;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //声明ViewHolder
        ViewHolder holder;
        if(convertView==null){
            //获取布局加载器
            LayoutInflater inflater=LayoutInflater.from(mcontext);
            //使用布局加载器加载item布局
            convertView=inflater.inflate(R.layout.item_phonetype,null);

            holder=new ViewHolder();
            holder.tv_item_phonetype=(TextView)convertView.findViewById(R.id.tv_item_phonetype);
            holder.iv_arrow_right=(ImageView)convertView.findViewById(R.id.iv_arrow_right);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.tv_item_phonetype.setText(mdata.get(position).getPhonetypename());
        return convertView;
    }

    public class ViewHolder{
        public TextView tv_item_phonetype;//电话类型
        public ImageView iv_arrow_right;//右箭头
    }
}
