package junjie.fun.mywiki.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import junjie.fun.mywiki.constant.code.BusinessCode;
import junjie.fun.mywiki.context.UserContext;
import junjie.fun.mywiki.entity.User;
import junjie.fun.mywiki.exception.BusinessException;
import junjie.fun.mywiki.mapper.UserMapper;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageUserCondition;
import junjie.fun.mywiki.request.user_admin.CreateOrUpdateUserRequest;
import junjie.fun.mywiki.request.user.LoginRequest;
import junjie.fun.mywiki.request.user.ResetPasswordRequest;
import junjie.fun.mywiki.response.data.LoginData;
import junjie.fun.mywiki.response.data.UserData;
import junjie.fun.mywiki.service.UserService;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static junjie.fun.mywiki.constant.code.BusinessCode.LOGIN_NAME_EXIST;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 用户登录
     */
    public LoginData login(LoginRequest request) {

        User userInDB = getUserByLoginName(request.getLoginName());

        if (ObjectUtils.isEmpty(userInDB)) {
            log.info("用户不存在：{}", JSON.toJSONString(request));
            throw new BusinessException(BusinessCode.LOGIN_NAME_NOT_EXIST);
        }

        if (!StringUtils.equals(userInDB.getPassword(), request.getPassword())) {
            log.info("用户密码错误：{}", JSON.toJSONString(request));
            throw new BusinessException(BusinessCode.PASSWORD_WRONG);
        }

        return CopyUtils.copy(userInDB, LoginData.class);
    }

    /**
     * 创建或更新用户
     */
    public Long createOrUpdateUser(CreateOrUpdateUserRequest request) {
        // 更新操作
        if (ObjectUtils.isNotEmpty(request.getId())) {
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                    .eq(User::getId, request.getId())
                    .set(User::getName, request.getName())
                    .set(User::getPassword, request.getPassword())
                    .set(User::getLoginName, request.getLoginName());

            baseMapper.update(null, updateWrapper);

            return request.getId();
        }
        // 创建操作
        else {
            User userInDB = getUserByLoginName(request.getLoginName());

            if (ObjectUtils.isNotEmpty(userInDB)) {
                throw new BusinessException(LOGIN_NAME_EXIST);
            }

            User userInsert = CopyUtils.copy(request, User.class);

            baseMapper.insert(userInsert);

            return userInsert.getId();
        }
    }

    /**
     * 修改密码
     */
    public void changePassword(ResetPasswordRequest request) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, UserContext.getUserId())
                .set(User::getPassword, request.getPassword());

        baseMapper.update(null, updateWrapper);
    }

    /**
     * 分页查询用户
     */
    public Page<UserData> pageUser(PageRequest<PageUserCondition> request) {

        PageUserCondition condition = request.getCondition() == null ?
                new PageUserCondition() :
                request.getCondition();

        Page<User> pageEntity = new Page<>(request.getPageNo(), request.getPageSize());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(StringUtils.isNotEmpty(condition.getLoginName()), User::getLoginName, condition.getLoginName());

        baseMapper.selectPage(pageEntity, queryWrapper);

        return CopyUtils.copyPage(pageEntity, UserData.class);
    }

    private User getUserByLoginName(String loginName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getLoginName, loginName);

        return baseMapper.selectOne(queryWrapper);
    }
}
