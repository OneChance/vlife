<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
			
<script>
	$(function() {

		var defaultData = '${treeData}';

		$('#treeview5').treeview({
			color : "#18BC9C",
			expandIcon : 'glyphicon glyphicon-chevron-right',
			collapseIcon : 'glyphicon glyphicon-chevron-down',
			nodeIcon : 'fa fa-map-marker',
			data : defaultData,
			onNodeSelected: function(event, node) {
			  if(!node.nodes){
			  	alert(node.able)
			  }
            }
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
						<spring:message code="region" />
					</h2>
					<hr class="star-primary">
					<div id="treeview5" class="" style="text-align:left;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
