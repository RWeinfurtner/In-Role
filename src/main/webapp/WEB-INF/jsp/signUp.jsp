<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<section id="main-content">
	<div class="container">
	
	<h2 id="employerSignUp">Signup</h2>

		<c:url var="formAction" value="/signUp" />
		<form method="POST" id ="signUpForm" action="${formAction}">
			<input type = "hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<input type = "hidden" name="inviteId" value="${invite.id}"/>
			
<!--  Email box -->			
			<div class= "form-group">
				<!-- <label for= "email"> Email Address: </label> -->
				<input type= "email" id="email" name="email" id="email" placeHolder = "Enter Email Address" class="form-control" />
			</div>
			
<!--  Password box -->

			<div class= "form-group">
			<!-- 	<label for= "password"> Password: </label> -->
				<input type= "password" id="password" name="password" id="password" placeHolder = "Enter Password" class="form-control" />
			</div>
			
<!-- Verify password box  -->

			<div class= "form-group">
			<!-- 	<label for= "passwordVerification"> Verify Password: </label> -->
				<input type= "password" id="verifyPassword" name="verifyPassword" placeHolder = "Verify Password" class="form-control" />
			</div>
			
<!--  Employer box -->
			
			<c:if test="${invite.userType == 'EMPLOYER'}">
				<div class= "form-group">
					<input type= "text" id="employerName" name="employerName" placeHolder = "Employer/Company Name" class="form-control" />
				</div>
			</c:if>

<!-- Student box  -->

			<c:if test="${invite.userType == 'STUDENT'}">
				<div class = "form-group">
					<input type = "text" id="studentFirstName" name="studentFirstName" placeHolder = "First Name" class="form-control" />
				</div>
				
				<div class = "form-group">
					<input type = "text" id="studentLastName" name="studentLastName" placeHolder = "Last Name" class="form-control" />
				</div>
				
				<div class = "form-group">
					<label for="cohort">Select Cohort:</label>
					<select name="cohort" id="cohort" class="form-control">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
					</select>
				</div>
				
				<div class = "form-group">
					<label for="language">Select Language:</label>
					<select name="language" id="language" class="form-control">
						<option value="C#">C#</option>
						<option value="Java">Java</option>
					</select>
				</div>
			</c:if>

<!-- Submit/continue -->

			<button type= "submit" class= "customButton" class="btn btn-default"> Sign up </button>
		</form>
	</div>

</section>

<script src="/capstone/src/main/webapp/js/signupValidation.js"></script>
<c:url var="validate" value="/js/signupValidation.js"/>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

