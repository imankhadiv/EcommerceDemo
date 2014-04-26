<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>

<c:import url="/header.jsp">
 <c:param name="title" value="Users"></c:param>
</c:import>

<table class="table table-striped table-hover">
	<thead>
 <tr>
	  <th>title</th>
	  <th>abstract</th>
	  <th>auther id</th>
	  <th>review count</th>
	  <th>pdf path</th>
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
  <td><%=articles.getString("title")%></td>
  <td><%=articles.getString("abstract")%></td>
  <td><%=articles.getInt("auth_id")%></td>
  <td><%=articles.getString("review_count")%></td>
  <td><a href='<%=articles.getString("pdf_path")%>'><%=articles.getString("pdf_path")%></a></td>
  <td><%=articles.getString("created_at")%></td>
 </tr>
  </tbody>
 <%
 }
%>
</table>

<c:import url="/footer.jsp"></c:import>