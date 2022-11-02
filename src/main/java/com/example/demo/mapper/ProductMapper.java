package com.example.demo.mapper;

import com.example.demo.model.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getAllProducts();

}
