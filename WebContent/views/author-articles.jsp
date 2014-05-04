<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>

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
			if (id >= articles.size()) {
		%>
		<c:redirect url="../home.jsp"></c:redirect>


		<%
			}
		%>
		<%
			Article article = articles.get(id);
		%>
	<%--  <%
	 if(request.getParameter("formmessage") != null){
		%>
		 <div class="alert alert-error">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Info!</strong>
			${param.errormessage}

		</div> --%>  
		<%  if(article.getForms().size() != 0){ %>
		
			<div class="alert alert-error">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Info!</strong>
			Please reply to all the reviewers form before uploading your article
			

		</div>
			
		<%
			}else {
		%>
		<div class="alert alert-info">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Info!</strong>
			Your article has not been reviewed yet.

		</div>

		<%
			}
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
				<table class="table table-striped table-hover table-borderd">
					<tr class ="row">
						<th>Editor Accepted</th>
						<th>Judgment</th>
						<th>Expertise</th>
						<th>Action</th>

					</tr>
					<%
						for (ReviewForm form : article.getForms()) {
					%>
					<% String progressClass;
						String progressStatus;
					if(form.getFormApproved().equals("0")) {
						progressClass = "bar-warning";
						progressStatus = "Pending";
					}else{
						progressClass = "bar-success";
						progressStatus = "Approved";
					}
					%> 
					
					<tr class="row">
						<td class="span3">
							<div class="progress">
							  <div class="progress-bar <%= progressClass %>" role="progressbar" aria-valuenow="<%=form.getFormApproved()%>" aria-valuemin="0" aria-valuemax="1" style="width: 100%">
							    <div class="sr-only text-center"><%=progressStatus %></div>
							  </div>
							</div>
						</td>
						<td><%=form.getOverall()%></td>
						<td><%=form.getLevel()%></td>

						<td><a
							href="${pageContext.request.contextPath}/views/author-form.jsp?formId=<%= article.getForms().indexOf(form)
  
 %>&articleId=<%= articles.indexOf(article) %>"
							class="btn btn-primary btn-success">view</a></td>


					</tr>

					<%
						}
					%>





				</table>
				<form class="form" action="" method="post"
					enctype="multipart/form-data">

					<div style="width: 250px; display: inline-block">
						<input size="30" type="file" name="file" />
					</div><br/>
					<input type="submit" class="btn btn-primary btn-large">
				</form>



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
					class="btn btn-success btn-lg active" role="button btn-sm">view</a></td>

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