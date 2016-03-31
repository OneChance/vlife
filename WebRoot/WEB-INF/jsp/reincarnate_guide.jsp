<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	$(function(){
		$("#reincarnation").click(function(){
			VLIFE.game.reincarnate(function(res){
				if(res.message){
            		VLIFE.game.showMsg(res.message);
            	}else{          		
            		location = location;
            	}
			});
		});
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
						<spring:message code="lifecomplete" />
					</h2>
					<hr class="star-primary">
					<img src="${env.resourcesUrl}/vlife/img/desc/lifecomplete.jpg"
						class="img-responsive img-centered" alt="">
					<p>
					<h3>
						<spring:message code="lifecompletedes" />
					</h3>
					</p>
					<p>
						<spring:message code="species" />
						:
						<spring:message code="${species.name}" />
					</p>
					<p>
						<spring:message code="lifetime" />
						:${species.lifetime}
						<spring:message code="day" />
					</p>
					<p>
						<spring:message code="soulget" />
						:${soulget}
					</p>

					<button type="button" class="btn btn-primary" id="reincarnation">
						<i class="fa fa-refresh"></i>
						<spring:message code="reincarnation" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
