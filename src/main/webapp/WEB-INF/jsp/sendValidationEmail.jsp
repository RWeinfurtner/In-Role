<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="AccessEmailAction" value="/accessEmail" />

<form method="POST" id="accessEmailForm" action="${AccessEmailAction}">

	<input type = "hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<input type = "hidden" name="link" value="${signUpUrl}"/>
	<input type = "hidden" name="email" value="${email}"/>
			
	<div>
		<h5 class= "accessEmail" id="accessEmailText"> Click here to Automatically send an Email to the user notifying them of there access:</h5>
	</div>
			
	<div class ="email-box">
		<div class="form-group">
				<input type="submit" value="Send Access Code" class="btn btn-block" id="accessEmailButton" /><p>  </p>
		</div>
	</div>
</form>
				