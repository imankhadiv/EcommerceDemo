<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/header.jsp">
	<c:param name="title" value="Form"></c:param>
</c:import>

	<div class="row">
		<div class="span4">
			<p>Overall Judgment</p>
			<select id="overall">
				<option value="champion">champion</option>
				<option value="favourable">favourable</option>
				<option value="indifferent">indifferent</option>
				<option value="detractor">detractor</option>
			</select>




			<p>Level</p>
			<select id="level">
				<option value="expert">expert</option>
				<option value="knowledgeable">knowledgeable</option>
				<option value="outsider">outsider</option>
			</select>

			<h4>Summary</h4>
			<textarea name="summary" id="summary" rows="5" cols="10">Summary</textarea>

			<h4>Secret Message</h4>
			<textarea name="secret" id="secret" rows="5" cols="10">enter your secret messsage to edittor</textarea>

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
				<!-- <input id="hiddene" name="numberOfErrors" style="visibility: hidden"
					value="" /> -->

				<h1 id="myerrors" onclick="addErrors()">Add More</h1>
			</div>
		</div>
	</div>
	<button type="button" onclick="createForm()">create</button>
	<!-- <input type="button" class="btn" value="Submit"/> -->
<br/>
<br/>
<br/>
<br/>
<c:import url="/footer.jsp"></c:import>
