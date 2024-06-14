package com.example.springwebtask.dao;

import com.example.springwebtask.record.ProductsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDao implements IProductDao {
    //DB接続用コンポーネントの宣言
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate_super; //

    @Override
    public List<ProductsRecord> findAll() {
        return jdbcTemplate.query("SELECT * FROM products ORDER BY id;",
                new DataClassRowMapper<>(ProductsRecord.class));
    }

    @Override
    public ProductsRecord findById(int searchId){
        var param = new MapSqlParameterSource();
        param.addValue("id", searchId);
        var list = jdbcTemplate.query("SELECT * FROM products WHERE id = :id;", param, new DataClassRowMapper<>(ProductsRecord.class));
        return list.isEmpty() ? null : list.get(0);

    }

//    JPAのやり方で行おうとした。JDBC用のページング・ソート方法を調べる
    @Override
    public List<ProductsRecord> findByName(String searchName, String sortRule){
        var param = new MapSqlParameterSource();
        param.addValue("name", searchName);
        String sql="SELECT * FROM products WHERE name LIKE('%'||:name||'%')";
        if(sortRule.isEmpty()){
            return findAll();
        }else{
//            return jdbcTemplate.query(sql+"ORDER BY"+sortRule+";", param, new DataClassRowMapper<>(ProductsRecord.class));
            return jdbcTemplate.query(sql+";", param, new DataClassRowMapper<>(ProductsRecord.class));
        }
    }

    @Override
    public int countRecord(){
        var countRecord = jdbcTemplate_super.queryForObject("SELECT COUNT(id) FROM products;",Integer.class);
        return Objects.requireNonNull(countRecord);
    }

    @Override
    public int insert(ProductsRecord data){
        var param = new MapSqlParameterSource();
        param.addValue("product_id", data.productId());
        param.addValue("category_id", Integer.valueOf(data.categoryId()));
        param.addValue("name", data.name());
        param.addValue("price", Integer.valueOf(data.price()));
        param.addValue("description",data.description());
        param.addValue("created_at", new Timestamp(System.currentTimeMillis()));
        param.addValue("updated_at", new Timestamp(System.currentTimeMillis()));

        return jdbcTemplate.update(
                "INSERT " +
                    "INTO products (product_id, category_id, name, price,description,created_at,updated_at) " +
                    "VALUES(" +
                    ":product_id, " +
                    ":category_id, " +
                    ":name, " +
                    ":price, " +
                    ":description,"+
                    ":created_at, " +
                    ":updated_at);"
                , param);
    }

    @Override
    public int update(ProductsRecord data){
        var param = new MapSqlParameterSource();
        param.addValue("id", data.id());
        param.addValue("product_id", Integer.valueOf(data.productId()));
        param.addValue("category_id", Integer.valueOf(data.categoryId()));
        param.addValue("name", data.name());
        param.addValue("price", Integer.valueOf(data.price()));
        param.addValue("updated_at", new Timestamp(System.currentTimeMillis()));

        return jdbcTemplate.update(
                "UPDATE products" +
                    " SET " +
                    "product_id=:product_id, " +
                    "category_id=:category_id," +
                    "name=:name, " +
                    "price=:price, " +
                    "updated_at=:updated_at " +
                    "WHERE id=:id;",
                param);
    }

    @Override
    public int delete(int id){
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbcTemplate.update("DELETE FROM products WHERE id=:id;", param);
    }
}
