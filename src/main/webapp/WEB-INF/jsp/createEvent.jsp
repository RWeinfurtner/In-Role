<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />
	<h4 id="messages">${message}</h4>

	<h1>Create New Event</h1>
	
	<form method="POST" action="">
	<input type = "hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
		<input type = "hidden" name="days" value="1"/>
	
		<div>
			<input type="text" name="eventName" id="eventName" placeholder="Please Enter Event Name" class="form-control"/>
		</div>
<!-- 		<div> -->
<!-- 			<input type="radio" name="eventDayMonday" id="eventDayMonday" value="Monday" class="form-control"> Monday -->
<!-- 			<input type="radio" name="eventDayTuesday" id="eventDayTuesday" value="Tuesday" class="form-control"> Tuesday -->
<!-- 			<input type="radio" name="eventDayWednesday" id="eventDayWednesday" value="Wednesday" class="form-control"> Wednesday -->
<!-- 			<input type="radio" name="eventDayThursday" id="eventDayThursday" value="Thursday" class="form-control"> Thursday -->
<!-- 			<input type="radio" name="eventDayFriday" id="eventDayFriday" value="Friday" class="form-control"> Friday -->
<!-- 		</div> -->
		<div class="form-group">
			<input type=text name="eventDays" id="day" placeholder="Please Select A Day" class="form-control"/>
		</div>
		<div>
			<input type="text" name="startTime" id="startTime" placeholder="Please Enter Start Time" class="form-control"/>
			<input type="text" name="endTime" id="endTime" placeholder="Please Enter End Time" class="form-control"/>
		</div>
			<input type="checkbox" name="breakEntry" id="break" value="true" checked class="form-control">Will there be a break during your event?
			
		
		<div id="breakEntry">
			<input type="text" name="breakStart" id="breakStart" placeholder="Please Enter Start Time" class="form-control"/>
			<input type="text" name="breakEnd" id="breakEnd" placeholder="Please Enter End Time" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="length">Select Interview Length</label>
			<select name="interviewLength" id="length" class="form-control">
				<option value="10">10 Minutes</option>
				<option value="15">15 Minutes</option>
				<option value="20">20 Minutes</option>
				<option value="25">25 Minutes</option>
				<option value="30">30 Minutes</option>
			</select>
		</div>
		<div>
			<input type="submit" value="Create Event"/>
		</div>
	</form>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />
<c:url var="timePickerHref" value="/js/jquery.timepicker.min.js"/>
<script src="${timePickerHref}"></script>
<c:url var="dateTimePickerActivatorHref" value="/js/datePicker.js"/>
<script src="${dateTimePickerActivatorHref}"></script>