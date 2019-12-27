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
		 * 判断输入的验证码是否正确
		 * 接收所有参数
		 * 把参数封装成user对象
		 * 设置uid
		 * 写入到数据库
		 * */
		//解决乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取参数   验证码
	    String code = request.getParameter("code");
	    String name = request.getParameter("username");
		System.out.println("code="+code);
		System.out.println(name);
		//到数据库中查询有没有该用户
		
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

		//获取服务器生成的验证码
		String word = (String) this.getServletContext().getAttribute("checkCode");
		//判断输入的验证
		if (code.equals(word)&&u==null) {
			//如果正确
			//1.接收所有参数
			Map<String, String[]> parameterMap = request.getParameterMap();
			
			for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
				System.out.println("----------");
				System.out.println(entry.getKey()+":"+Arrays.toString(entry.getValue()));
			}

			Student stu = new Student();
			//2.把接收的参数封装成user对象
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
					  System.out.println("数据插入成功");
				  }
	
			//跳转到登录页面
			response.getWriter().write("注册成功");
			
			response.sendRedirect("/web001/Login.jsp");
		} else if(code.equals(word)&&u!=null){
			//不正确，用户验证码错误，跳转回注册页
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write("该用户名已被注册");
			response.setHeader("refresh", "3;url=/web001/Regist.jsp");
			
		}else {
			response.getWriter().write("验证码错误！请重新输入");
			response.setHeader("refresh", "3;url=/web001/Regist.jsp");
		}
	}
}

