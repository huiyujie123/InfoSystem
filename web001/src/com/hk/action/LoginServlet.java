package com.hk.action;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.hk.po.Student;
import com.hk.utils.DBUtils;
import java.sql.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���������
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.println(name+"|���룺"+pwd);
		//�����ݿ��в�ѯ��û�и��û�
	    QueryRunner qr = new QueryRunner();
	     Connection conn =null;
	     try {
			 conn = DBUtils.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     String sql = "select * from user where username=? and password=?";
		Student u = null;
		try {
			 u = qr.query(conn,sql, new BeanHandler<>(Student.class),name,pwd);
			 System.out.println(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		//�ж���û��ֵ
		if (u != null) {
			response.getWriter().write("��½�ɹ�");
			//�����û����ĸ��� ��session��
			HttpSession session = request.getSession();
			session.setAttribute("user",u); 
			//��ת����ҳ��
			response.setHeader("refresh", "3,url=/Mystore-RegisterAndLogin/index.jsp");
		}else {
			response.getWriter().write("��¼ʧ��");
			//��ת��ע��ҳ��
			response.setHeader("refresh", "3,url=/web001/login.jsp");
		}
	}
}
