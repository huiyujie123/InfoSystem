package com.hk.action;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hk.po.Student;
import com.hk.utils.DBUtils;
import java.sql.Connection;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistServlet");
		/**
		 * �ж��������֤���Ƿ���ȷ
		 * �������в���
		 * �Ѳ�����װ��user����
		 * ����uid
		 * д�뵽���ݿ�
		 * */
		//�����������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡ����   ��֤��
	    String code = request.getParameter("code");
	    String name = request.getParameter("username");
		System.out.println("code="+code);
		System.out.println(name);
		//�����ݿ��в�ѯ��û�и��û�
		
	     QueryRunner qr = new QueryRunner();
	     Connection conn =null;
	     try {
			 conn = DBUtils.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     String sql = "select * from user where username=?";
		Student u = null;
		try {
			 u = qr.query(conn,sql, new BeanHandler<>(Student.class),name);
			 System.out.println(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");

		//��ȡ���������ɵ���֤��
		String word = (String) this.getServletContext().getAttribute("checkCode");
		//�ж��������֤
		if (code.equals(word)&&u==null) {
			//�����ȷ
			//1.�������в���
			Map<String, String[]> parameterMap = request.getParameterMap();
			
			for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				System.out.println("----------");
				System.out.println(entry.getKey()+":"+Arrays.toString(entry.getValue()));
			}

			Student stu = new Student();
			//2.�ѽ��յĲ�����װ��user����
			try {
				BeanUtils.populate(stu, parameterMap);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.println(stu);
			
			String sql1="insert into user value(?,?,?)";
			Object []obj = {stu.getUsername(),stu.getPassword(),stu.getPhone()};
			
				 int nums = DBUtils.executeNonQuery(sql1, obj);
				 if(nums>0) {
					  System.out.println("���ݲ���ɹ�");
				  }
	
			//��ת����¼ҳ��
			response.getWriter().write("ע��ɹ�");
			
			response.sendRedirect("/web001/Login.jsp");
		} else if(code.equals(word)&&u!=null){
			//����ȷ���û���֤�������ת��ע��ҳ
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write("���û����ѱ�ע��");
			response.setHeader("refresh", "3;url=/web001/Regist.jsp");
			
		}else {
			response.getWriter().write("��֤���������������");
			response.setHeader("refresh", "3;url=/web001/Regist.jsp");
		}
	}
}

