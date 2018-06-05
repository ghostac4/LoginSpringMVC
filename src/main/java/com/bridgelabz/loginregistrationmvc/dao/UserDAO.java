package com.bridgelabz.loginregistrationmvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bridgelabz.loginregistrationmvc.model.UserModel;

@Repository
public class UserDAO implements IUserDAO{
	
	private DataSource dataSource;
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	public UserDAO() {
	}
	
	@Autowired
	public UserDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean registerUser(UserModel userModel) {
		try(Connection connection = dataSource.getConnection()){
			String sql="insert into users(name,email,dateOfBirth,password,mobile)values(?,?,?,?,?)";
			Object[] args = new Object[] {userModel.getName(),userModel.getEmail(),userModel.getDateOfBirth(),
					userModel.getPassword(),userModel.getMobile()};
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, args);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public UserModel getUser(String email) {
		try(Connection connection = dataSource.getConnection()){
			String sql="select * from users where email=?";
			Object[] args = new Object[] {email};
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<UserModel> userModels= jdbcTemplate.query(sql, args, new RowMapper<UserModel>() {

				@Override
				public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserModel userModel = new UserModel();
					userModel.setName(rs.getString(2));
					userModel.setEmail(rs.getString(3));
					userModel.setDateOfBirth(rs.getString(4));
					userModel.setPassword(rs.getString(5));
					userModel.setMobile(rs.getLong(6));
					return userModel;
				}
			});
			if(userModels.isEmpty()) {
				return null;
			}else {
				return userModels.get(0);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void insertUuid(String username,String uuid) {
		try(Connection connection = dataSource.getConnection()){
			String sql="update users set uuid=? where email=?";
			Object[] args = new Object[] {uuid,username};
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, args);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public UserModel getUserByUuid(String uuid) {
		try(Connection connection = dataSource.getConnection()){
			String sql="select * from users where uuid=?";
			Object[] args=new Object[] {uuid};
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			UserModel userModel = jdbcTemplate.queryForObject(sql, args, new RowMapper<UserModel>() {

				@Override
				public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserModel userModel = new UserModel();
					userModel.setName(rs.getString(2));
					userModel.setEmail(rs.getString(3));
					userModel.setDateOfBirth(rs.getString(4));
					userModel.setPassword(rs.getString(5));
					userModel.setMobile(rs.getLong(6));
					return userModel;
				}
			});
			return userModel;
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean checkUser(String email) {
		UserModel userModel = getUser(email);
		if(userModel!=null)
	         return false;
	      return true;
	}

}
