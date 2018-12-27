<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
</head>
<body>


    <h1>商品信息展示</h1>
    <c:forEach items="${requestScope.productInfos}" var="info">

        商品名称:${info.prodcutName}&nbsp;&nbsp;&nbsp;
        商品价格:${info.productPrice}&nbsp;&nbsp;&nbsp;
        商品描述:${info.productDescription}   <a href="${pageContext.request.contextPath}/product/add?id=${info.productId}">添加到购物车中</a> <br>
    </c:forEach>

</body>
</html>