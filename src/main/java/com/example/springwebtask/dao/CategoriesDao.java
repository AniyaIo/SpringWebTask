package com.example.springwebtask.dao;

import com.example.springwebtask.record.CategoriesRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
public class CategoriesDao implements ICategoriesDao {
    //DB接続用コンポーネントの宣言
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate_super; //

    @Override
    public List<CategoriesRecord> findAll() {
        return jdbcTemplate.query("SELECT * FROM categories ORDER BY id ASC;",
                new DataClassRowMapper<>(CategoriesRecord.class));
    }

    @Override
    public int insert(CategoriesRecord data){
        var param = new MapSqlParameterSource();
        param.addValue("name", data.name());
        param.addValue("created_at", new Timestamp(System.currentTimeMillis()));
        param.addValue("updated_at", new Timestamp(System.currentTimeMillis()));

        return jdbcTemplate.update(
                "INSERT " +
                    "INTO categories (name,created_at,updated_at) " +
                    "VALUES(" +
                    ":name, " +
                    ":created_at, " +
                    ":updated_at);"
                , param);
    }

    @Override
    public int update(CategoriesRecord data){
        var param = new MapSqlParameterSource();
        param.addValue("name", data.name());
        param.addValue("updated_at", new Timestamp(System.currentTimeMillis()));

        return jdbcTemplate.update(
                "UPDATE categories" +
                    " SET " +
                    "name=:name, " +
                    "updated_at=:updated_at " +
                    "WHERE id=:id;",
                param);
    }

    @Override
    public int delete(int id){
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbcTemplate.update("DELETE FROM categories WHERE id=:id;", param);
    }
}
