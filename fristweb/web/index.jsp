
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: chenzhenghui
  Date: 2019/10/6
  Time: 4:27 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <jsp:forward page="show.jsp"></jsp:forward>
    <%

       Cookie[] cookies=request.getCookies();
       String user="";

       if(cookies!=null){
           for (int i =0;i<cookies.length;i++){
               if (cookies[i].getName().equals("mycookie")){
                   user=cookies[i].getValue();

               }
           }
       }
       if (user.equals("")){
    %>

       游客你好！
       请输入姓名

    <%

        }
       else{
    %>
    欢迎[<b><%=user%></b>]<br>

  <%
      }
  %>
  </body>
</html>
