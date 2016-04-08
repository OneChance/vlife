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
															
															var moveCostNoti = "<spring:message code='movecost' arguments='"+res.data.vigorCost+","+res.data.satietyCost+"'/>";
															var confirmText = "<spring:message code='move'/>";
															
															if(res.data.cost==0 && '${account.region}'!='0'){
																moveCostNoti = "<spring:message code='currentregion'/>";
															}	
															
															var info_detail = '';
													
															$.each(res.data.memberIn,function(k,v){
																info_detail = info_detail + k +":"+v + "<br>";
															});		
															
															var moreInfo = false;
															
															if(info_detail){
																moreInfo = true;
															}										
															
															var button = true;
															
															if('${account.region}'==node.id){
																button = false;
															}

															VLIFE.game.showMsg(moveCostNoti,"info",moreInfo,"<spring:message code='regioninfo'/>",info_detail,button,confirmText,function(){
																VLIFE.game.regionMove(node.id,function(res){
																	if(res.message){
																		VLIFE.game.showMsg(res.message);
																		unSelectNode(node);
																	}else{
																		$("#region").load(baseUrl + "/region");
																		VLIFE.game.showMsg("<spring:message code='movesuccessto'/>"+node.text,'success');
																	}
																},function(){
																	unSelectNode(node);
																})
															},"",function(){
																unSelectNode(node);
															});								
														}
													},function(){
														unSelectNode(node);
													});
								}
							}
						});
		
		var tree = $('#treeview5').treeview(true);
		tree.expandToNode('${account.region}');
		
		unSelectNode = function(node){
			var tree = $('#treeview5').treeview(true);
			var nodeFromTree = tree.getNode(node.nodeId);
			tree.unselectNode(nodeFromTree);
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
