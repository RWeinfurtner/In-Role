<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<section id="main-content">
	<div class="container" class="row">
	<div class="col-xs-12">

	<!--
		Different Size Devices still need to be opened and closed according to need.
		<div class="col-sm-12">
		<div class="col-md-12">
		<div class="col-lg-12">
	  -->
	
<!-- Main Title / Logo
// Welcome -->

<!-- Logo -->

    <div class="color-box" ></div>
    

	
	 <c:url var="buildingLogo" value="/img/InRoleLogo2.png"/>
				<img id="buildingImg" class="img-responsive" src="${buildingLogo}" alt="buildingLogo" >
	

<!-- Username/Email box -->

		
		<c:url var="formAction" value="/login" />
		<form method="POST" id ="loginForm" action="${formAction}">
			<input type = "hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
		
		<div class ="email-box">
			<div class="form-group">
				<!-- <label for= "email"> Email Address: </label> -->
				<input type= "email" id="email" name="email" placeHolder = "Enter Email Address" class="form-control" />
			</div>
		</div>
		
<!--  Password box -->


			<div class= "form-group">
			<!-- 	<label for= "password"> Password: </label> -->
				<input type= "password" id="password" name="password" id="password" placeHolder = "Enter Password" class="form-control" />
			</div>

<!-- Submit/continue -->


			<button type= "submit" class="btn btn-block " id="logInButton" ><p> Log in </p> </button>
		</form>
	</div>
	
	<div id = "footerGap">
    	</div>
    	
    <!-- ***NEWSLETTER BLOCK*** -->
	
	<!-- Left half -->
		<div class="col-xs-6">
		
		<c:url var="NewsletterAction" value="/newsletter" />
		
		 <form method="POST" id ="newsletterForm" action="${NewsletterAction}"> 
			
			<input type = "hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div>
				<h5 class= "newsLetter" id="newsLetterText"> Sign up for our Newsletter to receive information about Upcoming In Role Events</h5>
			</div>
			
			<div class ="email-box">
			
			<!-- get rid of all this and do an href -->
			
				<div class="form-group">
					<input type= "email" name="newsletterEmail" id="newsLetterEmail" placeHolder = "Email Address" class="form-control" />
					<button type= "submit" class="btn btn-block " id="newsLetterButton" ><p> Sign Up </p> </button>
				</div>
			</div>
			
			 </form> 
		</div>
	
	</div>
	
	
	


</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

