<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>

<c:import url="/header.jsp">
	<c:param name="title" value="Users"></c:param>
</c:import>

<c:choose>
	<c:when test="${param.articleId eq null}">
		<c:redirect url="../home.jsp"></c:redirect>
	</c:when>
	<c:when test="${param.formId eq null}">
		<c:redirect url="../home.jsp"></c:redirect>
	</c:when>

	<c:otherwise>

		<%
			Article article = ((ArrayList<Article>) session
							.getAttribute("articles")).get(Integer
							.valueOf(request.getParameter("articleId")));
					ReviewForm form = article.getForms().get(
							Integer.valueOf(request.getParameter("formId")));
		%>
		<div class="hero-unit ">
			<h1>Summary</h1>
			<p><%=form.getSummary()%></p>
			<h3>Criticism</h3>
			<form name="author" method="get" id="reply" action="" >

				<%
					for (Reason reason : form.getReasons()) {
				%>
				<div class="container">

					<div class="row">
						<div class="span4 offset5">
							<h4>
								Title:
								<%=reason.getTitle()%></h3>
							</h4>
						</div>
					</div>
					<div class="row">

						<%
							ArrayList<Comment> comments = reason.getComments();
										for (int i = 0; i < comments.size(); i++) {
						%>



						<%
							if (i % 2 == 0) {
						%>

						<div class="span3">
							<p>
								date:
								<%=comments.get(i).getCreatedAt()%>
							</p>
							<textarea style="background-color:gray;"rows="8" cols="8">Reviewer said:<%=comments.get(i).getContent()%></textarea>

						</div>
						<%
							}else{
						%>
						<div class="span3">
							<p>
								date:

								<%=comments.get(i).getCreatedAt()%>
							</p>
							<textarea style="background-color:#C0C0C0 ;" rows="8" cols="8">You replied:<%=comments.get(i).getContent()%></textarea>


						</div>
						

						<%
							}
										}
										if (comments.size() % 2 != 0) {
						%>
						<div class="span3">
						<br/>
							<textarea rows="8" cols="8">Reply</textarea>
						</div>
						<%
							}%>
							</div>
							<% }%>
									


		</form>
					</div>
					</div>

		

	</c:otherwise>
</c:choose>

<c:import url="/footer.jsp"></c:import>