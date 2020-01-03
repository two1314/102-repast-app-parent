package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ProductMapper extends Mapper<Product> {

    Product listAll(Long productId);

import tk.mybatis.mapper.common.Mapper;

public interface ProductMapper extends Mapper<Product> {
}