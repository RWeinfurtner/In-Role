<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section>
	<h1>View Schedule</h1>
	<c:if test="${currentUser.type == 'EMPLOYER'}">

	<div class="table-responsive">
		<table class="table">
		<tr>
			<th>${eventName}</th>
			<th>${currentEmployer.name}</th>
		</tr>
			<c:forEach items="${interviewTimes}" var="time">
			<tr>
				<td>${employerSchedule.get(time)}</td>
				<td>${time}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</c:if>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />