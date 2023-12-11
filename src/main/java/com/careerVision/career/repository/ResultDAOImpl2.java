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

import com.careerVision.career.model.QuizSaveAns;
import com.careerVision.career.model.Result2;

@Repository
public class ResultDAOImpl2
{
	 @Autowired
	 private NamedParameterJdbcTemplate jdbcTemplate;
	 
	 public QuizSaveAns  getUserQuizAnswer(String contactNo)
	 {
		String query =  "Select * from tbl_ans where contact_no = :contactNo";
		
	    SqlParameterSource parameterSource = new MapSqlParameterSource("contactNo",contactNo);
	    
	    try 
	    {
	        return jdbcTemplate.queryForObject(query, parameterSource, new RowMapper<QuizSaveAns >() 
	        {
	        	@Override
	            public QuizSaveAns  mapRow(ResultSet rs, int rowNum) throws SQLException 
	        	{
	        		QuizSaveAns  ans = new QuizSaveAns ();
	        		ans.setContactNo(rs.getString("contact_no"));
	            	ans.setHtmlcssOption(rs.getString("htmlcss_option")); 
	            	ans.setJavascriptOption(rs.getString("javascript_option")); 
	            	ans.setSpringbootOption(rs.getString("springboot_option")); 
	            	ans.setMysqlOption(rs.getString("mysql_option")); 

	            	ans.setJavaOption(rs.getString("java_option")); 

	            	
	            	return ans;
	            }
	        });
	    } 
	    catch (EmptyResultDataAccessException e) 
	    {
	    	e.printStackTrace();
	        return null;
	    }
	 }

	public List<Result2> getFinalResult(QuizSaveAns  QuizSaveAns ) 
	{
		String query =  "Select * from tbl_result where htmlcss=:htmlcss AND javascript =:javascript  AND springboot =:springboot  AND mysql =:mysql  AND java =:java";
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("htmlcss",QuizSaveAns .getHtmlcssOption()).addValue("javascript", QuizSaveAns .getJavascriptOption()).addValue("springboot", QuizSaveAns .getSpringbootOption()).addValue("mysql", QuizSaveAns .getMysqlOption()).addValue("java", QuizSaveAns .getJavaOption());
		
		 try 
		    {
		        return jdbcTemplate.query(query, parameterSource, new RowMapper<Result2>() 
		        {
		        	@Override
		            public Result2 mapRow(ResultSet rs, int rowNum) throws SQLException 
		        	{
		        		Result2 result = new Result2();
		            	result.setSuggestions(rs.getString("suggestions"));
		            	result.setSuggested_job(rs.getString("suggested_job"));
		            	
		            	return result;
		            }
		        });
		    } 
		    catch (EmptyResultDataAccessException e) 
		    {
		    	e.printStackTrace();
		        return null;
		    }
	}
}
