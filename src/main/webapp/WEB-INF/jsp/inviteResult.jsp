<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section id="main-content">
	<div class="container">
		<div class = "inviteText">
		<h2 id="inviteResult">Invitation Created!</h2>
		<p>Share the following link:</p>
		</div>
		<a id="copytext" href="${signUpUrl}">${signUpUrl}</a>
		<br>
		<br>
		<!-- <TEXTAREA ID="holdtext" STYLE="display:none;"></TEXTAREA>
		<button onClick="ClipBoard();">Copy to Clipboard</BUTTON>
		<button onClick="EmailLink();">Send in E-Mail</BUTTON> -->
		<!-- <form id="emailInvite" method="POST" action="/emailInvite">
			<button type="submit" class="btn btn-block " id="emailButton" ><p> Send in E-Mail </p> </button>
		</form> -->
		<br>
		<br>
	</div>
</section>

<SCRIPT LANGUAGE="JavaScript">

	function ClipBoard() {
		holdtext.innerText = copytext.innerText;
		Copied = holdtext.createTextRange();
		Copied.execCommand("Copy");
	}
	
	function EmailLink() {
		
	}


</SCRIPT>
<c:import url="/WEB-INF/jsp/sendValidationEmail.jsp" />
<c:import url="/WEB-INF/jsp/common/footer.jsp" />