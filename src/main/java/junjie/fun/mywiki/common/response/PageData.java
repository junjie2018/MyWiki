package junjie.fun.mywiki.common.response;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PageData<T> {
    /**
     * 当前页数
     */
    private Long current;

    /**
     * 每页记录数
     */
    private Long size;

    /**
     * 记录总数
     */
    private Long total;

    /**
     * 分页总数
     */
    private Long pages;

    /**
     * 记录
     */
    private List<T> records = Collections.emptyList();
}
