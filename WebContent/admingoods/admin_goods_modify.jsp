<%@page import="net.admin.goods.db.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebCotent/admingoods/admin_goods_modify.jsp</h1>
 
 <%
 	GoodsDTO gdto = (GoodsDTO)request.getAttribute("gdto");
 
 %>
 
 	<h1>관리자 상품수정 페이지</h1>
 	
    <form action="./GoodsModifyAction.ag" method="post" 
          enctype="multipart/form-data">
          
      <input type="hidden" name="num" value="<%=gdto.getNum()%>">
    
      <table border="1">
        <tr>
          <td>카테고리</td>
          <td>
             <select name="category">
               <option value="outwear" <% if(gdto.getCategory().equals("outwear")){%> selected <%} %> >외투</option>
               <option value="fulldress" <% if(gdto.getCategory().equals("fulldress")){%> selected <%} %>>정장/신사복</option>
               <option value="Tshirts" <% if(gdto.getCategory().equals("Tshirts")){%> selected <%} %>>티셔츠</option>
               <option value="shirts" <% if(gdto.getCategory().equals("shirts")){%> selected <%} %>>와이셔츠</option>
               <option value="pants" <% if(gdto.getCategory().equals("pants")){%> selected <%} %>>바지</option>
               <option value="shoes" <% if(gdto.getCategory().equals("shoes")){%> selected <%} %>>신발</option>   
             </select>          
          </td>
        </tr>
        <tr>
          <td>상품명</td>
          <td>
             <input type="text" name="name" value="<%=gdto.getName()%>">
          </td>
        </tr>
        <tr>
          <td>판매가</td>
          <td>
            <input type="text" name="price" value="<%=gdto.getPrice()%>"></td>
        </tr>
        <tr>
          <td>색상</td>
          <td><input type="text" name="color" value="<%=gdto.getColor()%>"></td>
        </tr>
        <tr>
          <td>수량</td>
          <td><input type="text" name="amount" value="<%=gdto.getAmount()%>"></td>
        </tr>
        <tr>
          <td>크기</td>
          <td><input type="text" name="size" value="<%=gdto.getSize()%>"></td>
        </tr>
        <tr>
          <td>제품정보</td>
          <td><input type="text" name="content" value="<%=gdto.getCotent()%>"></td>
        </tr>
        
        <tr>
			<td>인기상품</td>
			<td><input type="radio" value="1" name="best" <% if(gdto.getBest()==1){%> checked <%} %>>예
			<input type="radio" value="0" name="best" <% if(gdto.getBest()==0){%> checked <%} %>>아니오
			</td>
        </tr>
        
        <tr>
           <td colspan="2">
             <input type="submit" value="상품 수정">
             <input type="reset" value="다시 수정">
           </td>
        </tr>
        
      </table>
    </form>
    
    
</body>
</html>