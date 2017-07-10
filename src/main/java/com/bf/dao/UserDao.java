package com.bf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bf.entity.UserEntity;

public interface UserDao {
	    @Select("SELECT id,name FROM user WHERE id=#{id}")  
	    public UserEntity getById(@Param(value = "id") int id);  
	      
	    @Select("SELECT * FROM user ORDER BY id")  
	    public List<UserEntity> getAll();  
	    
	   public List<UserEntity> getAlls();
	    
}
