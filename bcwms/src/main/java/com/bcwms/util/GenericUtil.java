package com.bcwms.util;

import com.keepjoy.core.exception.KeepJoyServiceException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenericUtil {

    /** 对象类型转换
     * @param obj  源对象
     * @param cla  目标类对象
     * @param <T>  泛型
     * @return     目标对象
     * @throws Exception
     */
    public static  <T> T convertClass(Object obj,Class<T> cla) {
        Map<String,Object> maps = new HashMap<String,Object>();
        T  dataBean = null;
        try {
            if(null==obj) {
                return null;
            }
            Class<?> cls = obj.getClass();
            //得到类对象的实例对象
            dataBean = cla.newInstance();
            //得到申明的属性
            Field[] fields = cls.getDeclaredFields();

            List<Field> fieldList = new ArrayList<>() ;
            //递归父类的DECLAREDFIELDS
            Class tempClass = cla;
            while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
                fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
                tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
            }




            //通过get方法拿到源对象的属性值
            for(Field field : fields){
                //去掉public static final 的字段(这些标识无法set&get)
                if (field.getModifiers() == 26||field.getModifiers() == 25) continue;
                String fieldName = field.getName();
                String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                Method methodGet = cls.getDeclaredMethod(strGet);
                if(methodGet == null) throw new KeepJoyServiceException("from-obj-属性-"+fieldName+"没有get方法！");
                Object object = methodGet.invoke(obj);
                maps.put(fieldName,object==null?"":object);
            }
            //通过set方法给目标对象赋值
            for(Field field:fieldList){
                if (field.getModifiers() == 26) continue;
                field.setAccessible(true);
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                Object fieldValue = maps.get(fieldName)==null?null:maps.get(fieldName);
                if(fieldValue!=null){
                    try {
                        if(String.class.equals(fieldType)){
                            field.set(dataBean, fieldValue);
                        }else if(byte.class.equals(fieldType)){
                            field.setByte(dataBean, Byte.parseByte(fieldValue.toString()));

                        }else if(Byte.class.equals(fieldType)){
                            field.set(dataBean, Byte.valueOf(fieldValue.toString()));

                        }else if(boolean.class.equals(fieldType)){
                            field.setBoolean(dataBean, Boolean.parseBoolean(fieldValue.toString()));

                        }else if(Boolean.class.equals(fieldType)){
                            field.set(dataBean, Boolean.valueOf(fieldValue.toString()));

                        }else if(short.class.equals(fieldType)){
                            field.setShort(dataBean, Short.parseShort(fieldValue.toString()));

                        }else if(Short.class.equals(fieldType)){
                            field.set(dataBean, Short.valueOf(fieldValue.toString()));

                        }else if(int.class.equals(fieldType)){
                            field.setInt(dataBean, Integer.parseInt(fieldValue.toString()));

                        }else if(Integer.class.equals(fieldType)){
                            field.set(dataBean, Integer.valueOf(fieldValue.toString()));

                        }else if(long.class.equals(fieldType)){
                            field.setLong(dataBean, Long.parseLong(fieldValue.toString()));

                        }else if(Long.class.equals(fieldType)){
                            field.set(dataBean, Long.valueOf(fieldValue.toString()));

                        }else if(float.class.equals(fieldType)){
                            field.setFloat(dataBean, Float.parseFloat(fieldValue.toString()));

                        }else if(Float.class.equals(fieldType)){
                            field.set(dataBean, Float.valueOf(fieldValue.toString()));

                        }else if(double.class.equals(fieldType)){
                            field.setDouble(dataBean, Double.parseDouble(fieldValue.toString()));

                        }else if(Double.class.equals(fieldType)){
                            field.set(dataBean, Double.valueOf(fieldValue.toString()));

                        }else if(Date.class.equals(fieldType)){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            field.set(dataBean, sdf.parse(fieldValue.toString()));
                        }else{
                            field.set(dataBean, fieldValue);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBean;
    }


}
