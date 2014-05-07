<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.sql.ResultSet"%>
<c:import url="/header.jsp">
	<c:param name="title" value="Form"></c:param>
</c:import>
 <%  
 ResultSet form = (ResultSet) request.getAttribute("form"); %>
 <%
 	String overallstr = "";
 	String levelstr = "";
 	String summary = "";
 	String secret_message = "";

 	while (form.next()) {
 		overallstr = form.getString("overall");
 		levelstr = form.getString("level");
 	}
 %>
<c:set var="overallstr" scope="session" value="${2000*2}"/>
	<div class="row">

		<div class="span4">
			<p>Overall Judgment</p>
			<select id="overall">
				<option value="champion"  <%=(overallstr.equals("detractor")? "selected" :"" )%>>champion</option>
				<option value="favourable"  <%=(overallstr.equals("detractor")? "selected" :"" )%> >favourable</option>
				<option value="indifferent"  <%=(overallstr.equals("indifferent")? "selected" :"" )%>>indifferent</option>
				<option value="detractor"  <%=(overallstr.equals("detractor")? "selected" :"" )%>>detractor</option>
			</select>

			<p>Level</p>
			<select id="level">
				<option value="expert" <%=(levelstr.equals("levelstr")? "selected" :"" )%>>expert</option>
				<option value="knowledgeable" <%=(levelstr.equals("knowledgeable")? "selected" :"" )%>>knowledgeable</option>
				<option value="outsider" <%=(levelstr.equals("outsider")? "selected" :"" )%>>outsider</option>
			</select>

			<h4>Summary</h4>
			<textarea name="summary" id="summary" rows="5" cols="10" ><%=form.getString("summary")%></textarea>

			<h4>Secret Message</h4>
			<textarea name="secret" id="secret" rows="5" cols="10"><%=form.getString("secret_message")%></textarea>
			<div><input type="checkbox" id="secret_message"/>send email to editor<br/></div>

		</div>
		<div class="span4">
			<h3>Substantive Criticism</h3>
			<div class="criticism">
				<input id="hiddenc" name="numberOfCriticism"
					style="visibility: hidden" value="" />

				<!-- <p id="myCriticism" onclick="addCriticism()">
					<span id="myCriticism" class="add-on">Add Criticism<i
						class="icon-lock"></i></span>
				</p> -->
			</div>
				<p id="myCriticism" onclick="addCriticism()">
					<span id="myCriticism" class="add-on">Add Criticism<i
						class="icon-lock"></i></span>
				</p>
		</div>
		<div class="span4">
			<h3>Errors</h3>
			<div class="errors">

				<h1 id="myerrors" onclick="addErrors()">Add More</h1>
			</div>
		</div>
	</div>
	<button class="btn btn-primary" type="button" onclick="createForm()">update</button>
	<button class="btn btn-primary" type="button" onclick="createForm()">submit</button>
	<!-- <input type="button" class="btn" value="Submit"/> -->
<br/>
<br/>
<br/>
<br/>
<c:import url="/footer.jsp"></c:import>
