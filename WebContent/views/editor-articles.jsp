<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>

<c:import url="/header.jsp">
	<c:param name="title" value="Users"></c:param>
</c:import>
<%
	ArrayList<Article> articles = (ArrayList<Article>) session
			.getAttribute("editorArticles");
%>

<%-- <c:if test="${articles == null}">
 
  <jsp:forward page="../home.jsp"></jsp:forward>
 
 
 </c:if>  --%>

<c:choose>
	<%-- 
	<c:when test="${articles == null}">
		<c:redirect url="../home.jsp"></c:redirect>
	</c:when> --%>


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

					String message = (String) request.getAttribute("message");
		%>
		<div class="hero-unit " id="container">
			<div class="row">
					<div class="span8">
						<h1><%=article.getTitle()%></h1>
					</div>
					<div class="span2"><br /> <a
								href="${pageContext.request.contextPath}/ReaderController?id=<%= articles.indexOf(article) %>"
								class="btn btn-success btn-large active">view</a>
					</div>
			
			</div>
			<hr>
			<div class="row">
				<div class="span6">

					<h2>Abstract</h2>
					<p><%=article.getAbst()%></p>
					
				</div>


				<div class="span3">

					<ul style="border-width: 5px; border-style: groove">
						<dt>Keywords</dt>
						<%
							for (Keyword keyword : article.getKeywords()) {
						%>
						<dd><%=keyword.getWord()%></dd>


						<%
							}
						%>
					</ul>


				</div>
			</div>
			<hr />

			<h3>Main User</h3>
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
					<%
						if (article.getUsers() != null
										&& article.getUsers().size() > 0) {
					%>
					<h4>Other users</h4>
					<table class="table table-hover table-borderd">

						<%
							for (User user : article.getUsers()) {
						%>
						<tr>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Email</th>
							<th>Affiliation</th>
						</tr>
						<tr>
							<td><%=user.getFirstname()%></td>
							<td><%=user.getLastname()%></td>
							<td><%=user.getEmail()%></td>
							<td><%=user.getAffiliation()%></td>

						</tr>
						<%
							}
						%>

						<%
							}
						%>
					</table>
				</div>

				<h3>Reviews</h3>
				<table class="table table-striped table-hover table-borderd">
					<tr class="row">
						<th>Editor Accepted</th>
						<th>Judgment</th>
						<th>Expertise</th>
						<th>Action</th>

					</tr>
					<%
						for (ReviewForm form : article.getForms()) {
					%>
					<%
						String progressClass;
									String progressStatus;
									if (form.getFormApproved().equals("0")) {
										progressClass = "bar-warning";
										progressStatus = "Pending";
									} else {
										progressClass = "bar-success";
										progressStatus = "Approved";
									}
					%>

					<tr class="row">
						<td class="span3">
							<div class="progress">
								<div class="progress-bar <%=progressClass%>" role="progressbar"
									style="width: 100%">
									<div class="sr-only text-center"><%=progressStatus%></div>
								</div>
							</div>
						</td>
						<td><%=form.getOverall()%></td>
						<td><%=form.getLevel()%></td>

						<td>
							<%
								if (form.getFormApproved().equals("1")) {
							%> <a
							href="${pageContext.request.contextPath}/views/editor-form.jsp?formId=<%= article.getForms().indexOf(form)
  
 %>&articleId=<%= articles.indexOf(article) %>"
							class="btn btn-primary btn-success .disabled">view</a>

						</td>
						<%
							} else {
						%>

						<a
							href="${pageContext.request.contextPath}/views/editor-form.jsp?formId=<%= article.getForms().indexOf(form)
  
 %>&articleId=<%= articles.indexOf(article) %>"
							class="btn btn-primary btn-warning .disabled">Approve</a>

						</td>

						<%
							}
						%>

						<%
							}
						%>
					</tr>


				</table>

				<form action="${pageContext.request.contextPath}/EditorController"
					method="post">

					<input type="hidden" value="<%=article.getId()%>"
						name="articleId">

					<button type="button" class="btn btn-danger  btn-lg ">Publish Article</button>


				</form>




			</div>
	</c:when>
	<c:otherwise>
		<div class="hero-unit " id="container">
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
						href="${pageContext.request.contextPath}/views/editor-articles.jsp?id=<%= articles.indexOf(article)
  
 %>"
						class="btn btn-success btn-lg active" role="button btn-sm">view</a></td>

					</td>
				</tbody>

				</tr>
				<%
					}
				%>
			</table>
		</div>

	</c:otherwise>
</c:choose>





<c:import url="/footer.jsp"></c:import>