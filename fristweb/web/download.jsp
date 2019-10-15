<%--
  Created by IntelliJ IDEA.
  User: chenzhenghui
  Date: 2019/10/12
  Time: 10:33 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载地址</title>
</head>
<style>
    h1{
        font-size:54px;
        color:#FFF;
    }
    h2{
        font-size:45px;
        color:#FFF;
    }

</style>
<body style=" background-repeat:no-repeat;background-image: url(image/timg3.jpeg);background-size:cover">
<div style="text-align: center;">

<h1>请点击下面的链接下载</h1>
<% String name=(String) request.getAttribute("name");
    name=name+".txt";
    //System.out.println(name);
String path=request.getContextPath()+"/text/"+name;
%>
<a href =<%=path%>><h2>下载链接</h2></a>
</div>
</body>
</html>
