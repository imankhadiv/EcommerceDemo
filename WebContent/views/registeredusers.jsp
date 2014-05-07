<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>

<c:import url="/header.jsp">
	<c:param name="title" value="Users"></c:param>
</c:import>

<div class="hero-unit " id="container">
<% String message = (String)request.getAttribute("message"); %>
 <% if(message != null){%>
            <div class="alert alert-error">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Error!</strong>
			       <%= message %>
		    </div>
		    
		    <%} %>
		
<h3>Users</h3>
<hr/>
<table class="table table-hover .table-striped .table-bordered .table-condensed">
	<tr>
		<th>Email</th>
		<th>Firstname</th>
		<th>Lastname</th>
		<th>Role</th>
		<th>Invite</th>
	</tr>
	<%
		ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
		User currentUser = (User)request.getSession().getAttribute("user");
	%>

	<%
		for (User user : users) {
	%>
	<% if(currentUser.getEmail().equals(user.getEmail())){ %>
	<tr  style="background-color:#99FF33
	">
	<%}else{ %>
	<tr  style="background-color:#EBF7FB
	">
	<%} %>
	
		<td ><%=user.getEmail()%></td>
		<td><%=user.getFirstname()%></td>
		<td><%=user.getLastname()%></td>
		<td><%=user.getRole()%></td>
		<% if(!user.getRole().equals("editor")){ %>
		<td>
		
	<a
		href="${pageContext.request.contextPath}/EditorController?action=invite&&userId=<%=user.getId()%>" class="btn btn-primary"><i class="icon-envelope"></i></a> 
		
		
		</td>
		<%}else{ %>
		<td></td>
		<%} %>
	
	</tr>
	<%
		}
	%>
</table>
</div>

<c:import url="/footer.jsp"></c:import>