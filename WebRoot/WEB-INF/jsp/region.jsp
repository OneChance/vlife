<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	$(function() {

		var defaultData = '${treeData}';

		$('#treeview5')
				.treeview(
						{
							color : "#18BC9C",
							expandIcon : 'glyphicon glyphicon-chevron-right',
							collapseIcon : 'glyphicon glyphicon-chevron-down',
							nodeIcon : 'fa fa-map-marker',
							data : defaultData,
							onNodeSelected : function(event, node) {

								if (!node.nodes) {
								
									VLIFE.game
											.regionInfo(
													node.id,
													function(res) {
														if (res.message) {
															VLIFE.game.showMsg(res.message);
														} else {
															
															var moveCostNoti = "<spring:message code='movecost' arguments='"+res.data.cost+"'/>";
															var confirmText = "<spring:message code='move'/>";
															var cancelText = "<spring:message code='cancel'/>";
															
															if(res.data.cost==0){
																moveCostNoti = "<spring:message code='currentregion'/>";
															}	
															
															var info_detail;
															
															var button = true;
															
															if('${account.region}'==node.id){
																button = false;
															}
															
															

															VLIFE.game.showMsg(moveCostNoti,"info",true,"<spring:message code='regioninfo'/>",info_detail,button,confirmText,function(){
																
															},cancelText,function(){
																alert(node)
																//nodeUnSelect(node);
															});										
														}
													});
								}
							}
						});
		
		var tree = $('#treeview5').treeview(true);
		tree.expandToNode('${account.region}');
		
		nodeUnSelect = function(node){
			//tree.unselectNode(node);
		}
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
