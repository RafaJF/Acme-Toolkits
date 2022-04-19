<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.patron-dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.number-of-patronages-by-status"/>
		</th>
		<td>
			<acme:print value="${numberOfPatronagesByStatus}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.patron-dashboard.form.label.average-number-of-budgets-by-currency-and-status"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfBudgetsByCurrencyAndStatus}"/>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<th scope="row"> -->
<%-- 			<acme:message code="patron.patron-dashboard.form.label.deviation-of-budgets-by-currency-and-status"/> --%>
<!-- 		</th> -->
<!-- 		<td> -->
<%-- 			<acme:print value="${deviationOfBudgetsByCurrencyAndStatus}"/> --%>
<!-- 		</td> -->
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<th scope="row"> -->
<%-- 			<acme:message code="patron.patron-dashboard.form.label.min-budget-by-currency-and-status"/> --%>
<!-- 		</th> -->
<!-- 		<td> -->
<%-- 			<acme:print value="${minBudgetByCurrencyAndStatus}"/> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<th scope="row"> -->
<%-- 			<acme:message code="patron.patron-dashboard.form.label.max-budget-by-currency-and-status"/> --%>
<!-- 		</th> -->
<!-- 		<td> -->
<%-- 			<acme:print value="${maxBudgetByCurrencyAndStatus}"/> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
</table>

<h2>
	<acme:message code="patron.patron-dashboard.form.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PENDING", "ACCEPTED", "REJECTED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${ratioOfPendingApplications}"/>, 
						<jstl:out value="${ratioOfAcceptedApplications}"/>, 
						<jstl:out value="${ratioOfRejectedApplications}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:return/>

