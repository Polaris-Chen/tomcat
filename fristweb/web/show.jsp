<%--
  Created by IntelliJ IDEA.
  User: chenzhenghui
  Date: 2019/10/8
  Time: 6:59 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>czh的第一个项目</title>
</head>
<style>


    h1{
        font-size:54px;
        color:#FFF;
    }
    h2{
        font-size:30px;
        color:#FF0000;
    }
    h6{
        font-size:24px;
        color:#FFF;
    }
    h5{
        font-size:55px;
        color:transparent
    }
</style>
<body style="background-image: url(image/0f04af422502a40b6c8dc19d53d1f348-2.jpg)">
<div style="text-align: center;">
    <table width="100%" border="0">
        <tr>
            <h5>占位符</h5>
        </tr>
        <tr>

        </tr>
        <tr>

            <td align="center" width="90%"><h1>欢迎使用在线阅读网站小说爬取工具</h1></td>

        </tr>
        <tr>
            <td align="center" width="90%"><h6>本站可提供小说的txt格式下载</h6></td>
        </tr>
        <tr>
            <td width="50%" align="center">

                <h2>
                    在使用前请复制小说网站的第一章的链接，粘贴到下面的文本框中并点击确定
                </h2>
            </td>
        </tr>
        <tr>
            <td width="100%" align="center">

                <form name="form1" action="MainServlet" method="get" >

                    <input name="url" type="text" style="width:600px; height:40px;" /><br>
                    <input type="button" width="200" align="center" value="确定" style="width:90px;height:1000px" onclick="check()">

                </form></td>
            <script language="JavaScript">

                function check(){
                    if(form1.url.value==""){
                        alert("请输入链接!")
                    }

                    else{
                        form1.submit()
                        alert("正在爬取........请勿关闭页面")

                    }
                }
            </script>

        </tr>

        <tr>
            <td height="200" width="100" align="center"><h6>跳转页面后点击下载链接下载</h6></td>
        </tr>
        <tr>
            <td height="200" width="100" align="center"><a href=""></a> <h5></h5></td>
        </tr>
    </table>
</div>
</body>
</html>