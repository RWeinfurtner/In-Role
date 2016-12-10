<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section id="main-content">
	<div class="container">
	
	<div class="hidden-xs col-sm-12">
			<h4 id="messages">${message}</h4>
	
			<c:url var="StudentHomePageImage" value="/img/InRoleStudentsHome.png" />
			<img id="StudentsImg" class="img-responsive"
				src="${StudentHomePageImage}" alt="StudentsLogo">
	</div>
	
	<div class="col-xs-12 hidden-sm hidden-md hidden-lg">
		<h2 id="studentHeader">Student Home</h2>
		<%-- 		<h4 id="welcome">Welcome <c:out value=" ${currentUser.Email}" /></h4> --%>
		
	</div>
	
	<div class="col-xs-12">
		<div class="panel-group" id="accordion">
<%-- 			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#selectEmployers">Select Employers</a>
					</h4>
				</div>
				<div id="selectEmployers" class="panel-collapse collapse">
					<div class="panel-body">
						<form method="POST" action="${formAction}">
							<input type="submit" value="Select Employers">
						</form>
					</div>
				</div>
			</div> --%>
			<%-- <div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#updateProfile">Update Profile</a>
					</h4>
				</div>
				<div id="updateProfile" class="panel-collapse collapse">
					<div class="panel-body">
						<form method="POST" action="${updateProfileAction}" value="">
							<input type="submit" value="Update Profile">
						</form>
					</div>
				</div>
			</div> --%>
			<div class="panel panel-default">
			    <div class="panel-heading">
			      <c:url var="viewEventsHref" value="viewAllEvents"/>
			      <h4 class="panel-title">
			      	<a href="${viewEventsHref}">Event Schedule</a>
			      </h4>
			    </div>
			 </div>		 
		</div>
	</div>
	
	</div>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />