<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section class="profile">
	
	<h1 id="sectionHeader">Student Profile: <c:out value="${student.firstName} ${student.lastName}"/></h1>
<!-- 	<p id="instruction">(Click on bolded text to edit)</p>
 -->	<div class="row">
		<div class="col-sm-3">
			<p class="profileLabel text-right" id="smallStudentName"> Student Name:</p>
		</div>
		<div class="col-sm-9">
			<p><c:out value="${student.firstName} ${student.lastName}"/></p>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-3 text-right">
			<p class="profileLabel" id="cohort">Cohort:</p>
			</div>
			<div class="col-sm-9">
			<p><c:out value="${student.cohort}"/></p>
		</div>
	</div>
		
	<div class="row">
		<div class="col-sm-3 text-right">
			<p class="profileLabel" id="language">Language:</p>
		</div>
			<div class="col-sm-9">
			<p><c:out value="${student.language}"/></p>
		</div>

		</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />


