<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<style>
.column {
	float: left;
	width: 25%;
}

.column-50 {
	float: right;
	width: 50%;
}

.row:after {
	content: "";
	display: table;
	clear: both;
}
</style>
<acme:form readonly="true">
	<div class="row">

		<div class="column-50">
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.number-of-patronages-by-status" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
				<jstl:forEach items="${ numberOfPatronagesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ numberOfPatronagesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="patron.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
			</table>
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.average-number-of-budgets-by-currency-and-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
				<jstl:forEach
					items="${ averageNumberOfBudgetsByCurrencyAndStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ averageNumberOfBudgetsByCurrencyAndStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="patron.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
			</table>
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.deviation-of-budgets-by-currency-and-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
				<jstl:forEach
					items="${ deviationOfBudgetsByCurrencyAndStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ deviationOfBudgetsByCurrencyAndStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="patron.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
			</table>

			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.min-budget-by-currency-and-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
				<jstl:forEach items="${ minBudgetByCurrencyAndStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minBudgetByCurrencyAndStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="patron.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
			</table>

			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.max-budget-by-currency-and-status" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
				<jstl:forEach items="${ maxBudgetByCurrencyAndStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maxBudgetByCurrencyAndStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="patron.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>

			</table>
			<br> <br>
		</div>

		<div class="column-50">
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.number-of-patronages-by-status" />
			</h3>
			<br>
			<canvas id="total-canvas"></canvas>
		</div>


	</div>
	<br>
	<div class="row">
		<div class="column">
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.average-number-of-budgets-by-currency-and-status" />
			</h3>
			<br>
			<canvas id="average-canvas"></canvas>
		</div>
		<div class="column">
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.deviation-of-budgets-by-currency-and-status" />
			</h3>
			<br>
			<canvas id="dev-canvas"></canvas>
		</div>
		<div class="column">
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.min-budget-by-currency-and-status" />
			</h3>
			<br>
			<canvas id="min-canvas"></canvas>
		</div>
		<div class="column">
			<h3>
				<acme:message
					code="patron.patron-dashboard.form.label.max-budget-by-currency-and-status" />
			</h3>
			<br>
			<canvas id="max-canvas"></canvas>
		</div>
	</div>
	<br>
</acme:form>

<script type="text/javascript">
   var numberOfPatronagesByStatus = {
   	<jstl:forEach items="${numberOfPatronagesByStatus}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const total_data = {
     labels: Object.keys(numberOfPatronagesByStatus),
     datasets: [{
       data: Object.values(numberOfPatronagesByStatus),
       backgroundColor: [
    	   'rgb(254, 136, 127)',
     	  'rgb(136, 255, 114)',
     	  'rgb(71, 130, 255)'
       ],
       borderColor: [
    	   'rgb(193, 136, 127)',
     	  'rgb(136, 176, 114)',
     	  'rgb(71, 130, 159)'
       ],
       borderWidth: 1
     }]
   };
   
   var options = {
   	    legend : { display : false },
   	    scales: {
   	        y: {
   	         suggestedMin: 0,
   	         display: true
   	        }
   	      }
   	};
   
   var canvas = document.getElementById("total-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : total_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var averageNumberOfBudgetsByCurrencyAndStatus = {
   	   	<jstl:forEach items="${averageNumberOfBudgetsByCurrencyAndStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const average_data = {
    labels: Object.keys(averageNumberOfBudgetsByCurrencyAndStatus),
    datasets: [{
      data: Object.values(averageNumberOfBudgetsByCurrencyAndStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
          
        ]
    }]
   };
   
   var canvas = document.getElementById("average-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : average_data,
   	options: options
   });
</script>

<script type="text/javascript">

   var deviationOfBudgetsByCurrencyAndStatus = {
   	   	<jstl:forEach items="${deviationOfBudgetsByCurrencyAndStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const dev_data = {
    labels: Object.keys(deviationOfBudgetsByCurrencyAndStatus),
    datasets: [{
      data: Object.values(deviationOfBudgetsByCurrencyAndStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("dev-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : dev_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var minBudgetByCurrencyAndStatus = {
   	   	<jstl:forEach items="${minBudgetByCurrencyAndStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const min_data = {
    labels: Object.keys(minBudgetByCurrencyAndStatus),
    datasets: [{
      data: Object.values(minBudgetByCurrencyAndStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("min-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : min_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var maxBudgetByCurrencyAndStatus = {
   	   	<jstl:forEach items="${maxBudgetByCurrencyAndStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const max_data = {
    labels: Object.keys(maxBudgetByCurrencyAndStatus),
    datasets: [{
      data: Object.values(maxBudgetByCurrencyAndStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("max-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : max_data,
   	options: options
   });
</script>