<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>

<c:import url="/header.jsp">
 <c:param name="title" value="Users"></c:param>
</c:import>
<div class="hero-unit " id="container">
<table class="table table-striped table-hover table-borderd">
	<thead>
 <tr>
	  <th>title</th>
	  <th>abstract</th>
	  <th>main auther</th>
	  <th>created at</th>
	  <th>download</th>
	  <th>review</th>
	  
 </tr>
	  </thead>
 <%
  ResultSet articles = (ResultSet) request.getAttribute("article");
 %>
 <%
  while (articles.next()) {
 %>
 <tbody>
 <tr>
  <td><%=articles.getString("title")%></td>
  <td><%=articles.getString("abstract")%></td>
  <td><%=articles.getString("first_name")+" "+articles.getString("last_name") %></td>
  <td><%=articles.getString("created_at")%></td>
  <td><a href='<%=articles.getString("pdf_path")%>'><%=articles.getString("pdf_path")%></a></td> 
  <td><a href="${pageContext.request.contextPath}/JDBServlet?article_id=<%=articles.getString("id")%>&action=get_form">review</a></td>
 </tr>
  </tbody>
 <%
 }
%>
</table>
</div>
<c:import url="/footer.jsp"></c:import>