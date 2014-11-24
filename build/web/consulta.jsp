<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Banco Central - Consulta Saldo</title>
		<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!--webfonts-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
		<!--webfonts-->
		<link href="css/theme.css" rel='stylesheet' type='text/css' />
		<script type="text/javascript" src="js/jquery.min.js"></script>		
        <!--start slider -->
	    <link rel="stylesheet" href="css/fwslider.css" media="all">
		<script src="js/jquery-ui.min.js"></script>
		<script src="js/css3-mediaqueries.js"></script>
		<script src="js/fwslider.js"></script>
	<!--end slider -->
	       <!--  jquery plguin -->
		<script src="js/login.js"></script>		
		<script src="js/modernizr.custom.js"></script>
		

	    <!--scroll-->
 		<script type="text/javascript">
			$(document).ready(function() {
			
				var defaults = {
		  			containerID: 'toTop', // fading element id
					containerHoverID: 'toTopHover', // fading element hover id
					scrollSpeed: 1200,
					easingType: 'linear' 
		 		};
				
				
				$().UItoTop({ easingType: 'easeOutQuart' });
				
			});
		</script>
		<!--//scroll-->
	<!-- Add fancyBox light-box -->
	<link rel="stylesheet" type="text/css" href="css/magnific-popup1.css">
		<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
				<script>
					$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
					});
				});
		</script>
                <%
              ArrayList<String> lista = (ArrayList<String>)request.getAttribute("info");
               String personales=lista.get(4)+" "+lista.get(5)+" "+lista.get(6);
               String tipocuenta="Cuenta Corriente";
               if(lista.get(4).equals("1"))
                   tipocuenta="Cuenta de ahorros";
               String cuenta="Cuenta no:"+lista.get(1)+", Saldo$"+lista.get(2)+","+tipocuenta;%>
		<!-- //End fancyBox light-box -->	
	</head>
<body>
              
<!--features-->
<div class="container">
		<div class="content-feature-grids" id="features">
						<h3> CONSULTA SALDO</h3>
						<p>Banco Central</p>
				<div class="col-md-6">
					<div class="feature-grid">
						<div class="feature-grid-left icon2">
							<span> </span>
						</div>
						<div class="feature-grid-right">
							<h2>Datos Personales</h2>
                                                        <p> <% out.println(personales.toUpperCase());%> </p>
						</div>
						<div class="clearfix"></div>		
					</div>
				</div>
				<div class="col-md-6">
					<div class="feature-grid">
						<div class="feature-grid-left icon6">
							<span> </span>
						</div>
						<div class="feature-grid-right">
							<h2>Datos Cuenta</h2>
							<p> <% out.println(cuenta.toUpperCase());%> </p>
						</div>
						<div class="clearfix"></div>		
					</div>
				</div>
					<div class="clearfix"></div>		
				</div>
									<input type="button" onclick=" location.href='transacciones.html' " value="MENU INICIAL" name="boton" /> 
									<input type="button" onclick=" location.href='CerrarSesion' " value="SALIR" name="boton" /> 
</div>
<!--start-pricing-->


		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
		<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
			});
		});
	</script>

		 <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
</body>
</html>

