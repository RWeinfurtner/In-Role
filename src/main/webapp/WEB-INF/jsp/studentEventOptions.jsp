<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section>

		<h2 id="eventHeader">${selectedEvent.name}</h2>


<div class = "form-group">
				<p>Please rank your top 3 employers, 1 being most desired and 3 being 3rd most desired. Zero denotes no preference.
				<c:url value="/submitEventPreferences" var="submitPreferencesAction"/>
				<form method="POST" action="${submitPreferencesAction}">
					<label for="cohort">Select Employer Preferences:</label>
					<c:forEach var="employer" items="{registeredEmployers}">
					<select name="${employer.id}" id="${employer.id}" class="form-control">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="0">0</option>
					</select>
					</c:forEach>
					<input type ="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
					<input type="submit" value="Submit Preferences">
				</form>
						
					
				</div>
				
				
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
				