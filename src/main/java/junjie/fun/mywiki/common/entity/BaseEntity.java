package junjie.fun.mywiki.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    /**
     * 记录主键
     */
    private Long id;

    /**
     * 记录创建者
     */
    private Long creator;

    /**
     * 记录修改者
     */
    private Long modifier;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录修改时间
     */
    private LocalDateTime localDateTime;

    /**
     * 乐观锁版本控制
     */
    private Long version;

    /**
     * 删除状态
     */
    private Long isDelete;

}
