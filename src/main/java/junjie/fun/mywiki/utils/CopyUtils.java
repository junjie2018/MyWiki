package junjie.fun.mywiki.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.response.PageData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

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

    public static <T, R> PageData<R> copyPageData(Page<T> entityPage, Class<R> clazz) {
        return copyPageData(entityPage, clazz, null);
    }

    public static <T, R> PageData<R> copyPageData(Page<T> entityPage, Class<R> clazz,
                                                  BiConsumer<T, R> disposer) {

        List<R> pageRecords = new ArrayList<>();

        for (T record : entityPage.getRecords()) {
            R pateRecordItem = copy(record, clazz);

            if (disposer != null) {
                disposer.accept(record, pateRecordItem);
            }

            pageRecords.add(pateRecordItem);
        }

        PageData<R> result = new PageData<>();

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

    /**
     * 用于将一个列表转换成森林，要求列表中每一项需要记录自己的parentId（item无记录子节点的字段）
     *
     * @param inputs        输入的列表
     * @param isRoot        判断item是否为根item（即无parentId）
     * @param transfer      因为原item可能无字段记录自己子节点信息，故需要转换成其他的实体
     * @param getPrimaryId  获取item的主键
     * @param getParentId   获取item的parentId
     * @param getChildNodes 获取存储子节点的方法
     * @param <T>           item的类型
     * @param <R>           转换后的实体类型
     * @return 返回的森林
     */
    private <T, R> List<R> listToForest(List<T> inputs,
                                        Function<T, Boolean> isRoot,
                                        Function<T, R> transfer,
                                        Function<T, String> getPrimaryId,
                                        Function<T, String> getParentId,
                                        Function<R, List<R>> getChildNodes) {

        if (CollectionUtils.isEmpty(inputs)) {
            return Collections.emptyList();
        }

        Map<String, R> uniqueIdToItem = new HashMap<>();

        for (T input : inputs) {
            uniqueIdToItem.put(getPrimaryId.apply(input), transfer.apply(input));
        }

        List<R> result = new ArrayList<>();

        for (T input : inputs) {

            R item = uniqueIdToItem.get(getPrimaryId.apply(input));

            if (isRoot.apply(input)) {
                result.add(item);
                continue;
            }

            R itemParent = uniqueIdToItem.get(getParentId.apply(input));

            getChildNodes.apply(itemParent).add(item);
        }

        return result;
    }

    /**
     * 用于将一个列表转换成森林，要求列表中每一项需要记录自己的parentId（item包含记录子节点的字段）
     *
     * @param inputs        输入的列表
     * @param isRoot        判断item是否为根item（即无parentId）
     * @param getPrimaryId  获取item的主键
     * @param getParentId   获取item的parentId
     * @param getChildNodes 获取存储子节点的方法
     * @param <T>           item的类型
     * @return 返回的森林
     */
    private <T> List<T> listToForest(List<T> inputs,
                                     Function<T, Boolean> isRoot,
                                     Function<T, String> getPrimaryId,
                                     Function<T, String> getParentId,
                                     Function<T, List<T>> getChildNodes) {

        if (CollectionUtils.isEmpty(inputs)) {
            return Collections.emptyList();
        }

        Map<String, T> uniqueIdToItem = new HashMap<>();

        for (T input : inputs) {
            uniqueIdToItem.put(getPrimaryId.apply(input), input);
        }

        List<T> result = new ArrayList<>();

        for (T input : inputs) {

            T item = uniqueIdToItem.get(getPrimaryId.apply(input));

            if (isRoot.apply(input)) {
                result.add(item);
                continue;
            }

            T itemParent = uniqueIdToItem.get(getParentId.apply(input));

            getChildNodes.apply(itemParent).add(item);
        }

        return result;
    }

}
