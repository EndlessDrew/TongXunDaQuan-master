package feicui.projectone.entity;

/**
 * 电话实体类
 * Created by z on 2016/9/1.
 */
public class PhoneTypeEntity {
    //电话的类型
    private String  phonetypename;
    private String  subTable;

    public String getSubTable() {
        return subTable;
    }

    public void setSubTable(String subTable) {
        this.subTable = subTable;
    }

    public String getPhonetypename() {
        return phonetypename;
    }

    public void setPhonetypename(String phonetypename) {
        this.phonetypename = phonetypename;
    }
}
