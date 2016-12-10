<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang = "en">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Employer Matchmaking</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<c:url value="/js/jquery-ui.min.js" var ="jQueryUiHref"/>
<script src="${jQueryUiHref}"></script>
<c:url value="/css/master.css" var="cssHref" />
<link rel="stylesheet" type="text/css" href="${cssHref}">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<c:url value="/css/jquery-ui.min.css" var="jqueryCssHref"/>
<c:url value="/css/jquery.timepicker.css" var="timePickerCssHref"/>
<link rel="stylesheet" href="${jqueryCssHref}"/>
<link rel="stylesheet" href="${timePickerCssHref}"/>
<!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
<c:url var="bootstrap" value="/js/bootstrap.min.js"/>
<c:url var="validate" value="/js/jquery.validate.min.js"/>
<c:url var="additionalMethods" value="/js/additional-methods.min.js"/>
<c:url var="profile" value="/js/profile.js"/>
<script src="${additionalMethods}"></script>
<script src="${bootstrap}"></script>
<script src="${validate}"></script>
<script src="${profile}"></script>

</head>

<body>
	