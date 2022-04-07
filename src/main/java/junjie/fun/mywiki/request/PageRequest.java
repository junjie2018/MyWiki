package junjie.fun.mywiki.request;

import lombok.Data;

@Data
public class PageRequest<T> {
    private Integer current = 0;

    private Integer pageSize = 10;

    T condition;
}
