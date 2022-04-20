package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import junjie.fun.mywiki.common.response.PageData;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;
import junjie.fun.mywiki.service.*;
import junjie.fun.mywiki.mapper.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Long createUser(CreateUserRequest request) {
        User eBookInert =
                User.builder()
                        .loginName(request.getLoginName())
                        .name(request.getName())
                        .password(request.getPassword())
                        .build();

        this.baseMapper.insert(eBookInert);

        return eBookInert.getId();
    }

    @Override
    public void deleteUsers(List<Long> userIds) {
        this.baseMapper.deleteBatchIds(userIds);
    }

    @Override
    public Long updateUser(UpdateUserRequest request) {
        LambdaUpdateWrapper<User> updateWrapper =
                new LambdaUpdateWrapper<User>()
                        .eq(User::getId, request.getId())
                        .set(User::getLoginName, request.getLoginName())
                        .set(User::getName, request.getName())
                        .set(User::getPassword, request.getPassword());

        this.baseMapper.update(null, updateWrapper);

        return request.getId();
    }

    @Override
    public PageData<UserData> pageUser(PageUserRequest request) {

        Page<User> pageEntity = request.getPage(User.class);
        PageUserRequest.Condition condition = request.getCondition();

        LambdaQueryWrapper<User> queryWrapper =
                new LambdaQueryWrapper<User>().orderByDesc(User::getCreateTime);

        baseMapper.selectPage(pageEntity, queryWrapper);

        return CopyUtils.copyPageData(pageEntity, UserData.class);
    }

    @Override
    public List<UserData> queryUsers(List<Long> userIds) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().in(User::getId, userIds);

        return CopyUtils.copyList(baseMapper.selectList(queryWrapper), UserData.class);
    }
}
