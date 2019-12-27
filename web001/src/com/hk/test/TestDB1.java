package com.hk.test;
import java.awt.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import com.hk.po.Student;
import com.hk.utils.BaseDao;
import com.hk.utils.DBUtils;
public class TestDB1 extends DBUtils {
	
	@Test
  public void insert() {
	  String sql = "INSERT INTO t_stu(stu_id,stu_nm,sex,class_no)"
	  		+ "values(?,?,?,?);";
	  Object []obj = {12323209,"惠雨洁","女",160603};
	  int nums = DBUtils.executeNonQuery(sql, obj);
	  if(nums>0) {
		  System.out.println("数据插入成功");
	  }
	  else {
		  System.out.println("数据插入失败");
	  }
  }
	@Test
	public void update() {
		  String sql = "UPDATE t_stu SET stu_nm=?,sex=?, class_no=?  where stu_nm like ?";
		  Object []obj = {"惠语猪","女",160603,"%"+"莫"+"%"};
		  int nums = DBUtils.executeNonQuery(sql, obj);
		  if(nums>0) {
			  System.out.println("数据更新成功");
		  }
		  else {
			  System.out.println("数据更新失败");
		  }
	  }

}
