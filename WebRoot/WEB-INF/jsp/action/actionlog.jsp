<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(function() {
		$(".action_remove").click(function(){
		
			var actionId = $(this).attr("actionid");
		
			VLIFE.game.confirm(op_confirm_msg,function(){
				VLIFE.game.actionDelete('${species.name}',actionId,function(res){
				
					var msgType = 'danger';

					if(res.success){
						msgType = 'success';
						$("#actionlog_tab").click();
					}
					
					VLIFE.game.showMsg(res.message,msgType);
				});
			});
		});
	});
</script>

<div class="row">

	<table class="table table-striped normal-table">
		<tr>
			<th style="width:15%">
				<spring:message code="actionname" />
			</th>
			<th>
				<spring:message code="actiondetail" />
			</th>
			<th>
				<spring:message code="operation" />
			</th>
		</tr>

		<tbody>
			<c:forEach var="action" items="${actions}">
				<tr>
					<td>
						<spring:message code="action_${action.code}" />
					</td>
					<td>
						${action.detail}
					</td>
					<td>
						<button type="button" class="btn btn-danger property-btn action_remove" actionid="${action.id}">
							<i class="fa fa-remove"></i>
							<spring:message code="action_delete" />
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>