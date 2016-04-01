<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(function() {

		var addPow = 0;
		var addDef = 0;
		var addDex = 0;
		var addInte = 0;
		var addHp = 0;

		var addedPow = parseInt('${account.addPow}');
		var addedDef = parseInt('${account.addDef}');
		var addedDex = parseInt('${account.addDex}');
		var addedInte = parseInt('${account.addInt}');
		var addedHp = parseInt('${account.addHp}');

		$(".property-btn").click(function() {

			var multi = $(this).attr("multi");
			var addValue = parseInt(multi);
			var propName = $(this).parents("tr").attr("prop");
			var propValueWrapper = $(this).parents("tr").find("addp");
			var func = $(this).attr("func");

			if (func == 'minus') {
				addValue = 0 - addValue;
			}

			switch (propName) {
			case ("power"):
				addPow = cal(addPow, addValue, propValueWrapper, addedPow);
				break;
			case ("def"):
				addDef = cal(addDef, addValue, propValueWrapper, addedDef);
				break;
			case ("dex"):
				addDex = cal(addDex, addValue, propValueWrapper, addedDex);
				break;
			case ("inte"):
				addInte = cal(addInte, addValue, propValueWrapper, addedInte);
				break;
			case ("hp"):
				addHp = cal(addHp, addValue, propValueWrapper, addedHp);
				break;
			}

		});

		function cal(addO, addValue, wrapper, baseValue) {
			var sumSoul = parseInt($("soul").html());
			var addN = Math.max(addO + addValue, 0 - baseValue);
			var changeValue = addN - addO;
			wrapper.html('+' + (addN + baseValue));
			$("soul").html(sumSoul - changeValue);
			return addN;
		}

		$("#changeproperty").click(function() {
			VLIFE.game.changeProp({
				addPow : addPow,
				addDef : addDef,
				addDex : addDex,
				addInt : addInte,
				addHp : addHp
			}, addCallback);
		});

		function addCallback(res) {
			if (res.message) {
				VLIFE.game.showMsg(res.message);
			} else {
				VLIFE.game.showMsg("<spring:message code='propertychanged'/>");
				$("#property").load(baseUrl + "/property");
			}
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
						<spring:message code="property" />
					</h2>
					<hr class="star-primary">
					<p>
					<h3>
						<spring:message code="soul" />
						:
						<soul>
						${account.soul}
						</soul>
					</h3>
					</p>
					<p>
						<spring:message code="species" />
						:
						<spring:message code="${species.name}" />
					</p>
					<p>
						<spring:message code="level" />
						:${account.level}
					</p>
					<p>
						<spring:message code="exp" />
						:${account.exp}
					</p>

					<table class="table table-striped property-table">
						<tbody>
							<tr prop="power">
								<td>
									<spring:message code="power" />
								</td>
								<td>
									${species.basePow}(
									<addp>
									+${account.addPow}
									</addp>
									)
								</td>
								<td>
									<jsp:include page="property_btn.jsp" />
								</td>
							</tr>
							<tr prop="def">
								<td>
									<spring:message code="def" />
								</td>
								<td>
									${species.baseDef}(
									<addp>
									+${account.addDef}
									</addp>
									)
								</td>
								<td>
									<jsp:include page="property_btn.jsp" />
								</td>
							</tr>
							<tr prop="dex">
								<td>
									<spring:message code="dex" />
								</td>
								<td>
									${species.baseDex}(
									<addp>
									+${account.addDex}
									</addp>
									)
								</td>
								<td>
									<jsp:include page="property_btn.jsp" />
								</td>
							</tr>
							<tr prop="inte">
								<td>
									<spring:message code="inte" />
								</td>
								<td>
									${species.baseInt}(
									<addp>
									+${account.addInt}
									</addp>
									)
								</td>
								<td>
									<jsp:include page="property_btn.jsp" />
								</td>
							</tr>
							<tr prop="hp">
								<td>
									<spring:message code="hp" />
								</td>
								<td>
									${species.baseHp}(
									<addp>
									+${account.addHp}
									</addp>
									)
								</td>
								<td>
									<jsp:include page="property_btn.jsp" />
								</td>
							</tr>
						</tbody>
					</table>


					<c:if test="${remainTime > (24 * 60 * 60 * 1000)}">
						<button type="button" class="btn btn-primary" id="changeproperty">
							<i class="fa fa-edit"></i>
							<spring:message code="changeproperty" />
						</button>
					</c:if>
					<c:if test="${remainTime < (24 * 60 * 60 * 1000)}">
						<spring:message code="cannotchangefromtime" />
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
