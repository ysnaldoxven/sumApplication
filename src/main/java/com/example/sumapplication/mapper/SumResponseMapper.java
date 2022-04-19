package com.example.sumapplication.mapper;

import com.example.sumapplication.model.SumResponseBody;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SumResponseMapper implements RowMapper<SumResponseBody> {

    @Override
    public SumResponseBody mapRow(ResultSet rs, int rowNum) throws SQLException {
        SumResponseBody sumResponseBody = new SumResponseBody();

        sumResponseBody.setId_response(rs.getInt("id_response"));
        sumResponseBody.setIdRequestFk(rs.getInt("id_request_fk"));
        sumResponseBody.setEndpoint(rs.getString("endpoint"));
        sumResponseBody.setResultSum(rs.getInt("result_sum"));

        return sumResponseBody;
    }
}