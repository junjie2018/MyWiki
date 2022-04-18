package junjie.fun.mywiki.request.ebook;

import junjie.fun.mywiki.request.Page2;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageEBookRequest extends Page2 {

    /**
     * 分页条件
     */
    private Condition condition = new Condition();

    @Data
    public static class Condition {
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
