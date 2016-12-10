<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <html lang = "en">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employer Matchmaking</title>
<c:url value="/css/master.css" var="cssHref" />
<link rel="stylesheet" type="text/css" href="${cssHref}">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<c:url var="profile" value="/js/profile.js"/>
<script src="${profile}"></script>
<c:url value="/css/jquery-ui.min.css" var="jqueryCssHref"/>
<link rel="stylesheet" href="${jqueryCssHref}"/>
<c:url value="/js/jquery.min.js" var="jqueryHref"/>
<script type="text/javascript" src="${jqueryHref}"></script>

</head> --%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />
<c:import url="/WEB-INF/jsp/common/nav.jsp" />

<section class="profile">
	
	<h1 id="sectionHeader">Employer Profile: <c:out value="${employer.name}"/></h1>
<!-- 	<p id="instruction">(Click on bolded text to edit)</p>
 -->	<div class="row">
		<div class="col-sm-3">
			<p class="profileLabel text-right" id="smallCompanyName"> Company Name:</p>
		</div>
		<div class="col-sm-9">
			<p><c:out value="${employer.name}"/></p>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-3 text-right">
			<p class="profileLabel" id="companyDescription">Company Description:</p>
		</div>
		
		<div class="col-sm-9">
			<c:url var="formAction" value="/viewProfile"/>
			<form id="form" method="POST" action=<%-- "${formAction}" --%>>
				<input type="hidden" id="id" name="id" value="${employer.id}"/>
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
				<input type="hidden" id="defaultText" value="No company description set. Click here to edit."/>
				<label class="pull-left"><c:out value="${employer.description}"/></label>
				<input id="description" name="description" class="clickedit" type="text" size=500 />
				<div class="clearfix"></div>
	<!-- 			<input type="submit" value="Click a field to edit"/>-->
			</form>
		</div>
	</div>
</section>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />



<!--  <h4>Editable labels (below)</h4>

<label class="pull-left">Click me and enter some text</label>
<input class="clickedit" type="text" />
<div class="clearfix"></div>

<label class="pull-left">Some other thing</label>
<input class="clickedit" type="text" />
<div class="clearfix"></div> -->