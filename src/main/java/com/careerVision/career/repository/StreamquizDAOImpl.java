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

import com.careerVision.career.model.QuizStream;

@Repository
public class StreamquizDAOImpl
{
	 @Autowired
	 private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<QuizStream> getQuizStream(int id) {
		String query = "Select * from tbl_quiz_stream where stream = :id";
	    SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
	    try 
	    {
	        return jdbcTemplate.query(query, parameterSource, new RowMapper<QuizStream>() {
	            @Override
	            public QuizStream mapRow(ResultSet rs, int rowNum) throws SQLException
	            {
	            	QuizStream quizStream=new QuizStream();
	            	quizStream.setStream(rs.getInt("stream"));
	            	quizStream.setQuestion(rs.getString("question"));
	            	quizStream.setOption1(rs.getString("option_1"));
	            	quizStream.setOption2(rs.getString("option_2"));
	            	quizStream.setOption3(rs.getString("option_3"));
	            	quizStream.setOption4(rs.getString("option_4"));
	            	
	            	return quizStream;
	            }
	        });
	    } 
	    catch (EmptyResultDataAccessException e) 
	    {
	        return null;
	    }
	}
}