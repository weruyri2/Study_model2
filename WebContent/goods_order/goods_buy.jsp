
<%@page import="net.goods.db.GoodsDTO"%>
<%@page import="net.basket.db.basketDTO"%>
<%@page import="net.member.db.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebCotent/goods/goods_buy.jsp</h1>
	
	<%
		ArrayList goodsList = (ArrayList)request.getAttribute("goodsList"); 
		ArrayList basketList = (ArrayList)request.getAttribute("basketList"); 
		MemberBean mb = (MemberBean)request.getAttribute("memberDTO");
	
	%>
	
	<!-- 주문 목록정보 (상품)-->
	<h1>결제 리스트</h1>
	
	<form action="OrderAdd.or" method="post">
	<table border="1">
	 <tr>

	  <td>사진</td>
	  <td>제품명</td>

	  <td>색상</td>
	  <td>수량</td>
	  <td>가격</td>
	  <td>삭제</td>
	 </tr>
	 

	 <%
	 	int total = 0;
	 
	 	for(int i=0; i<basketList.size(); i++){
	 		basketDTO bkdto = (basketDTO)basketList.get(i);
	 		GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
	 		
	 		String arr[] = gdto.getImage().split(",");
	 	
	 %>
	 	 <tr>
	 

	 	<td>
	 		<img src="upload/<%=arr[0]%>" width="125" height="100">
	 	</td>
	 	<td>
	 		<%=gdto.getName() %>
	 	</td>

	 	<td>
	 		<%=bkdto.getB_g_color() %>
	 	</td>
	 	<td>
	 		<%=bkdto.getB_g_amount() %>
	 	</td>
	 	<td>
	 		<%=gdto.getPrice() %>
	 	</td>
	 	
	 	<td>
	 		<a href="BasketDelete.ba?b_num=<%=bkdto.getB_num() %>">삭제</a>
	 	</td>
	 	
	 	<%
	 		total += bkdto.getB_g_amount()* gdto.getPrice();
	 	}
	 	%>
	 	</tr>
	 	<tr>
	 		<td colspan="6">총 금액 : <%=total %>원</td>
	 	</tr>
		<tr>
			<td colspan="6"> <h4>주문자 정보</h4> </td>
			</tr>
			<tr>
			<td colspan="3"> 이름 </td>
			<td colspan="3"> <%=mb.getName() %></td>
					</tr>
			<tr>
			<td colspan="3"> 휴대폰 </td>
			<td colspan="3"> <input type="text" name="phone"> </td>
					</tr>
			<tr>
			<td colspan="3"> 이메일</td>
			<td colspan="3"> <%=mb.getEmail() %> </td>
			</tr>
			
			<tr>
			<td colspan="6"> <h4>배송지 정보</h4></td>
			</tr>
						<tr>
			<td colspan="3"> 받는사람 </td>
			<td colspan="3"> <input type="text" name="o_receive_name"> </td>
					</tr>
								<tr>
			<td colspan="3"> 전화번호호(집전화) </td>
			<td colspan="3"> <input type="text" name="o_receive_phone">  </td>
					</tr>
								<tr>
			<td colspan="3"> 휴대폰 </td>
			<td colspan="3"><input type="text" name="o_receive_mobile"> </td>
					</tr>
			<tr>
			<td colspan="3"> 배송지주소 </td>
			<td colspan="3"> <input type="text" name="o_receive_addr1"> </td>
					</tr>
				<tr>
			<td colspan="3"> 배송지상세주소 </td>
			<td colspan="3"> <input type="text" name="o_receive_addr2"> </td>

					</tr>
			<tr>
			<td colspan="3"> 요구사항 </td>
			<td colspan="3"> <input type="text" name="o_memo"> </td>
					</tr>
			<tr>
			<td colspan="6">
			<h4>결제 정보</h4></td>
						</tr>
			<tr>
			<td colspan="3"> 온라인 입금(입금자명) </td>
			<td colspan="3"> <input type="text" name="o_trade_payer"> </td>
					</tr>
					
			<tr>
			<td colspan="6" align="center">
			<h1><input type="submit" value=결제하기>
			<input type="reset" value="다시쓰기"> </h1></td>
			</tr>
	</table>
	</form>


	<!-- 주문자 정보 -->

</body>
</html>