<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	$(function() {
		$(".nav-pills").find("a").click(function() {
			$("#"+$(this).attr("id")+"_content").load('${env.baseUrl}'+$(this).attr("id").split("_")[0]);
		});
		
		$("#myaction_tab").click();
	});
</script>

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
						<spring:message code="action" />
					</h2>
					<hr class="star-primary">

					<ul class="nav nav-pills">
						<li>
							<a href="#myaction_tab_content" data-toggle="pill" id="myaction_tab"><spring:message
									code="myaction" /> </a>
						</li>
						<li>
							<a href="#actionlog_tab_content" data-toggle="pill" id="actionlog_tab"><spring:message
									code="actionlog" /> </a>
						</li>
					</ul>

					<div id="tab_content" class="tab-content">
						<div class="tab-pane fade" id="myaction_tab_content"></div>
						<div class="tab-pane fade" id="actionlog_tab_content"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
