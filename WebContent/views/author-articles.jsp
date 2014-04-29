<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.Article"%>

<c:import url="/header.jsp">
	<c:param name="title" value="Users"></c:param>
</c:import>
<%
	ArrayList<Article> articles = (ArrayList<Article>) session
			.getAttribute("articles");
%>

<%-- <c:if test="${articles == null}">
 
  <jsp:forward page="../home.jsp"></jsp:forward>
 
 
 </c:if>  --%>

<c:choose>

	<c:when test="${articles == null}">
		<c:redirect url="../home.jsp"></c:redirect>
	</c:when>


	<c:when test="${param.id != null}">

		<%
			int id = Integer.valueOf(request.getParameter("id"));
		%>
		<%
			Article article = articles.get(id);
		%>
		<div class="hero-unit " id="container">
			<h2><%=article.getTitle()%></h2>
			<hr>
			<div class="row">
				<div class="span6">
					<h4>Abstract</h4>
					<p><%=article.getAbst()%></p>
				</div>
				<div class="span4">
					<table class="table">
						<th>Published</th>
						<td><%=article.getStatus()%></td>
						<tr>
							<th>Uploaded</th>
							<td><%=article.getCreatedAt()%></td>
					</table>


				</div>
			</div>
			<div>
				<hr>
				<h3>Reviews</h3>
		<table style="border:solid 2px blue" "class="table table-striped table-hover table-borderd">
					<tr>
						<th>Editor Accepted</th>
						<th>Judgment</th>
						<th>Expertise</th>
						<th>Action</th>
						
					</tr>
					<tr>
						<td><%=article.getStatus()%></td>
						<td><%=article.getCreatedAt()%></td>
						<td>
						<td><a
					href="${pageContext.request.contextPath}/views/author-articles.jsp?id=<%= articles.indexOf(article)
  
 %>"
					class="btn btn-primary btn-lg active" role="button btn-sm">view</a></td>

				</td>
					</tr>




				</table>



			</div>

		</div>

	</c:when>
	<c:otherwise>
		<h1>Articles</h1>

		<table class="table table-striped table-hover table-borderd">
			<tr>
			<thead>
				<th>Volume</th>
				<th>Edition</th>
				<th>Title</th>
				<th>Reviewed</th>
				<th>Published</th>
				<th>Uploaded</th>
				<th>Action</th>
			</thead>
			</tr>

			<%
				for (Article article : articles) {
			%>
			<tr>
			<tbody>
				<td><%=article.getVolume()%></td>
				<td><%=article.getEdition()%></td>
				<td><%=article.getTitle()%></td>
				<td><%=article.getReview_count()%></td>
				<td><%=article.getStatus()%></td>
				<td><%=article.getCreatedAt()%></td>

				<td><a
					href="${pageContext.request.contextPath}/views/author-articles.jsp?id=<%= articles.indexOf(article)
  
 %>"
					class="btn btn-primary btn-lg active" role="button btn-sm">view</a></td>

				</td>
			</tbody>

			</tr>
			<%
				}
			%>
		</table>

	</c:otherwise>
</c:choose>










<c:import url="/footer.jsp"></c:import>