package com.hk.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class DBUtils {
  private static ComboPooledDataSource ds = null;
  
   static {
	   ds = new ComboPooledDataSource("eshopInfo");
   }
   /**
    *  
    * @param sql
    * @param obj
    * @return
    */
   public static int executeNonQuery(String sql,Object...obj) {
	   int result = 0;
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		if(obj!=null&& obj.length!=0) {
			for(int i=0;i<obj.length;i++) {
				pstmt.setObject(i+1,obj[i]);
			}
		}
		
		result = pstmt.executeUpdate();	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		closeConnection(conn,pstmt,null);
	}
	   return result;
   } 
   public static Connection getConnection() throws SQLException {
	   return ds.getConnection();
   }
/**
 * 
 * @param con 数据库连接
 * @param ps 执行域
 * @param rs 结果集
 */
   public static void closeConnection(Connection con ,
		   PreparedStatement ps, ResultSet rs) {
	   if(rs!=null) {
		   try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   if(ps!=null) {
		   try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   if(con!=null) {
		   try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
}
