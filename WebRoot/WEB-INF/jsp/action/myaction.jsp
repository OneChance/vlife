<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

	$(function() {
	
		$(".time-action").find(".fa-remove").find("timeshow").zhcountdown(function(event) {      
	        switch(event.type) {
	        	case "show":
	        		$(this).html(event.value);
	        		break;
	            case "finished":
	            	$("#myaction_tab").click();
					break;
	        }
   		});
		
		$(".time-action").click(function(){
			var actionCode = $(this).attr("actioncode");	
			var codeMsg = "<spring message code='"+actionCode+"'/>";

			VLIFE.game.confirm(op_confirm_msg,function(){
					VLIFE.game.actionExe('${species.name}',actionCode,function(res){
					if(res.success){
						VLIFE.game.showMsg(res.message,'success');	
						$("#myaction_tab").click();
					}else{
						VLIFE.game.showMsg(res.message,'danger');
					}				
				});
			})	
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
				<spring:message code="actionearn" />
			</th>
			<th>
				<spring:message code="actioncost" />
			</th>
			<th>
				<spring:message code="operation" />
			</th>
		</tr>

		<tbody>
			<c:forEach var="action" items="${actions}">
				<tr>
					<td>
						${action.name}
					</td>
					<td>
						${action.earn}
					</td>
					<td>
						${action.cost}
					</td>
					<td>
						<button type="button" class="btn btn-${action.btncss} property-btn time-action" actioncode="${action.code}">
							<i class="fa fa-${action.fontfa}">
							<c:if test="${action.remainTime>0}">
							<timeshow timeleft="${action.remainTime}"></timeshow>
							</c:if>
							${action.status}
							</i>
						</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
