<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>

	<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>VLife</title>

		<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
		<link href="${env.resourcesUrl}/css/bootstrap.min.css"
			rel="stylesheet">

		<!-- Custom CSS -->
		<link href="${env.resourcesUrl}/css/freelancer.css" rel="stylesheet">

		<!-- Custom Fonts -->
		<link
			href="${env.resourcesUrl}/font-awesome-4.1.0/css/font-awesome.min.css"
			rel="stylesheet" type="text/css">

		<link href="${env.resourcesUrl}/icheck/skins/square/blue.css?v=1.0.2"
			rel="stylesheet">

	</head>

	<body id="page-top" class="index">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header page-scroll">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#page-top"><spring:message
							code="brand" /> </a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li class="hidden">
							<a href="#page-top"></a>
						</li>
						<li class="page-scroll">
							<a href="#portfolio"><spring:message code="info" /> </a>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>

		<!-- Header -->
		<header>
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<p class="page-scroll">
							<a href="#contact"><img class="img-responsive"
									src="${env.resourcesUrl}/img/profile.png" alt=""> </a>
						</p>
						<div class="intro-text">
							<span class="name"> <c:if test="${not empty loginAccount}">
								</c:if> <c:if test="${empty loginAccount}">
									<spring:message code="nolife" />
								</c:if> </span>
							<hr class="star-light">
							<span class="skills">Web Developer - Graphic Artist - User
								Experience Designer</span>
						</div>
					</div>
				</div>
			</div>
		</header>

		<!-- Portfolio Grid Section -->
		<section id="portfolio">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-center">
						<h2>
							<spring:message code="info" />
						</h2>
						<hr class="star-primary">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal1" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/img/portfolio/cabin.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal2" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/img/portfolio/cake.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal3" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/img/portfolio/circus.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal4" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/img/portfolio/game.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal5" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/img/portfolio/safe.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal6" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/img/portfolio/submarine.png"
								class="img-responsive" alt=""> </a>
					</div>
				</div>
			</div>
		</section>

		<!-- Contact Section -->
		<section id="contact">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-center">
						<h2>
							<spring:message code="newlife" />
						</h2>
						<hr class="star-primary">
					</div>
				</div>
				<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						<form id="accountForm" novalidate>

							<div class="row control-group">
								<div class="col-xs-12 controls">
									<div style="margin-bottom:10px;">
										<input type="radio" id="login" name="entertype" required
											value="login"
											data-validation-required-message="<spring:message code='pleasechoose' /><spring:message code='retype' />">
										<label for="login" class="radio-label">
											<spring:message code='lifegoon' />
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" id="reg" name="entertype" value="reg">
										<label for="reg" class="radio-label">
											<spring:message code='anotherlife' />
										</label>
									</div>
									<p class="help-block text-danger"></p>
								</div>
							</div>


							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label>
										<spring:message code='name' />
									</label>
									<input type="text" class="form-control"
										placeholder="<spring:message code='name' />" id="name"
										required
										data-validation-required-message="<spring:message code='pleaseinput' /><spring:message code='name' />">
									<p class="help-block text-danger"></p>
								</div>
							</div>

							<div class="row control-group">
								<div
									class="form-group col-xs-12 floating-label-form-group controls">
									<label>
										<spring:message code='password' />
									</label>
									<input type="password" class="form-control"
										placeholder="<spring:message code='password' />" id="password"
										required
										data-validation-required-message="<spring:message code='pleaseinput' /><spring:message code='password' />">
									<p class="help-block text-danger"></p>
								</div>
							</div>

							<div class="row control-group for-reg">
								<div class="col-xs-12 controls">
									<div style="margin-top:30px;margin-bottom:20px;">
										<input type="radio" id="sexm" name="sex" required value="m"
											data-validation-required-message="<spring:message code='pleasechoose' /><spring:message code='sex' />">
										<label for="sexm" class="radio-label">
											<spring:message code='sexm' />
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" id="sexf" name="sex" value="f">
										<label for="sexf" class="radio-label">
											<spring:message code='sexf' />
										</label>
									</div>
									<p class="help-block text-danger"></p>
								</div>
							</div>

							<br>
							<div id="success"></div>
							<div class="row">
								<div class="form-group col-xs-12">
									<button type="submit" class="btn btn-success btn-lg">
										<spring:message code='enterlife' />
									</button>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
		</section>

		<!-- Footer -->
		<footer class="text-center">

			<div class="footer-below">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							Copyright &copy; 2014.Company name All rights reserved.
						</div>
					</div>
				</div>
			</div>
		</footer>

		<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
		<div class="scroll-top page-scroll visible-xs visble-sm">
			<a class="btn btn-primary" href="#page-top"> <i
				class="fa fa-chevron-up"></i> </a>
		</div>

		<!-- Portfolio Modals -->
		<div class="portfolio-modal modal fade" id="portfolioModal1"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="modal-body">
								<h2>
									Project Title
								</h2>
								<hr class="star-primary">
								<img src="${env.resourcesUrl}/img/portfolio/cabin.png"
									class="img-responsive img-centered" alt="">
								<p>
									Use this area of the page to describe your project. The icon
									above is part of a free icon set by Flat Icons. On their
									website, you can download their free set with 16 icons, or you
									can purchase the entire set with 146 icons for only $12!
								</p>
								<ul class="list-inline item-details">
									<li>
										Client:
										<strong><a href="http://sc.chinaz.com">Start
												Bootstrap</a> </strong>
									</li>
									<li>
										Date:
										<strong><a href="http://sc.chinaz.com">April 2014</a>
										</strong>
									</li>
									<li>
										Service:
										<strong><a href="http://sc.chinaz.com">Web
												Development</a> </strong>
									</li>
								</ul>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="portfolio-modal modal fade" id="portfolioModal2"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="modal-body">
								<h2>
									Project Title
								</h2>
								<hr class="star-primary">
								<img src="${env.resourcesUrl}/img/portfolio/cake.png"
									class="img-responsive img-centered" alt="">
								<p>
									Use this area of the page to describe your project. The icon
									above is part of a free icon set by Flat Icons. On their
									website, you can download their free set with 16 icons, or you
									can purchase the entire set with 146 icons for only $12!
								</p>
								<ul class="list-inline item-details">
									<li>
										Client:
										<strong><a href="http://sc.chinaz.com">Start
												Bootstrap</a> </strong>
									</li>
									<li>
										Date:
										<strong><a href="http://sc.chinaz.com">April 2014</a>
										</strong>
									</li>
									<li>
										Service:
										<strong><a href="http://sc.chinaz.com">Web
												Development</a> </strong>
									</li>
								</ul>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="portfolio-modal modal fade" id="portfolioModal3"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="modal-body">
								<h2>
									Project Title
								</h2>
								<hr class="star-primary">
								<img src="${env.resourcesUrl}/img/portfolio/circus.png"
									class="img-responsive img-centered" alt="">
								<p>
									Use this area of the page to describe your project. The icon
									above is part of a free icon set by Flat Icons. On their
									website, you can download their free set with 16 icons, or you
									can purchase the entire set with 146 icons for only $12!
								</p>
								<ul class="list-inline item-details">
									<li>
										Client:
										<strong><a href="http://sc.chinaz.com">Start
												Bootstrap</a> </strong>
									</li>
									<li>
										Date:
										<strong><a href="http://sc.chinaz.com">April 2014</a>
										</strong>
									</li>
									<li>
										Service:
										<strong><a href="http://sc.chinaz.com">Web
												Development</a> </strong>
									</li>
								</ul>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="portfolio-modal modal fade" id="portfolioModal4"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="modal-body">
								<h2>
									Project Title
								</h2>
								<hr class="star-primary">
								<img src="${env.resourcesUrl}/img/portfolio/game.png"
									class="img-responsive img-centered" alt="">
								<p>
									Use this area of the page to describe your project. The icon
									above is part of a free icon set by Flat Icons. On their
									website, you can download their free set with 16 icons, or you
									can purchase the entire set with 146 icons for only $12!
								</p>
								<ul class="list-inline item-details">
									<li>
										Client:
										<strong><a href="http://sc.chinaz.com">Start
												Bootstrap</a> </strong>
									</li>
									<li>
										Date:
										<strong><a href="http://sc.chinaz.com">April 2014</a>
										</strong>
									</li>
									<li>
										Service:
										<strong><a href="http://sc.chinaz.com">Web
												Development</a> </strong>
									</li>
								</ul>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="portfolio-modal modal fade" id="portfolioModal5"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="modal-body">
								<h2>
									Project Title
								</h2>
								<hr class="star-primary">
								<img src="${env.resourcesUrl}/img/portfolio/safe.png"
									class="img-responsive img-centered" alt="">
								<p>
									Use this area of the page to describe your project. The icon
									above is part of a free icon set by Flat Icons. On their
									website, you can download their free set with 16 icons, or you
									can purchase the entire set with 146 icons for only $12!
								</p>
								<ul class="list-inline item-details">
									<li>
										Client:
										<strong><a href="http://sc.chinaz.com">Start
												Bootstrap</a> </strong>
									</li>
									<li>
										Date:
										<strong><a href="http://sc.chinaz.com">April 2014</a>
										</strong>
									</li>
									<li>
										Service:
										<strong><a href="http://sc.chinaz.com">Web
												Development</a> </strong>
									</li>
								</ul>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="portfolio-modal modal fade" id="portfolioModal6"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-content">
				<div class="close-modal" data-dismiss="modal">
					<div class="lr">
						<div class="rl"></div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2">
							<div class="modal-body">
								<h2>
									Project Title
								</h2>
								<hr class="star-primary">
								<img src="${env.resourcesUrl}/img/portfolio/submarine.png"
									class="img-responsive img-centered" alt="">
								<p>
									Use this area of the page to describe your project. The icon
									above is part of a free icon set by Flat Icons. On their
									website, you can download their free set with 16 icons, or you
									can purchase the entire set with 146 icons for only $12!
								</p>
								<ul class="list-inline item-details">
									<li>
										Client:
										<strong><a href="http://sc.chinaz.com">Start
												Bootstrap</a> </strong>
									</li>
									<li>
										Date:
										<strong><a href="http://sc.chinaz.com">April 2014</a>
										</strong>
									</li>
									<li>
										Service:
										<strong><a href="http://sc.chinaz.com">Web
												Development</a> </strong>
									</li>
								</ul>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									<i class="fa fa-times"></i> Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- jQuery Version 1.11.0 -->
		<script src="${env.resourcesUrl}/js/jquery-1.11.0.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${env.resourcesUrl}/js/bootstrap.min.js"></script>

		<!-- Plugin JavaScript -->
		<script src="${env.resourcesUrl}/js/jquery.easing.min.js"></script>
		<script src="${env.resourcesUrl}/js/classie.js"></script>
		<script src="${env.resourcesUrl}/js/cbpAnimatedHeader.js"></script>

		<!-- Contact Form JavaScript -->
		<script src="${env.resourcesUrl}/js/jqBootstrapValidation.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="${env.resourcesUrl}/js/freelancer.js"></script>

		<script src="${env.resourcesUrl}/js/vlife.js"></script>

		<script src="${env.resourcesUrl}/icheck/js/icheck.js?v=1.0.2"></script>

		<script>
	$(function() {
		$('input').iCheck({
			radioClass : 'iradio_square-blue'
		});
	})
</script>

		<div style="display: none">
			<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
				language='JavaScript' charset='gb2312'></script>
		</div>

	</body>
</html>