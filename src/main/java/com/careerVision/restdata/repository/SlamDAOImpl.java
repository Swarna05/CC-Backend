package com.careerVision.restdata.repository;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.careerVision.restdata.model.User;

/**
 * The Class PersonDAOImpl.
 */
@Repository
public class SlamDAOImpl {

    /** The jdbc template. */
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Saves User Details
     *
     * @param User
     * @return void
     * @throws Exception 
     */
	public void saveUserDetails(User user) throws Exception 
	{ //throws Exception is optional, it is required as I have added "throw new Exception();" in catch 
		System.out.println("First name"+ user.getFullName()+ "Last name+"+user.getEmail());
		String query = "Insert into tbl_user (user_name, email_id, contact_no,password, dob) VALUES(:username,:email,:contact,:password,:dob)";
        SqlParameterSource parameterSource = new MapSqlParameterSource("username", user.getFullName())
        		.addValue("email", user.getEmail()).addValue("contact", user.getContactNo()).addValue("password",user.getPassword()).addValue("dob", user.getDob());

        try {
            jdbcTemplate.update(query, parameterSource);
        } catch (DataAccessException e) {
        	throw new Exception();
        }
	}

	public User getUserDetails(long contactNo) {
		String query = "Select * from tbl_user where contact_no = :contactNo";
        SqlParameterSource parameterSource = new MapSqlParameterSource("contactNo", contactNo);
        try {
            return jdbcTemplate.queryForObject(query, parameterSource, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                	User user = new User();
                	user.setFullName(rs.getString("user_name"));
                	user.setDob(rs.getDate("dob"));
                	user.setEmail(rs.getString("email_id"));
                	user.setPassword(rs.getString("password")); //here password is the "password" column in tbl_user
                	user.setContactNo(rs.getLong("contact_no"));
                	return user;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
		
	}

}
