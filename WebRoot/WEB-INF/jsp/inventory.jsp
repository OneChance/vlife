<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(function() {

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

					<div class="row">

						<table class="table table-striped normal-table">
							<tr>
								<th style="width: 15%">
									<spring:message code="inventory_name" />
								</th>
								<th>
									<spring:message code="inventory_type" />
								</th>
								<th>
									<spring:message code="inventory_effect" />
								</th>
							</tr>

							<tbody>
								<c:forEach var="inventory" items="${inventoryList}">
									<tr>
										<td>
											<spring:message code="${inventory.name}" />
										</td>
										<td>
											<spring:message code="${inventory.type}" />
										</td>
										<td>
											${inventory.detail}
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
