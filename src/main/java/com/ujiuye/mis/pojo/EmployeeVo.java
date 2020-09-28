package com.ujiuye.mis.pojo;

/**
 * @author LiLe
 * @create 2020-07-02 14:11
 */
//查询扩展类
public class EmployeeVo extends  Employee {
//搜索的三个条件
    private  Integer keyindex;
    private  String keyCode;
    private  Integer num;

    @Override
    public String toString() {
        return "CustomerVo{" +
                "keyindex=" + keyindex +
                ", keyCode='" + keyCode + '\'' +
                ", num=" + num +
                '}';
    }

    public Integer getKeyindex() {
        return keyindex;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public Integer getNum() {
        return num;
    }

    public void setKeyindex(Integer keyindex) {
        this.keyindex = keyindex;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
