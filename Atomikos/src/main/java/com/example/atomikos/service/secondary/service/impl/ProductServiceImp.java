package com.example.atomikos.service.secondary.service.impl;

import com.example.atomikos.service.secondary.vo.Product;
import com.example.atomikos.service.secondary.mapper.ProductMapper;
import com.example.atomikos.service.secondary.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */
@Service
public class ProductServiceImp extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
