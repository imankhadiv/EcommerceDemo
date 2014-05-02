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

	<div class="searcpanel">
	<ul id="tabs" class="nav nav-tabs">
		<li class="active" id="author"><a id="first" >by author</a></li>
		<li id="title"><a  id="second" >by Title</a></li>
		<li id="keyword"><a  id="third" >by Keyword</a></li>
		<li id="date"><a  id="forth" >by Date</a></li>
	</ul>
	<div class="input-prepend">
		<span class="add-on"><i class="icon-search"></i></span> <input
			type="text" name="search" placeholder="Search" id="input">
	</div>
	<br />
	<a id="search"
		href=""
		class="btn btn-primary btn-large">Search</a>
		
</div>
</div>




<c:import url="footer.jsp"></c:import>
