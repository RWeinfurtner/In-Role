<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section id="main-content">
	<div class="container">

		<h4 id="messages">${message}</h4>

		<div class="hidden-xs col-sm-12">
			<c:url var="StaffHomePageImage" value="/img/InRoleStaff2Home.png" />
			<img id="womanImg" class="img-responsive" src="${StaffHomePageImage}"
				alt="WomanLogo">
		</div>

		<div class="col-xs-12 hidden-sm hidden-md hidden-lg">

			<h2 id="staffHeader">Staff Home</h2>
			<%-- 		<h4 id="welcome">Welcome <c:out value=" ${currentUser.Email}" /></h4> --%>


		</div>

		<div class="col-xs-12">

			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#createInvite">Create Employee/Student Invite</a>
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
								<input type="submit" value="Create New Invite">
							</form>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<c:url var="createEventURL" value="/createEvent" />
						<h4 class="panel-title">
							<a href="${createEventURL}">Create Event</a>
						</h4>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<c:url var="viewEventsHref" value="viewAllEvents" />
						<h4 class="panel-title">
							<a href="${viewEventsHref}">View Events</a>
						</h4>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="${sendScheduleHref}">Send Out Schedule</a>
						</h4>
					</div>
					<%--  <div id="sendSchedule" class="panel-collapse collapse">
					<div class="panel-body">
						<c:url var="sendScheduleFormAction" value="/invite" />
						
						<form method="POST" action="${sendScheduleFormAction}" value="/invite">
						<input type="text" name="email" placeholder="Please Enter New User Email" class="form-control"/>
						<div class="radio">
							<input type="radio" name="userType" value="3"> Student <br>
							<input type="radio" name="userType" value="4"> Employer 
						</div>
						<input type="submit" value="Send Out Schedule">
					</form>
					</div>
			    </div> --%>
				</div>
			</div>
		</div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />