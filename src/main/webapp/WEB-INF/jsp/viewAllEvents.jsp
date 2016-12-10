<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section>
	
		
		<ul>
		<c:forEach items="${eventList}" var="event">
			<div class="media">
				<div class="media-left"></div>
				<div class="media-body">
				<c:url value="/viewEvent?eventId=${event.eventId}" var="eventHref"/>
					<h5 class="media-heading" id="{event.name}"><a href="${eventHref}">${event.name}</a></h5>
				</div>
			</div>
			
		</c:forEach>
	</ul>


</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />
