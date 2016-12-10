<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section >
	
		<h4 id="messages">${message}</h4>
		<h2 id="eventHeader">${selectedEvent.name}</h2>
			<c:url var="signUpAction" value="signUpForEvent"/>
<%-- 		<h4 id="welcome">Welcome <c:out value=" ${currentUser.Email}" /></h4> --%>
			<div class="media">
				<div class="media-left"></div>
				<div class="media-body">
				<c:url value="/event?eventId=${event.eventId}" var="eventHref"/>
					<p>Day of Event: ${selectedEvent.day}</p>
					<p>Start of Event: ${selectedEvent.startTime}</p>
					<p>End of Event: ${selectedEvent.endTime}</p>
					<p>Length of Interviews: ${selectedEvent.interviewLength} minutes</p>
					
				</div>
			</div>

			<c:if test="${currentUser.type == 'EMPLOYER'}">
			<div class="panel-group" id="accordion">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#signUp">Sign Up for Event</a></h4>
			    </div>
			    <div id="signUp" class="panel-collapse collapse">
					<div class="panel-body">
					<c:url value="signUpForEvent" var="signUpAction"/>
					<form method="POST" action="${signUpAction}">
						<input type ="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
						<input type="hidden" name="eventId" value="${selectedEvent.eventId}">
						<input type="submit" value="Sign Up for Event">
					</form>
					</div>
			    </div>
			 </div> 
		</div>
	</c:if>
		<c:if test="${currentUser.type == 'STAFF'}">
			<div class="panel-group" id="accordion">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#inviteStudent">Invite Student to Event</a></h4>
			    </div>
			    <div id="inviteStudent" class="panel-collapse collapse">
					<div class="panel-body">
						<div class="form-group">
						<form method="POST" action="${signUpAction}">
							<c:forEach items="${validCohorts}" var="cohort">
								<input type="hidden" name="cohort" value=>
							</c:forEach>
							<input type ="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
							<input type="hidden" name="eventId" value="${selectedEvent.eventId}">
							<input type="submit" value="Email Invited Students About Event">
						</form>
					</div>
			    </div>
			 </div>
		</div>
		</div>
	</c:if>
	<c:if test="${currentUser.type == 'STUDENT'}">
		<c:if test="${hasAccess == true}">
				<c:if test="${preferences != null}">
					<p>You have previously saved preferences for this event.</p>
					<p>Your current preferences:</p>
					<ul>
					<c:forEach var="employer" items="${registeredEmployers}">
						<li>${employer.name}: 
						<c:choose>
							<c:when test="${preferences.get(employer.id) == null}">
							No preference
							</c:when>
							<c:otherwise>
						${preferences.get(employer.id)}
						</c:otherwise>
						</c:choose>
						</li>
						<br>
					</c:forEach>
					</ul>
				</c:if>
				<div class = "form-group">
					<p>Please rank your top 3 employers, 1 being most desired and 2 being 2nd most desired. Zero denotes no preference.
					<c:url value="/submitEventPreferences" var="submitPreferencesAction"/>
					<form method="POST" action="${submitPreferencesAction}">
						<label>Select Employer Preferences:</label>
						<br>
						<c:forEach var="employer" items="${registeredEmployers}">
						<label for="${employer.id}">${employer.name}</label>
						<select name="employerPreference" id="${employer.id}" class="form-control">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="0">0</option>
						</select>
						</c:forEach>
						<input type ="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
						<input type="hidden" name="eventId" value="${selectedEvent.eventId}">
						<input type="hidden" name="prefs" value="${prefs}">
						<input type="submit" value="Submit Preferences">
					</form>
				</div>
		</c:if>
	</c:if>

</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
