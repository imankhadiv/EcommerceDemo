<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="header.jsp">
	<c:param name="title" value="HomePage"></c:param>
</c:import>

<c:if test="${message != ''}">

	<h5 id="flash-message">${message}</h5>

</c:if>
<div class="hero-unit " id="container">

	<h2>The Self-Resourcing Electronic Journal</h2>
	<p id="explain">Search for a particular paper by title or by
		author(s) names or by date interval or by a subject keyword search.</p>
	<div class="input-prepend">
		<span class="add-on"><i class="icon-search"></i></span> <input
			type="text" name="search" placeholder="Search" value="">
	</div>
	<br />
	<button id="search" class="btn btn-primary btn-large" href="footer.jsp"
		onClick="search()">Search</button>
</div>




<c:import url="footer.jsp"></c:import>
