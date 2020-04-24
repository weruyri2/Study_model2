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

	<h1>WebContent/admin_order/admin_order_detail.jsp</h1>
	
		<%
	
		List adminOrderDetail = (List)request.getAttribute("adminOrderDetail");
	
		int total = 0;
		
		OrderDTO odto = (OrderDTO)adminOrderDetail.get(0);
	
	%>
	
	<h2>결제 상세 내역  / 수정</h2>
	<h2>주문번호 : <%=odto.getO_trade_num() %></h2>
	
	<table border="1">
	 <tr>
	 	<td>상품명</td>
	 	<td>상품크기</td>
	 	<td>상품색상</td>
	 	<td>주문개수</td>
	 	<td>주문금액</td>
	 </tr>
	 
	 <%
	 	for(int i=0; i<adminOrderDetail.size(); i++){
	 		OrderDTO ordto = (OrderDTO)adminOrderDetail.get(i);
		
	 		total += (ordto.getO_sum_money());
	 %>
	 <tr>
		<td><%=ordto.getO_g_name() %></td>
		<td><%=ordto.getO_g_size() %></td>
		<td><%=ordto.getO_g_color() %></td>
		<td><%=ordto.getO_g_amount() %></td>
		<td><%=ordto.getO_sum_money() %></td>
	 
	 </tr>

	 <%		
	 	}
	 %>
	 
	 <tr>
	 	<td colspan="5">
	 		<h3>배송지 정보</h3>
	 		받는 사람 : <%=odto.getO_receive_name() %> <br>
	 		집전화 : <%=odto.getO_receive_phone() %> <br>
	 		휴대폰 : <%=odto.getO_receive_mobile() %> <br>
	 		배송지 주소 : <%=odto.getO_receive_addr1() %> <br>
	 		나머지 주소 : <%=odto.getO_receive_addr2() %> <br>
	 		기타 요구사항 : <%=odto.getO_memo() %> <br>
	 	</td>
	 </tr>
	 <tr>
	 
	 	<td colspan="5">
	 		<h3>결제 정보</h3>
	 			주문 합계 금액 : <%=total %> 원 <br>
	 			결제 방법 : <%=odto.getO_trade_type() %> <br>
	 			입금자 명 : <%=odto.getO_trade_payer() %> <br>
	 	</td>
	 	
	</tr> 	

	</table>
	
		<fieldset>
	<form action="./AdminOrderModify.ao" method="post">
	 <input type="hidden" name="trade_num" value="<%=odto.getO_trade_num()%>">
	 
	 택배 운송장번호 : <input type="text" name="trans_num"
                         value="<%=odto.getO_trans_num() %>"
                      ><br>
                주문상태 : <select name="status"> 
                  <option value="0"
                   <%if(odto.getO_status()==0) { %>
                	   selected
                   <%} %>
                  >대기중</option>
                  <option value="1" 
                  <%if(odto.getO_status()==1) { %>
                	   selected
                   <%} %>
                   >발송준비</option>
                  <option value="2" 
                  <%if(odto.getO_status()==2) { %>
                	   selected
                   <%} %>
                   >발송완료</option>
                  <option value="3"
                   <%if(odto.getO_status()==3) { %>
                	   selected
                   <%} %>
                   >배송중</option>
                   
                  <option value="4"
                  <%if(odto.getO_status()==4) { %>
                	   selected
                   <%} %>
                   >배송완료</option>
                  <option value="5"<%if(odto.getO_status()==5) { %> selected<%} %>>주문취소</option>
                </select> <br>
	
	<input type="submit" value="정보 수정하기(운송장번호처리,상태 변경)">
    </form>
	</fieldset>


</body>
</html>