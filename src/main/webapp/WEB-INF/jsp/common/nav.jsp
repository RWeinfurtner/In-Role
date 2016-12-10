<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<c:url var="homePageURL" value="home" />
				<li class="active"><a href="${homePageURL}">Home Page</a></li>
				<c:url var="viewProfileURL" value="viewProfile"/>
				<li><a href="${viewProfileURL}">View Profile</a></li>
				<c:url var="hiringNetworkURL" value="hiringNetwork"/>
				<li><a href="${hiringNetworkURL}">View Hiring Network</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:url var="logout" value="/logout"/>
				<li><a href="${logout}"><span class="glyphicon glyphicon-exit"></span>Logout</a></li>

			</ul>
		</div>
	</nav>
</section>