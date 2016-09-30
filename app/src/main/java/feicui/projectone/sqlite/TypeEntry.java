package feicui.projectone.sqlite;

import android.provider.BaseColumns;

/**
 * 电话类型的SQL契约类
 * 实际上就是存放一些SQL代码的 静态常量
 * Created by z on 2016/9/1.
 */
public class TypeEntry implements BaseColumns {
    //数据库的目录
    public static final String  DATABASE_PATH="/data/data/feicui.projectone/databases";
    //表名 电话类型
    public static final String  TABLE_NAME="PhoneType";
    //列名
    public static final String  COLUMNS_NAME_TYPE="type";//列名  类型
    public static final String  COLUMNS_NAME_SUBTABLE="subtable";


    //创建表格的SQL语句
    public static final String SQL_CREATE_TABLE =
            "create table " + TABLE_NAME + " (" +
                    _ID + " integer primary key," +
                    COLUMNS_NAME_TYPE + " text," +
                    COLUMNS_NAME_SUBTABLE + " text" + ")";
    //删除表格的SQL语句
    public static final String SQL_DELETE_TABLE =
            "drop table if exists " + TABLE_NAME;

    //子表表名

    public static final String SUB_CATERING = "Catering";//订餐电话
    public static final String SUB_PUBLIC_SERVICE = "PublicService";//公共服务
    public static final String SUB_EXPRESSAGE = "Expressage";//快递服务



}
