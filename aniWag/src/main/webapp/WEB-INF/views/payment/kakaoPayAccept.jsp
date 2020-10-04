<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:out value="${info.approved_at}"></c:out><br/>
	<c:out value="${info.partner_order_id}"></c:out><br/>
	<c:out value="${info.item_name}"></c:out><br/>
	<c:out value="${info.quantity}"></c:out><br/>
	<c:out value="${info.amount.total}"></c:out><br/>
	<c:out value="${info.payment_method_type}"></c:out><br/>
	

	
</body>
</html>