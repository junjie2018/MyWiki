package junjie.fun.mywiki.request.ebook;

import junjie.fun.mywiki.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageEBookRequest extends PageRequest {

    /**
     * 分页条件
     */
    private Condition condition = new Condition();

    @Data
    public static class Condition {
        /**
         * 主键
         */
        private List<String> ids;

        /**
         * EBook名称
         */
        private String name;

        /**
         * 未知
         */
        private Long category2Id;
    }
}
