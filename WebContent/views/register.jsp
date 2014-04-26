<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/header.jsp">
<c:param name="title" value="HomePage"></c:param>
</c:import>

	<center>
		<div class="container">
			<form name="signup" method="post" id="signup"
				action="<%=response.encodeUrl(request.getContextPath())
					+ "/Login?action=createaccount"%>"
				onsubmit="return validatesignup()">
				<input type="hidden" name="action" value="createaccount">
				<h1>Create Account</h1>
				<hr />

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
					<tr>
						<td>Lastname:</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-user"></i></span> <input
									type="text" name="lastname" placeholder="Lastname" value="">
							</div>
					</tr>
					<tr>
						<td>Email:*</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-envelope"></i></span> <input
									type="text" name="email" placeholder="Email" value="">
							</div>
						</td>
						<td class="text-error" id="emailerror"></td>
					</tr>
					<tr>
						<td>Password:*</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" name="password" placeholder="Password" value="">
							</div>
						</td>
						<td class="text-error" id="passworderror"></td>
					</tr>
					<tr>
						<td>Confirm password:*</td>
						<td>
							<div class="input-prepend">
								<span class="add-on"><i class="icon-lock"></i></span> <input
									type="password" name="confirmpassword"
									placeholder="Confirm password" value="">
							</div>
						</td>
						<td class="text-error" id="confirmpassworderror"></td>
					</tr>
					<tr>
						<td><input class="btn btn-primary" type="submit"
							value="Register"></td>
					</tr>
				</table>
				<%
					if (request.getAttribute("message") != null) {
				%>
				<p class="text-error">
					<b><%=request.getAttribute("message")%></b>
				</p>
				<%
					}
				%>
				<p class="text-error" id="error" ></p>
			</form>
		</div>
	</center>


<c:import url="/footer.jsp"></c:import>
