package com.hk.test;

import java.util.List;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.hk.po.Student;
import com.hk.utils.BaseDao;

public class TestDB2 extends BaseDao {
	@Test
	  public void select()throws Exception {
		  String sql = "select* from t_stu";
		  QueryRunner qr = new QueryRunner();
		  Connection con = super.getConnection();
		  List<Student> users = 
				  qr.query(con,
						  sql,
						  new BeanListHandler<Student>(Student.class));
		  
		  super.closeConnection(con,null,null);
		  for(Student usr:users) {
			  System.out.println("ÐÕÃû£º"+usr.getStu_nm()+"\tÐÔ±ð£º"+usr.getSex());
		  }
	  }
}
