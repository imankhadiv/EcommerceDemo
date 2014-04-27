<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/header.jsp">
	<c:param name="title" value="HomePage"></c:param>
</c:import>

<h1>Upload your article</h1>
<div class="hero-unit " id="container">
	<form action="../UploadArticle" method="post"
		enctype="multipart/form-data">
		<legend>Form For Submitting Article</legend>

		<div class="row">
			<div class="span6">
				<table>
					<tr>
						<td>Firstname:</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" name="firstname" placeholder="Firstname" value="">
							</div>
						</td>
					</tr>
					<!-- <h4>More than one authors?</h4>
					<div class="authors">
						<input type="text" id="hiddenc" name="numberOfAuthors" style="visibility:hidden" value="0">

					</div>
 -->
					<tr>
						<td>Lastname:</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" name="lastname" placeholder="Lastname" value="">
							</div>
					</tr>
					<tr>
						<td>Email:</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span> <input
									type="text" name="email" placeholder="Email" value="">
							</div>
						</td>
						<td class="text-error" id="emailerror"></td>
					</tr>
					<tr>

						<td id="author" onclick="addAuthor()"><span id="author"
							class="add-on">Add author<i class="icon-plus"></i></span></td>
					</tr>
					<tr>
						<td>
							<div class="authors" style="position: fixed, top:30px, right:5px">
								<input type="text" id="hiddenc" name="numberOfAuthors"
									style="visibility: hidden" value="0">

							</div>

						</td>
					<tr>
						<td>Title:</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-pencil"></i></span> <input
									type="text" name="title" placeholder="Title" value="">
							</div>
						</td>
						<td class="text-error" id=""></td>
					</tr>
					<tr>
						<td>Abstract:</td>
						<td><textarea rows="5" cols="30" name="abstract"
								placeholder="Abstract"></textarea></td>
					</tr>
					<tr>
						<td>Keywords:</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-pencil"></i></span> <input
									type="text" name="keywords"
									placeholder="Enter Maximum 10 keywords seperated by comma"
									value="">
							</div>
						</td>
					</tr>
					<tr>
						<td>upload your article:</td>
						<td><input type="file" name="file" /></td>
					</tr>

					<tr>
						<td><button type="submit" class="btn">Upload</button></td>
					</tr>

				</table>

				<%-- <p class="text-error">
					<b><%=request.getAttribute("message")%></b>
				</p>
 --%>

				<!-- 
				<p id="author" onclick="addAuthor()">
					<span id="author" class="add-on">Add author<i
						class="icon-plus"></i></span>
				</p>
				 -->

			</div>
			<!-- <div class="span4">
			<h4>More than one authors?</h4>
			<div class="authors">
				<input type="text" id="hiddenc" name="numberOfAuthors"
					value="5" />

			</div>
			<p id="author" onclick="addAuthor()">
				<span id="author" class="add-on">Add author<i
					class="icon-plus"></i></span>
			</p>
		</div> -->
			<p class="text-error" id="error"></p>
		</div>
	</form>
</div>
<c:import url="/footer.jsp"></c:import>