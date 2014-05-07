<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>

<c:import url="/header.jsp">
 <c:param name="title" value="Users"></c:param>
</c:import>
<div class="hero-unit " id="container">
<form action="ArticleToSelectServlet" method="get">

<table class="table table-striped table-hover table-borderd">
	<thead>
 <tr>
 	  <th>select</th>
	  <th>title</th>
	  <th>abstract</th>
	  <th>main auther</th>
	  <th>created at</th>
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
  <td><input type="checkbox" name="article_id" value=<%=articles.getString("id")%> /></td>
  <td><%=articles.getString("title")%></td>
  <td><%=articles.getString("abstract")%></td>
  <td><%=articles.getString("first_name")+" "+articles.getString("last_name") %></td>
  <%-- <td><a href='<%=articles.getString("pdf_path")%>'><%=articles.getString("pdf_path")%></a></td> --%>
  <td><%=articles.getString("created_at")%></td>
 </tr>
  </tbody>
 <%
 }
%>
</table>
<input class="btn" type="submit" value="select"/>
</form>
</div>
<c:import url="/footer.jsp"></c:import>