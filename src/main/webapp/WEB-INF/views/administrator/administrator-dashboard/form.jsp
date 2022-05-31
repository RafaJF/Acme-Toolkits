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

<h3>
	<acme:message code="administrator.administrator-dashboard.form.label.ratio-items-with-chimpum"/>
</h3>
<table>
	<caption></caption>
	<tr>
		<th>
			<acme:print value="${ratioOfItemsWithChimpum}"/>
		</th>
	</tr>
</table>

<h3>
	<acme:message
		code="administrator.administrator-dashboard.form.label.average-budget-chimpums-by-currency" />
</h3>
<table class="table table-sm">
	<caption></caption>
	<div class="column">
		<jstl:forEach items="${ averageBudgetOfChimpumsByCurrency.keySet() }" var="key">
			<tr>
				<jstl:set value="${ averageBudgetOfChimpumsByCurrency.get(key) }" var="amount" />
					<jstl:if test="${ amount>0 }">
						<th scope="row" style="width: 15%"><acme:message
							code="administrator.dashboard.form.status.${ key }" /></th>
						<td><acme:print value="${ amount }" /></td>
					</jstl:if>
			</tr>
		</jstl:forEach>
	</div>
</table>

<h3>
	<acme:message
		code="administrator.administrator-dashboard.form.label.deviation-budget-chimpums-by-currency" />
</h3>
<table class="table table-sm">
	<caption></caption>
	<div class="column">
		<jstl:forEach items="${ deviationBudgetOfChimpumsByCurrency.keySet() }" var="key">
			<tr>
				<jstl:set value="${ deviationBudgetOfChimpumsByCurrency.get(key) }" var="amount" />
					<jstl:if test="${ amount>0 }">
						<th scope="row" style="width: 15%"><acme:message
							code="administrator.dashboard.form.status.${ key }" /></th>
						<td><acme:print value="${ amount }" /></td>
					</jstl:if>
			</tr>
		</jstl:forEach>
	</div>
</table>

<h3>
	<acme:message
		code="administrator.administrator-dashboard.form.label.minimum-budget-chimpums-by-currency" />
</h3>
<table class="table table-sm">
	<caption></caption>
	<div class="column">
		<jstl:forEach items="${ minimumBudgetOfChimpumsByCurrency.keySet() }" var="key">
			<tr>
				<jstl:set value="${ minimumBudgetOfChimpumsByCurrency.get(key) }" var="amount" />
					<jstl:if test="${ amount>0 }">
						<th scope="row" style="width: 15%"><acme:message
							code="administrator.dashboard.form.status.${ key }" /></th>
						<td><acme:print value="${ amount }" /></td>
					</jstl:if>
			</tr>
		</jstl:forEach>
	</div>
</table>

<h3>
	<acme:message
		code="administrator.administrator-dashboard.form.label.maximum-budget-chimpums-by-currency" />
</h3>
<table class="table table-sm">
	<caption></caption>
	<div class="column">
		<jstl:forEach items="${ maximumBudgetOfChimpumsByCurrency.keySet() }" var="key">
			<tr>
				<jstl:set value="${ maximumBudgetOfChimpumsByCurrency.get(key) }" var="amount" />
					<jstl:if test="${ amount>0 }">
						<th scope="row" style="width: 15%"><acme:message
							code="administrator.dashboard.form.status.${ key }" /></th>
						<td><acme:print value="${ amount }" /></td>
					</jstl:if>
			</tr>
		</jstl:forEach>
	</div>
</table>











			<h3>
				<acme:message code="administrator.administrator-dashboard.form.label.total-number-of-components"/>
			</h3>
			<table>
			<caption></caption>
				<tr>
				<th id="totalNumberOfComponentsId">
					<acme:print value="${totalNumberOfComponents}"/>
				</th>
				</tr>
			</table>
			<h3>
				<acme:message code="administrator.administrator-dashboard.form.label.total-number-of-tools"/>
			</h3>
			<table>
				<caption></caption>
				<tr>
				<th id="totalNumberOfToolsId">
					<acme:print value="${totalNumberOfTools}"/>
				</th>
				</tr>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.total-number-of-patronages-by-status" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ totalNumberOfPatronagesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ totalNumberOfPatronagesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="total-canvas"></canvas>
				</div>
			</table>
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.average-retail-price-of-components-by-technology-and-currency" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ averageRetailPriceOfComponentsByTechnologyAndCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ averageRetailPriceOfComponentsByTechnologyAndCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="average-canvas"></canvas>
				</div>
			</table>
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.deviation-retail-price-of-components-by-technology-and-currency" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ deviationRetailPriceOfComponentsByTechnologyAndCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ deviationRetailPriceOfComponentsByTechnologyAndCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="dev-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.minimum-retail-price-of-components-by-technology-and-currency" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ minimumRetailPriceOfComponentsByTechnologyAndCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minimumRetailPriceOfComponentsByTechnologyAndCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="min-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.maximum-retail-price-of-components-by-technology-and-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ maximumRetailPriceOfComponentsByTechnologyAndCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maximumRetailPriceOfComponentsByTechnologyAndCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="max-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.average-retail-price-of-tools-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ averageRetailPriceOfToolsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ averageRetailPriceOfToolsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="avg2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.deviation-retail-price-of-tools-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
				<div class="column">
				<jstl:forEach items="${ deviationRetailPriceOfToolsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ deviationRetailPriceOfToolsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="dev2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.minimum-retail-price-of-tools-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ minimumRetailPriceOfToolsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minimumRetailPriceOfToolsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="min2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.maximum-retail-price-of-tools-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ maximumRetailPriceOfToolsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maximumRetailPriceOfToolsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="max2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.average-budget-patronages-by-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ averageBudgetPatronagesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ averageBudgetPatronagesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" />
							</th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="avg3-canvas"></canvas>
				</div>
			</table>
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.deviation-budget-patronages-by-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ deviationBudgetPatronagesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ deviationBudgetPatronagesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="dev3-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.minimum-budget-patronages-by-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ minimumBudgetPatronagesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minimumBudgetPatronagesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="min3-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.maximum-budget-patronages-by-status" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ maximumBudgetPatronagesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maximumBudgetPatronagesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="max3-canvas"></canvas>
				</div>
			</table>
			<br> <br>
		</div>
</acme:form>

<script type="text/javascript">
   var totalNumberOfPatronagesByStatus = {
   	<jstl:forEach items="${totalNumberOfPatronagesByStatus}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const total_data = {
     labels: Object.keys(totalNumberOfPatronagesByStatus),
     datasets: [{
       data: Object.values(totalNumberOfPatronagesByStatus),
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
   var averageRetailPriceOfComponentsByTechnologyAndCurrency = {
   	   	<jstl:forEach items="${averageRetailPriceOfComponentsByTechnologyAndCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const average_data = {
    labels: Object.keys(averageRetailPriceOfComponentsByTechnologyAndCurrency),
    datasets: [{
      data: Object.values(averageRetailPriceOfComponentsByTechnologyAndCurrency),
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
   var deviationRetailPriceOfComponentsByTechnologyAndCurrency = {
   	   	<jstl:forEach items="${deviationRetailPriceOfComponentsByTechnologyAndCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const dev_data = {
    labels: Object.keys(deviationRetailPriceOfComponentsByTechnologyAndCurrency),
    datasets: [{
      data: Object.values(deviationRetailPriceOfComponentsByTechnologyAndCurrency),
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
   var minimumRetailPriceOfComponentsByTechnologyAndCurrency = {
   	   	<jstl:forEach items="${minimumRetailPriceOfComponentsByTechnologyAndCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const min_data = {
    labels: Object.keys(minimumRetailPriceOfComponentsByTechnologyAndCurrency),
    datasets: [{
      data: Object.values(minimumRetailPriceOfComponentsByTechnologyAndCurrency),
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
   var maximumRetailPriceOfComponentsByTechnologyAndCurrency = {
   	   	<jstl:forEach items="${maximumRetailPriceOfComponentsByTechnologyAndCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const max_data = {
    labels: Object.keys(maximumRetailPriceOfComponentsByTechnologyAndCurrency),
    datasets: [{
      data: Object.values(maximumRetailPriceOfComponentsByTechnologyAndCurrency),
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

<script type="text/javascript">
   var averageRetailPriceOfToolsByCurrency = {
   	<jstl:forEach items="${averageRetailPriceOfToolsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const avg2_data = {
     labels: Object.keys(averageRetailPriceOfToolsByCurrency),
     datasets: [{
       data: Object.values(averageRetailPriceOfToolsByCurrency),
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
   
   var canvas = document.getElementById("avg2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : avg2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var deviationRetailPriceOfToolsByCurrency = {
   	<jstl:forEach items="${deviationRetailPriceOfToolsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const dev2_data = {
     labels: Object.keys(deviationRetailPriceOfToolsByCurrency),
     datasets: [{
       data: Object.values(deviationRetailPriceOfToolsByCurrency),
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
   
   var canvas = document.getElementById("dev2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : dev2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var minimumRetailPriceOfToolsByCurrency = {
   	<jstl:forEach items="${minimumRetailPriceOfToolsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const min2_data = {
     labels: Object.keys(minimumRetailPriceOfToolsByCurrency),
     datasets: [{
       data: Object.values(minimumRetailPriceOfToolsByCurrency),
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
   
   var canvas = document.getElementById("min2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : min2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var maximumRetailPriceOfToolsByCurrency = {
   	<jstl:forEach items="${maximumRetailPriceOfToolsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const max2_data = {
     labels: Object.keys(maximumRetailPriceOfToolsByCurrency),
     datasets: [{
       data: Object.values(maximumRetailPriceOfToolsByCurrency),
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
   
   var canvas = document.getElementById("max2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : max2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var averageBudgetPatronagesByStatus = {
   	   	<jstl:forEach items="${averageBudgetPatronagesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const avg3_data = {
    labels: Object.keys(averageBudgetPatronagesByStatus),
    datasets: [{
      data: Object.values(averageBudgetPatronagesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("avg3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : avg3_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var deviationBudgetPatronagesByStatus = {
   	   	<jstl:forEach items="${deviationBudgetPatronagesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const dev3_data = {
    labels: Object.keys(deviationBudgetPatronagesByStatus),
    datasets: [{
      data: Object.values(deviationBudgetPatronagesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("dev3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : dev3_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var minimumBudgetPatronagesByStatus = {
   	   	<jstl:forEach items="${minimumBudgetPatronagesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const min3_data = {
    labels: Object.keys(minimumBudgetPatronagesByStatus),
    datasets: [{
      data: Object.values(minimumBudgetPatronagesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("min3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : min3_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var maximumBudgetPatronagesByStatus = {
   	   	<jstl:forEach items="${maximumBudgetPatronagesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const max3_data = {
    labels: Object.keys(maximumBudgetPatronagesByStatus),
    datasets: [{
      data: Object.values(maximumBudgetPatronagesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("max3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : max3_data,
   	options: options
   });
</script>