<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section>
	<div>
		<h1>Successfully Logged Out</h1>
		<div>
			<c:url var="loginURL" value="/login" />
			<a href="${loginURL}">Go To Log In</a>
		</div>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />