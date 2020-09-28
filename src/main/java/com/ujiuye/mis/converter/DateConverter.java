package com.ujiuye.mis.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiLe
 * @create 2020-08-28 0:39
 */
public class DateConverter  implements Converter<String,Date> {
    @Override
    public Date convert(String s) {
        try {
            return  new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
