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

 <h1>WebContent/goods_order/order_list.jsp</h1>

	<%
		
		List orderList = (List)session.getAttribute("orderList");
	
	%>
			
	
	<h2>주문 결제 확인</h2>
	
	<table border="1">
	 <tr>
		<td>주문번호</td>
		<td>상품명</td>
		<td>결제방법</td>
		<td>주문금액</td>
		<td>주문상태</td>
		<td>주문일시</td>
		<td>운송장번호</td>
	 </tr>
	 
	 <%
	 	for(int i=0; i<orderList.size(); i++){
	 		
	 		OrderDTO odto = (OrderDTO)orderList.get(i);
	 
	 %>
	<tr>
		<td>
		<a href="./OrderDetail.or?trade_num=<%=odto.getO_trade_num() %>">
		<%=odto.getO_trade_num() %></a></td>
		<td><%=odto.getO_g_name() %></td>
		<td><%=odto.getO_trade_type() %></td>
		<td><%=odto.getO_sum_money() %></td>
			
		<%
		String msg = "";
		
		if(odto.getO_status() == 0) {
			msg = "준비중";
		}else if(odto.getO_status() == 1){
			msg = "발송준비";
		}else if(odto.getO_status() == 2){
			msg = "발송완료";
		}else if(odto.getO_status() == 3){
			msg = "배송중";
		}else if(odto.getO_status() == 4){
			msg = "배송완료";
		}else if(odto.getO_status() == 5){
			msg = "주문취소";
		}else{
			msg = "관리자에게 문의하세요!";
		}
		%>
		
		<td><%=msg%></td>
		
		<td><%=odto.getO_date() %></td>
		<td><%=odto.getO_trans_num() %> </td>
	</tr>
	<%
	}
	%>
	</table>
	
	<h3><a href="./BasketList.ba">장바구니 페이지로</a></h3>
	
</body>
</html>