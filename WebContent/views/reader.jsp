<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>

<c:import url="/header.jsp">
	<c:param name="title" value="Reader"></c:param>
</c:import>


<div class="hero-unit " id="container">

	<%
		ArrayList<Article> articles = (ArrayList<Article>) session
				.getAttribute("readerArticles");
	%>

	<%
		if (articles == null) {
	%>
	<jsp:forward page="../home.jsp"></jsp:forward>
	<%
		} else if ((request.getParameter("id") == null)) {
	%>



	<table class="table table-striped table-hover table-borderd">
		<thead>
			<tr>
				<th>Volume</th>
				<th>Edition</th>
				<th>Title</th>
				<th>Uploaded</th>
				<th>Action</th>

			</tr>
		</thead>
		<tbody>
			<%
				for (Article article : articles) {
			%>

			<tr>

				<td><%=article.getVolume()%></td>

				<td><%=article.getEdition()%></td>
				<%
					request.setAttribute("articles", articles);
				%>

				<td><a
					href="${pageContext.request.contextPath}/views/reader.jsp?id=<%= articles.indexOf(article) %>"><%=article.getTitle()%></a></td>

				<td><%=article.getCreatedAt()%></td>
				<td><a
					href="${pageContext.request.contextPath}/views/reader.jsp?id=<%= articles.indexOf(article) %>"
					class="btn btn-primary btn-lg active">view</a></td>
			</tr>

			<%
				}
			%>

		</tbody>

	</table>


	<%
		} else {
	%>

	<%
		Article article = articles.get(Integer.valueOf(request
					.getParameter("id")));
	%>
	<div>

		<h1><%=article.getTitle()%></h1>
		<hr>
		<div class="row">
			<div class="span6">

				<h2>Abstract</h2>
				<p><%=article.getAbst()%></p><br/>
				<a
					href="${pageContext.request.contextPath}/ReaderController?id=<%= articles.indexOf(article) %>"
					class="btn btn-primary btn-lg active">view</a></td>
			</div>
			<div class="span3">
				<ul style="border: 2px solid">
					<%
						for (Keyword keyword : article.getKeywords()) {
					%>
					<li><%=keyword.getWord()%></li>


					<%
						}
					%>
				</ul>


			</div>
		</div>
		
		<table class="table table-hover table-borderd">
			<tr>
				<th>Firstname</th>
				<td><%=article.getMainUser().getFirstname()%></td>
			</tr>
			<tr>
				<th>Lastname</th>
				<td><%=article.getMainUser().getLastname()%></td>

			</tr>
			<tr>
				<th>Email</th>
				<td><%=article.getMainUser().getEmail()%></td>
			</tr>

		</table>

		<div class="row">
			<div class="span4">
			
				<table class="table table-hover table-borderd">
				
				<% for(User user:article.getUsers()) {%>
			<tr>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>Email</th>
				<th>Affiliation</th>
			</tr>
			<tr>
				<td><%= user.getFirstname()%></td>
				<td><%= user.getLastname()%></td>
				<td><%= user.getEmail()%></td>
				<td><%= user.getAffiliation()%></td>

			</tr>
			
<%} %>
		</table>
			
			</div>
		</div>
	</div>

	<%
		}
	%>

</div>

<c:import url="/footer.jsp"></c:import>
