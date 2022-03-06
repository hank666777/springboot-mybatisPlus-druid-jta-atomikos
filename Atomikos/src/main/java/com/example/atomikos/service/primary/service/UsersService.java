package com.example.atomikos.service.primary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.atomikos.service.primary.vo.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */
public interface UsersService extends IService<Users> {

    int insertTwo(Users users);
}
