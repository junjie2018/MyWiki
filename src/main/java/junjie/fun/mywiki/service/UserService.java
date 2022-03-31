package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import junjie.fun.mywiki.entity.User;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageUserCondition;
import junjie.fun.mywiki.request.user.LoginRequest;
import junjie.fun.mywiki.request.user.ChangePasswordRequest;
import junjie.fun.mywiki.request.user_admin.CreateUserRequest;
import junjie.fun.mywiki.request.user_admin.UpdateUserRequest;
import junjie.fun.mywiki.response.data.LoginData;
import junjie.fun.mywiki.response.data.UserData;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    LoginData login(LoginRequest request);

    Long createUser(CreateUserRequest request);

    void updateUser(UpdateUserRequest request);

    void changePassword(ChangePasswordRequest request);

    Page<UserData> pageUser(PageRequest<PageUserCondition> request);
}
