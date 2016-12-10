<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section id="main-content">
	<div class="container">

		<div class="hidden-xs col-sm-12">
			<c:url var="AdminHomePageImage" value="/img/InRoleAdminHome.png" />
			<img id="buildingImg" class="img-responsive"
				src="${AdminHomePageImage}" alt="buildingLogo">
		</div>

		<div class="col-xs-12 hidden-sm hidden-md hidden-lg">
			<h2 id="adminHome">Admin Home</h2>
			<%-- <h4 id="welcome">Welcome <c:out value=" ${currentUser.Email}" /></h4> --%>
			<h4 id="messages">${message}</h4>
		</div>

		<div class="col-xs-12">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#createStaff"> Create Staff Member</a>
						</h4>
					</div>
					<div id="createStaff" class="panel-collapse collapse">
						<div class="panel-body">
							<c:url var="staffFormAction" value="/createStaff" />
							<form method="POST" action="${staffFormAction}">
								<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
								<input type="text" name="email"
									placeholder="Please Enter New User Email" class="form-control" />
								<input type="text" name="password"
									placeholder="Please Enter New User Password"
									class="form-control" /> <input type="submit"
									value="Create New Staff User">
							</form>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#createInvite">Create Employer/Student Invite</a>
						</h4>
					</div>
					<div id="createInvite" class="panel-collapse collapse">
						<div class="panel-body">
							<c:url var="inviteFormAction" value="/invite" />
							<form method="POST" id="inviteForm" action="${inviteFormAction}">
								<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
								<input type="email" name="email"
									placeholder="Please Enter New User Email" class="form-control" />
								<div class="radio">
									<input type="radio" name="userType" value="3"> Student
									<br> <input type="radio" name="userType" value="4">
									Employer
								</div>
								<input id="inviteButton" type="submit" value="Create New Invite">
							</form>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<!-- 					<div class="panel-heading"> -->
					<!-- 						<h4 class="panel-title"> -->
					<!-- 							<a data-toggle="collapse" data-parent="#accordion" -->
					<!-- 								href="#createEvent">Create Event</a> -->
					<!-- 						</h4> -->
					<!-- 					</div> -->
					<!-- 					<div id="createEvent" class="panel-collapse collapse"> -->
					<!-- 						<div class="panel-body"> -->
					<%-- 							<c:url var="createEventURL" value="createEvent" /> --%>
					<%-- 							<form method="GET" action="${createEventURL}"> --%>
					<!-- 								<input type="submit" value="Create New Event"> -->
					<!-- 							</form> -->
					<!-- 						</div> -->
					<!-- 					</div> -->

					<div class="panel-heading">
						<c:url var="createEventURL" value="/createEvent" />
						<h4 class="panel-title">
							<a href="${createEventURl}">Create Event</a>
						</h4>
					</div>
					<!-- 						<div class="panel-body"> -->
					<%-- 							<c:url var="createEventURL" value="createEvent" /> --%>
					<%-- 							<form method="GET" action="${createEventURL}"> --%>
					<!-- 								<input type="submit" value="Create New Event"> -->
					<!-- 							</form> -->
					<!-- 						</div> -->
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />