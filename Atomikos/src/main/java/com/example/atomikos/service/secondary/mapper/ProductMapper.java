package com.example.atomikos.service.secondary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.atomikos.service.secondary.vo.Product;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Hank
 * @since 2022-03-06
 */
@Repository("productMapper")
public interface ProductMapper extends BaseMapper<Product> {

}
