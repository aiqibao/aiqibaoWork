package com.aiqibao.canal.client.util;
import java.lang.reflect.Field;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 15:46
 * Best wish!
 */
public class FieldUtil {
    public static void setFieldValue(Object object,String fieldName,String value) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        try{
            field = object.getClass().getDeclaredField(fieldName);
        }catch (NoSuchFieldException e){
            field = object.getClass().getSuperclass().getDeclaredField(fieldName);
        }
        field.setAccessible(true);
        Class<?> type = field.getType();
        Object result = StringConvertUtil.convertType(type, value);
        field.set(object,result);
    }
}
