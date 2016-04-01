<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>

	<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>VLife</title>

		<!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
		<link href="${env.resourcesUrl}/vlife/css/bootstrap.min.css"
			rel="stylesheet">

		<!-- Custom CSS -->
		<link href="${env.resourcesUrl}/vlife/css/freelancer.css"
			rel="stylesheet">

		<!-- Custom Fonts -->
		<link
			href="${env.resourcesUrl}/vlife/font-awesome-4.1.0/css/font-awesome.min.css"
			rel="stylesheet" type="text/css">

		<link href="${env.resourcesUrl}/icheck/skins/square/blue.css?v=1.0.2"
			rel="stylesheet">

		<!-- Countdown -->
		<link rel="stylesheet"
			href="${env.resourcesUrl}/countdown/css/style.css">
		<link rel="stylesheet"
			href="${env.resourcesUrl}/treeview/css/bootstrap-treeview.css">	

		<script>
	var server_error_msg = '<spring:message code='servererror' />';
	var baseUrl = '${env.baseUrl}'
	var remainTime = '${remainTime}';
</script>
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
					<a class="navbar-brand" href="#page-top" id="pagetop"><spring:message
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

						<c:if test="${login}">
							<li>
								<a id="unsign" style="cursor: pointer;"><spring:message
										code="unsign" /> </a>
							</li>
						</c:if>

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
							<c:if test="${not login}">
								<a href="#contact">
							</c:if>
							<c:if test="${login}">
								<a href="#property" class="portfolio-link game-page" url="property" data-toggle="modal">
							</c:if>
							<img class="img-responsive"
									src="${env.resourcesUrl}/vlife/img/${profile}" alt=""> </a>
						</p>
						<div class="intro-text">
							<span class="name"> <c:if test="${login}">
									${account.name}&nbsp;<spring:message code="maintitle" />
									<spring:message code="${species.name}" />
								</c:if> <c:if test="${not login}">
									<spring:message code="nolife" />
								</c:if> </span>
							<hr class="star-light">
							<span class="skills"> <c:if
									test="${login}">
									<div class="timer">
										<div class="days-wrapper">
											<span class="days"></span>
											<br>
											<spring:message code="day" />
										</div>
										<div class="hours-wrapper">
											<span class="hours"></span>
											<br>
											<spring:message code="hour" />
										</div>
										<div class="minutes-wrapper">
											<span class="minutes"></span>
											<br>
											<spring:message code="minute" />
										</div>
										<div class="seconds-wrapper">
											<span class="seconds"></span>
											<br>
											<spring:message code="seconds" />
										</div>
									</div>

									<div id="reincarnateButton" class="col-xs-12"
										style="margin-top: 50px; display: none">
										<a class="btn btn-primary btn-lg portfolio-link game-page"
											url="reincarnateGuide" href="#reincarnateGuide"
											data-toggle="modal"> <spring:message code='lifecomplete' />
										</a>
									</div>
									
								</c:if> <c:if test="${not login}">
									<div class="title-pacehold"></div>
								</c:if> </span>
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
						<a href="#region" class="portfolio-link game-page" url="region"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/vlife/img/portfolio/cabin.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal2" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/vlife/img/portfolio/cake.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal3" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/vlife/img/portfolio/circus.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal4" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/vlife/img/portfolio/game.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal5" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/vlife/img/portfolio/safe.png"
								class="img-responsive" alt=""> </a>
					</div>
					<div class="col-sm-4 portfolio-item">
						<a href="#portfolioModal6" class="portfolio-link"
							data-toggle="modal">
							<div class="caption">
								<div class="caption-content">
									<i class="fa fa-search-plus fa-3x"></i>
								</div>
							</div> <img src="${env.resourcesUrl}/vlife/img/portfolio/submarine.png"
								class="img-responsive" alt=""> </a>
					</div>
				</div>
			</div>
		</section>

		<!-- Contact Section -->
		<c:if test="${not login}">
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
										<div style="margin-bottom: 10px;">
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
											<spring:message code='account' />
										</label>
										<input type="text" class="form-control"
											placeholder="<spring:message code='account' />" id="account"
											required
											data-validation-required-message="<spring:message code='pleaseinput' /><spring:message code='account' />">
										<p class="help-block text-danger"></p>
									</div>
								</div>

								<div class="row control-group for-reg">
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
											placeholder="<spring:message code='password' />"
											id="password" required
											data-validation-required-message="<spring:message code='pleaseinput' /><spring:message code='password' />">
										<p class="help-block text-danger"></p>
									</div>
								</div>

								<div class="row control-group for-reg">
									<div class="col-xs-12 controls">
										<div style="margin-top: 30px; margin-bottom: 20px;">
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
								<div id="success" style="display: none">
									<div class="alert alert-danger">
										<strong></strong>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<button type="submit" class="btn btn-primary btn-lg">
											<i class="fa fa-key"></i>
											<spring:message code='enterlife' />
										</button>
									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</section>
		</c:if>

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
		<div class="portfolio-modal modal fade" id="reincarnateGuide"
			tabindex="-1" role="dialog" aria-hidden="true">
		</div>
		<div class="portfolio-modal modal fade" id="property"
			tabindex="-1" role="dialog" aria-hidden="true">
		</div>
		<div class="portfolio-modal modal fade" id="region"
			tabindex="-1" role="dialog" aria-hidden="true">
		</div>

		<!-- jQuery Version 1.11.0 -->
		<script src="${env.resourcesUrl}/vlife/js/jquery-1.11.0.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${env.resourcesUrl}/vlife/js/bootstrap.min.js"></script>

		<!-- Plugin JavaScript -->
		<script src="${env.resourcesUrl}/vlife/js/jquery.easing.min.js"></script>
		<script src="${env.resourcesUrl}/vlife/js/classie.js"></script>
		<script src="${env.resourcesUrl}/vlife/js/cbpAnimatedHeader.js"></script>

		<!-- Contact Form JavaScript -->
		<script src="${env.resourcesUrl}/vlife/js/jqBootstrapValidation.js"></script>

		<!-- Custom Theme JavaScript -->
		<script src="${env.resourcesUrl}/vlife/js/freelancer.js"></script>

		<script>
	$(function() {
		$('input').iCheck({
			radioClass : 'iradio_square-blue'
		});
	})
</script>
		<script src="${env.resourcesUrl}/vlife/js/zhstar-module.js"></script>
		<script src="${env.resourcesUrl}/vlife/js/vlife.js"></script>
		<script src="${env.resourcesUrl}/icheck/js/icheck.js?v=1.0.2"></script>
		<script
			src="${env.resourcesUrl}/countdown/js/jquery.backstretch.min.js"></script>
		<script src="${env.resourcesUrl}/countdown/js/jquery.countdown.js"></script>
		<script src="${env.resourcesUrl}/countdown/js/scripts.js"></script>
		<script src="${env.resourcesUrl}/treeview/js/bootstrap-treeview.js"></script>

		<div style="display: none">
			<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
				language='JavaScript' charset='gb2312'></script>
		</div>

	</body>
</html>