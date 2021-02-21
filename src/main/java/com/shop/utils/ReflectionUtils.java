package com.shop.utils;

import com.shop.content.anotation.Column;
import com.shop.content.anotation.Entity;
import com.shop.content.anotation.Id;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

public class ReflectionUtils {
    public static String getColumnName(Class<?> clazz, String fieldName){
        try {
            return clazz.getDeclaredField(fieldName).getAnnotation(Column.class).value();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String primaryName(Class<?> clazz, String fieldName){
        try {
            return clazz.getDeclaredField(fieldName).getAnnotation(Id.class).value();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean autoIncrement(Class<?> clazz, String fieldName){
        try {
            return clazz.getDeclaredField(fieldName).getAnnotation(Id.class).autoIncrement();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getClassName(Class<?> clazz){
        return clazz.getAnnotation(Entity.class).value();
    }

    public static Object methodGet(Object object, String fieldName){
        StringBuilder methodName = new StringBuilder("get")
                .append(fieldName.substring(0,1).toUpperCase() + fieldName.substring(1));
        try {
            Method method = object.getClass().getDeclaredMethod(methodName.toString());
            return method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T convertToEntity(ResultSet rs, Class<T> clazz){
        try {
            T t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {

                try {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Id.class)){
                        field.set(t, rs.getObject(primaryName(clazz, field.getName())));
                    }
                    else if (field.getType().equals(LocalDate.class)){
                        String date = rs.getString(getColumnName(clazz, field.getName()));
                        field.set(t, TimeUtil.convertToLocalDate(date));
                    }
                    else {
                        field.set(t, rs.getObject(getColumnName(clazz, field.getName())));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void copy(Object resource, T target) throws IllegalAccessException {
        Field[] resourceFields = resource.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();
        for (int i = 0; i<resourceFields.length; i++){
            Field resourceField = resourceFields[i];
            for (int j=0;j<targetFields.length;j++){
                Field targetField = targetFields[j];
                if (methodGet(resource, resourceField.getName()) != null){
                    if (resourceField.getType().equals(targetField.getType())){
                        if (resourceField.getName().equals(targetField.getName())){
                            targetField.setAccessible(true);
                            targetField.set(target, methodGet(resource, resourceField.getName()));
                        }
                    }
                }
            }
        }
    }

    public static <T> T mapToModel(Class<T> tClass, HttpServletRequest request){
        T t = null;
        try {
            t = tClass.newInstance();
            BeanUtils.populate(t, request.getParameterMap());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
}
