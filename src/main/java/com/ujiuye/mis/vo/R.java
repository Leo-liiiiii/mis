package com.ujiuye.mis.vo;


import lombok.Data;


import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private Integer code;
    private Boolean status;
    private String msg;
    private Map<String,Object> data = new HashMap<>();

    public static R ok(){
        return new R(){{
            setCode(20000);
            setStatus(true);
            setMsg("成功");
        }};
    }

    public static R error(){
        return new R(){{
            setCode(30000);
            setStatus(false);
            setMsg("操作失败");
        }};
    }

    public R message(String message){
        this.msg = message;
        return this;
    }

    public R data(Map map){
        this.data = map;
        return this;
    }

    public R data(String key , Object value){
        this.data.put(key,value);
        return this;
    }

}
