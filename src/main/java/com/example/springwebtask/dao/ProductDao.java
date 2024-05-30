package com.example.springwebtask.dao;

import com.example.springwebtask.record.ProductsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProductDao implements IProductDao {
    //DB接続用コンポーネントの宣言
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ProductsRecord> findAll() {
        return jdbcTemplate.query("SELECT * FROM products ORDER BY id",
                new DataClassRowMapper<>(ProductsRecord.class));
    }

    @Override
    public ProductsRecord findById(int searchId){
        var param = new MapSqlParameterSource();
        param.addValue("id", searchId);
        var list = jdbcTemplate.query("SELECT * FROM products WHERE id = :id", param, new DataClassRowMapper<>(ProductsRecord.class));
        return list.isEmpty() ? null : list.get(0);

    }

    @Override
    public int insert(ProductsRecord data){
        var param = new MapSqlParameterSource();
        param.addValue("product_id", data.productId());
        param.addValue("category_id", data.categoryId());
        param.addValue("name", data.name());
        param.addValue("price", data.price());
        param.addValue("created_at", new Timestamp(System.currentTimeMillis()));
        param.addValue("updated_at", new Timestamp(System.currentTimeMillis()));

        return jdbcTemplate.update(
                "INSERT " +
                    "INTO products (product_id, category_id, name, price,created_at,updated_at) " +
                    "VALUES(" +
                    ":product_id, " +
                    ":category_id, " +
                    ":name, " +
                    ":price, " +
                    ":created_at, " +
                    ":updated_at)"
                , param);
    }

    @Override
    public int update(ProductsRecord data){
        var param = new MapSqlParameterSource();
        param.addValue("id", data.id());
        param.addValue("product_id", data.productId());
        param.addValue("category_id", data.categoryId());
        param.addValue("name", data.name());
        param.addValue("price", data.price());
        param.addValue("updated_at", new Timestamp(System.currentTimeMillis()));

        return jdbcTemplate.update(
                "UPDATE products" +
                    " SET " +
                    "product_id=:product_id, " +
                    "category_id=:category_id," +
                    "name=:name, " +
                    "price=:price, " +
                    "updated_at=:updated_at " +
                    "WHERE id=:id",
                param);
    }

    @Override
    public int delete(int id){
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbcTemplate.update("DELETE FROM products WHERE id=:id", param);
    }
}
