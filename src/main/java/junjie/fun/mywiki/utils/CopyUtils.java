package junjie.fun.mywiki.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CopyUtils {
    /**
     * 单体复制
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    public static <T, R> Page<R> copyPage(Page<T> entityPage, Class<R> clazz) {
        return copyPage(entityPage, clazz, null);
    }

    public static <T, R> Page<R> copyPage(Page<T> entityPage, Class<R> clazz,
                                          BiConsumer<T, R> disposer) {

        List<R> pageRecords = new ArrayList<>();

        for (T record : entityPage.getRecords()) {
            R pateRecordItem = copy(record, clazz);

            if (disposer != null) {
                disposer.accept(record, pateRecordItem);
            }

            pageRecords.add(pateRecordItem);
        }

        Page<R> result = new Page<>();

        BeanUtils.copyProperties(entityPage, result);
        result.setRecords(pageRecords);

        return result;
    }

    public static <T, R> List<R> copyList(List<T> entityList, Class<R> clazz) {
        return copyList(entityList, clazz, null);
    }

    public static <T, R> List<R> copyList(List<T> entityList, Class<R> clazz,
                                          BiConsumer<T, R> disposer) {
        List<R> responseDataList = new ArrayList<>();

        for (T entityItem : entityList) {
            R responseData = copy(entityItem, clazz);

            if (disposer != null) {
                disposer.accept(entityItem, responseData);
            }

            responseDataList.add(responseData);
        }

        return responseDataList;
    }

}
