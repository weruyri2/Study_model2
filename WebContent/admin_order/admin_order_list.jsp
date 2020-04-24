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
	<h1>Webcontent/admingoods/admin_order_list.jsp</h1>
	
	<%
		List adminOrderList = (List)request.getAttribute("adminOrderList");
	
	%>
	
	
	<table border="1">
	 <tr>
	 	<td>주문번호</td>
	 	<td>주문자</td>
	 	<td>결제 방법</td>
	 	<td>주문 금액</td>
	 	<td>주문 상태</td>
	 	<td>주문 일시</td>
	 	<td>수정 / 삭제</td>	 	
	 </tr>
	 
	 <%
	 	for(int i=0; i<adminOrderList.size(); i++){
	 		OrderDTO odto = (OrderDTO)adminOrderList.get(i);
	 		
	 		
	 %>
		 <tr>
	 		<td><%=odto.getO_trade_num() %></td>
	 		<td><%=odto.getO_m_id() %></td>
	 		<td><%=odto.getO_trade_type() %></td>
	 		<td><%=odto.getO_sum_money() %></td>
	 		<td><%
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
	 		
	 		%><%=msg %></td>
	 		<td><%=odto.getO_date() %></td>
	 		<td><a href="./AdminOrderDetail.ao?trade_num=<%=odto.getO_trade_num()%>">수정</a>
	 		 /
	 		 	<a href="./AdminOrderDelete.ao?trade_num=<%=odto.getO_trade_num()%>">삭제</a></td>
	 	</tr>
	 <%
	 	}
	 
	 %>
	
	</table>
	
	
	<hr>
	<hr>
	<h3><a href="./Main.me">메인으로</a></h3>
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>