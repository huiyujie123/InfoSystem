<%@ page language="java" import="java.util.Calendar" pageEncoding="GBK"%>
<%! Calendar rightNow ;%>
<%rightNow = Calendar.getInstance();%>
<%= rightNow.get(Calendar.YEAR)%>:
<%=rightNow.get(Calendar.MONTH)+1%>:
<%=rightNow.get(Calendar.DAY_OF_MONTH)%>
   