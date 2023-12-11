package com.careerVision.career.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.careerVision.career.model.QuizSaveAns;

@Repository
public class QuizSaveAnsDAOImpl 
{
	@Autowired
	 private NamedParameterJdbcTemplate jdbcTemplate;
	

	public void getSaveAns(QuizSaveAns quizans) throws Exception
	{
		//System.out.println("javaans:"+quizans.getJavaOption());
		
		String query = "Insert into  tbl_ans (contact_no,htmlcss_option,javascript_option,springboot_option,mysql_option,java_option) VALUES(:contactNo,:htmlcssOption,:javascriptOption,:springbootOption,:mysqlOption,:javaOption)";
		
        SqlParameterSource parameterSource = new MapSqlParameterSource("contactNo", quizans.getContactNo()).addValue("htmlcssOption", quizans.getHtmlcssOption())
        		.addValue("javascriptOption", quizans.getJavascriptOption()).addValue("springbootOption", quizans.getSpringbootOption()).addValue("mysqlOption", quizans.getMysqlOption()).addValue("javaOption", quizans.getJavaOption());

        try 
        {
        	jdbcTemplate.update(query, parameterSource);
        } 
        catch (DataAccessException e) 
        {
        	e.printStackTrace();
        	throw new Exception();
        }
	}
}