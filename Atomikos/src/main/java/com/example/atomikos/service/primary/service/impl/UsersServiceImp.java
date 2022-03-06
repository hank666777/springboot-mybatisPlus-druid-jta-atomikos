package com.example.atomikos.service.primary.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.atomikos.service.primary.mapper.UsersMapper;
import com.example.atomikos.service.primary.service.UsersService;
import com.example.atomikos.service.primary.vo.Users;
import com.example.atomikos.service.secondary.mapper.ProductMapper;
import com.example.atomikos.service.secondary.vo.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */
@Service("usersService")
public class UsersServiceImp extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource(name = "usersMapper")
    private UsersMapper usersMapper;
    @Resource(name = "productMapper")
    private ProductMapper productMapper;


    @Transactional
    public int insertTwo(Users users) {

        usersMapper.insert(users);

        Product product = new Product();
        product.setpId("1");
        product.setpName("test");
        product.setpDesc("testDesc");
        product.setVersion(1);
        product.setStatus("0");
        product.setCreateDate(LocalDate.now());
        product.setUpdateDate(LocalDate.now());

        productMapper.insert(product);
        int i = 1 / 0;
        return 0;
    }
}
