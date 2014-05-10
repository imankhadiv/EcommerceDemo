<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="beans.Comment" %>
<%@ page import="java.util.List" %> 
<c:import url="/header.jsp">
 <c:param name="title" value="Users"></c:param>
</c:import>
<div class="hero-unit " id="container">
<% //TODO display exist comment %>
<%-- <% List<Comment> comments = ( List<Comment> ) request.getAttribute("commentList"); 
   System.out.println(comments.size());
%>
 --%>
<c:forEach items="${requestScope.commentList}" var="comment">
 <div class="col-lg-4">
 <%-- <c: test="${comment.parent_id==-1}"> --%>
 <% //TODO test if is reason %>
  <h2><c:out value="${comment.title}" /></h2>
      <blockquote><p class="text-danger" ><c:out value="${comment.content}" />
      </p></blockquote>
   </div>
  </c:forEach>
</div>

<div class="span4">
<h2>Comment</h2>
<h3>Comment</h3>
<h4>Comment</h4>
<textarea id="content" rows="5" cols="8"></textarea>
<br>
<button class="btn btn-primary" onclick="submitBtn()">submit</button>


</div>
<c:import url="/footer.jsp"></c:import>