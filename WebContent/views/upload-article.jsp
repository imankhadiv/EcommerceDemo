<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/header.jsp">
<c:param name="title" value="HomePage"></c:param>
</c:import>

<h1>Upload your article</h1>
<form action="../UploadArticle" method="post" enctype="multipart/form-data" >
				<fieldset>
					<legend>Form For Submitting Article</legend>
					<label>Email:</label>
					<input type="text" name="email" value="Main Contact">
					<label>Name:</label>
					<input type="text" name="firstname"/>
					<label>Lastname:</label>
					<input type="text" name="lastname"/>
					<label>Title</label>
					<input type="text" name="title"/>
					<label>Abstract</label>
					<textarea name="abstract" rows="5" cols="20">Value</textarea>
					<label>Keywords</label>
					<input type="text" name="keywords" value="Enter Maximum 10 keywords seperated by comma" />
					<label>upload your article</label>
					<input type="file" name="file"/>
					
					<br/>
					<button type="submit" class="btn">Search</button>
			
			
				<p class="text-error">
					<b><%= request.getAttribute("message") %></b>
				</p>
				<p>hello world</p>
				
				<p class="text-error" id="error" ></p>
				</fieldset>
		</form>



<c:import url="/footer.jsp"></c:import>