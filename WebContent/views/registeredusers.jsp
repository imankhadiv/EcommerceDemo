<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>

<c:import url="/header.jsp">
 <c:param name="title" value="Users"></c:param>
</c:import>

<table class="table table-striped table-hover">
 <tr>
	<thead>
	  <th>Email</th>
	  <th>Firstname</th>
	  <th>Lastname</th>
	  <th>Role</th>
	  </thead>
 </tr>
 <%
  ResultSet users = (ResultSet) request.getAttribute("users");
 %>
 <%
  while (users.next()) {
 %>
 <tr>
 <tbody>
  <td><%=users.getString("email")%></td>
  <td><%=users.getString("first_name")%></td>
  <td><%=users.getString("last_name")%></td>
  <td><%=users.getString("role")%></td>
  </tbody>
 </tr>
 <%
 }
%>
</table>


<c:import url="/footer.jsp"></c:import>