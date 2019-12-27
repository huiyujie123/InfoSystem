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
	  Object []obj = {12323209,"�����","Ů",160603};
	  int nums = DBUtils.executeNonQuery(sql, obj);
	  if(nums>0) {
		  System.out.println("���ݲ���ɹ�");
	  }
	  else {
		  System.out.println("���ݲ���ʧ��");
	  }
  }
	@Test
	public void update() {
		  String sql = "UPDATE t_stu SET stu_nm=?,sex=?, class_no=?  where stu_nm like ?";
		  Object []obj = {"������","Ů",160603,"%"+"Ī"+"%"};
		  int nums = DBUtils.executeNonQuery(sql, obj);
		  if(nums>0) {
			  System.out.println("���ݸ��³ɹ�");
		  }
		  else {
			  System.out.println("���ݸ���ʧ��");
		  }
	  }

}
