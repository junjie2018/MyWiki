package junjie.fun.mywiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import junjie.fun.mywiki.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
