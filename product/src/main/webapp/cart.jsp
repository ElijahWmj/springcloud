<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
</head>
<body>


    <h1>购物车信息展示</h1>
    <c:forEach items="${sessionScope.cart}" var="entry">

        商品id:${entry.key}&nbsp;&nbsp;&nbsp;
        购物数量:${entry.value}&nbsp;&nbsp;&nbsp; <br>
    </c:forEach>


    <a href="${pageContext.request.contextPath}/product/order">提交订单</a>

</body>
</html>