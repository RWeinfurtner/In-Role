<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section>
	<h2>Tech Elevator Hiring Network</h2>
	<ul>
		<c:forEach items="${employers}" var="employer">
			<div class="media">
				<div class="media-left"></div>
				<div class="media-body">
					<h5 class="media-heading" id="employerName">${employer.name}</h5>
					<p id="employerDesc">${employer.description}</p>
				</div>
			</div>
			
		</c:forEach>
	</ul>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />