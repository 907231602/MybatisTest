package com.bf.springTest;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.bf.dao.UserDao;
import com.bf.entity.UserEntity;

/**
 * mybatis测试
 * testGetById，xml文件配置映射
 * testGetByIds，注解映射
 * @author Administrator
 *
 */
public class TestSpring {

		private Reader reader;  
	    private SqlSessionFactory sqlSessionFactory;  
	  
	    @Before  
	    public void setUp() throws Exception {  
	        try {  
	            reader = Resources.getResourceAsReader("mybatis.xml");  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
	    }  
	    
	    //xml文件配置查询数据
	    @Test  
	    public void testGetById() {  
	        SqlSession session = sqlSessionFactory.openSession();  
	        try {  
	            UserEntity user;  
	  
	            Object obj = session.selectOne("com.bf.mapping.user.getByID", 4);  
	            if (null == obj) {  
	                System.out.println("the result is null.");  
	            } else {  
	                user = (UserEntity) obj;  
	               
	                System.out.println(user);  
	            }  
	        } finally {  
	            session.close();  
	        }  
	    }  
	
	    //注解方式查询数据
	    @Test  
	    public void testGetByIds() {  
	        SqlSession session = sqlSessionFactory.openSession();  
	        try {  
	            UserDao cp = session.getMapper(UserDao.class);  
	            UserEntity c = cp.getById(5);  
	            if (null == c) {  
	                System.out.println("the result is null.");  
	            } else {  
	                System.out.println(c);  
	            }  
	        } finally {  
	            session.close();  
	        }  
	    }  
	    
	    /** 
	     * 测试批量删除 
	     */  
	    @Test  
	    public void testDeleteBatch() {  
	        SqlSession session = sqlSessionFactory.openSession();  
	        try {  
	            List<Integer> idList = new ArrayList<Integer>();  
	            idList.add(1);  
	            idList.add(2);  
	            idList.add(3); 
	            session.delete("com.bf.mapping.user.deleteBatch", idList);  
	            session.commit();  
	        } finally {  
	            session.close();  
	        }  
	    } 
	    
	    
	   @Test  
	    public void testGetAlls() {  
	        SqlSession session = sqlSessionFactory.openSession();  
	        try {  
	            UserDao cp = session.getMapper(UserDao.class);  
	            List<UserEntity> list = cp.getAlls();  
	            for (UserEntity c : list) {  
	                System.out.println(c);  
	            }  
	        } finally {  
	            session.close();  
	        }  
	    }  

}
