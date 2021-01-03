<%--
  Created by IntelliJ IDEA.
  User: zhangjingwei
  Date: 2021/1/3
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
    %>
</head>
<body>
<%=basePath%>
</body>
</html>
