<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section id="main-content">
	<div class="container">

		<div class="col-xs-12 hidden-sm hidden-md hidden-lg">
			<h4 id="messages">${message}</h4>

			<h2 id="employeeHome">Employee Home</h2>
			<!-- <h3>Hello <c:out value="${user.Email}"/></h3>  -->
		</div>

		<div class="hidden-xs col-sm-12">
			<h4 id="messages">${message}</h4>

			<c:url var="EmployerHomePageImage"
				value="/img/InRoleEmployerHome.png" />
			<img id="EmployerImg" class="img-responsive"
				src="${EmployerHomePageImage}" alt="EmployerLogo">

		</div>
		<div class="col-xs-12">

			<div class="panel-group" id="accordion">
				<%-- <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#updateProfile">Update Profile</a></h4>
			    </div>
			    <div id="updateProfile" class="panel-collapse collapse">
					<div class="panel-body">	
						<form method="POST" action="${updateProfileAction}" value="">
						<input type="submit" value="Update Profile">
					</form>
					</div>
			    </div> --%>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:url var="viewEventsHref" value="viewAllEvents" />
				<h4 class="panel-title">
					<a href="${viewEventsHref}">Sign Up for an Event</a>
				</h4>
			</div>
		</div>
	</div>
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />