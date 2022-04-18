package junjie.fun.mywiki.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.Data;


@Data
public abstract class Page2 {
    /**
     * 当前页数
     */
    private Long current = 0L;

    /**
     * 每页记录数
     */
    private Long size = 10L;

    public <T> Page<T> getPage(Class<T> entityClass) {
        //noinspection unchecked
        return CopyUtils.copy(this, Page.class);
    }

    public static <T> Page<T> getOnePage(Class<T> entityClass) {
        return new Page<>(1, 1);
    }

    public static <T> Page<T> getNoLimitPage(Class<T> entityClass) {
        return new Page<>(1, -1);
    }
}
