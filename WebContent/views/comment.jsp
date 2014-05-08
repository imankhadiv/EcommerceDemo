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
  <pre><c:out value="${comment.title}" /></pre>
      <blockquote><p ><c:out value="${comment.content}" />
      </p></blockquote>
   
  </c:forEach>
</div>
<c:import url="/footer.jsp"></c:import>