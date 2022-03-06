package com.example.atomikos.service.primary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.atomikos.service.primary.vo.Users;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */

@Repository("usersMapper")
public interface UsersMapper extends BaseMapper<Users> {

}
