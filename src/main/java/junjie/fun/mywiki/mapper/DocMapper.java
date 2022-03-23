package junjie.fun.mywiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import junjie.fun.mywiki.entity.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DocMapper extends BaseMapper<Doc> {
    /**
     * 自增文档的浏览数
     */
    void increaseViewCount(@Param("id") Long docId);

    /**
     * 自增文档的投票数
     */
    void increaseVoteCount(@Param("id") Long docId);
}
