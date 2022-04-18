package junjie.fun.mywiki.request;

import lombok.Data;


@Data
public class PageRequest<T> {

    private Integer current = 0;

    private Integer size = 10;

    T condition;
}
