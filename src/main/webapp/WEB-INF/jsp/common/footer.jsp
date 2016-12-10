<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</body>

<section id="Footer-content">
	<div class="container" class="row">
	<div id = "footerGap">
    	</div>

		<!-- Footer Left Half -->
		<div class="col-xs-6"> 
		
		<h5 class= "miscFooterItem" id="textTitle">
		Awards
		</h5>
	 	
	 	<c:url var="websiteOfTheYear" value="/img/Award1.png"/>
				<img id="WinningAwardImg" class="img-responsive" src="${websiteOfTheYear}" alt="Award1" >
	 	<c:url var="DoorlsAward" value="/img/award2.png"/>
				<img id="finalistAwardImg" class="img-responsive" src="${DoorlsAward}" alt="Award2" >
		<c:url var="RobbieCatAward" value="/img/robbieBitmoji.png"/>
				<img id="BitmojiAwardImg" class="img-responsive" src="${RobbieCatAward}" alt="Award3" >
		
		</div>

		<!-- Footer Right Half -->
		<div class="col-xs-6">
		
			<h5 class= "aboutUs" id="textTitle">
				About Us
			</h5>
		
			<p class= "aboutUs">
				In Role is a collaborative web-based project designed to allow all participants in a multi-variable selection process to input data generate reporting tools. 
			</p>
		</div>
		
	</div>
</section>	

</html>
