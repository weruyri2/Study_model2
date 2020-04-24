<%@page import="net.order.db.OrderDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/goods_order/order_detail.jsp</h1>

	<%
	
		List orderDetailList = (List)request.getAttribute("orderDetailList");
	
		int total = 0;
	
	%>
	
	<h2>결제 상세 내역</h2>
	
	<table border="1">
	 <tr>
	 	<td>상품명</td>
	 	<td>상품크기</td>
	 	<td>상품색상</td>
	 	<td>주문개수</td>
	 	<td>주문금액</td>
	 </tr>
	 
	 <%
	 	for(int i=0; i<orderDetailList.size(); i++){
	 		OrderDTO odto = (OrderDTO)orderDetailList.get(i);
		
	 		total += (odto.getO_sum_money());
	 %>
	 <tr>
		<td><%=odto.getO_g_name() %></td>
		<td><%=odto.getO_g_size() %></td>
		<td><%=odto.getO_g_color() %></td>
		<td><%=odto.getO_g_amount() %></td>
		<td><%=odto.getO_sum_money() %></td>
	 
	 </tr>
	 <%		
	 	}
	 %>
	 <tr>
	 	<td colspan="5">
	 		<h4>총 주문금액: <%=total %> 원</h4>
	 	</td>
	 
	 </tr>	
	</table>

	<h2><a href="./OrderList.or">결제 페이지로</a></h2>


</body>
</html>