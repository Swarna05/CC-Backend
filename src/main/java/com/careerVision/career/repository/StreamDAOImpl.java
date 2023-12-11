package com.careerVision.career.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.careerVision.career.model.Stream;


@Repository
public class StreamDAOImpl
{
	 @Autowired
	 private NamedParameterJdbcTemplate jdbcTemplate;
	

	public List<Stream> getStream()
	{
		String query =  "Select * from tbl_stream";
	    SqlParameterSource parameterSource = new MapSqlParameterSource();
	    try 
	    {
	        return jdbcTemplate.query(query, parameterSource, new RowMapper<Stream>() {
	        	@Override
	            public Stream mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Stream stream = new Stream();
	            	stream.setStream(rs.getString("stream"));
	            	stream.setStream_id(rs.getInt("stream_id"));
	            	
	            	return stream;
	            }
	        });
	    } 
	    catch (EmptyResultDataAccessException e) 
	    {
	        return null;
	    }
	}
}