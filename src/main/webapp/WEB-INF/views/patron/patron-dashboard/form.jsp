<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<style>
.column {
  float: left;
  width: 50%;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
<acme:form readonly="true">
   <div>
   	  <acme:message code="patron.patron-dashboard.form.label.number-of-patronages-by-status"/>
      <canvas id="total-canvas"></canvas>
   </div>
   <br>
   <div class="row">
	   <div class="column">
	      <acme:message code="patron.patron-dashboard.form.label.average-number-of-budgets-by-currency-and-status"/>
	      <canvas id="average-canvas"></canvas>
	   </div>
	   <div class="column">
	      <acme:message code="patron.patron-dashboard.form.label.deviation-of-budgets-by-currency-and-status"/>
	      <canvas id="dev-canvas"></canvas>
	   </div>
   </div>
   <br>
   <div class="row">
	   <div class="column">
	      <acme:message code="patron.patron-dashboard.form.label.min-budget-by-currency-and-status"/>
	      <canvas id="min-canvas"></canvas>
	   </div>
	   <div class="column">
	      <acme:message code="patron.patron-dashboard.form.label.max-budget-by-currency-and-status"/>
	      <canvas id="max-canvas"></canvas>
	   </div>
   </div>
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
         'rgba(255, 99, 132, 0.2)',
         'rgba(255, 159, 64, 0.2)',
         'rgba(255, 205, 86, 0.2)'
       ],
       borderColor: [
         'rgb(255, 99, 132)',
         'rgb(255, 159, 64)',
         'rgb(255, 205, 86)'
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
    	  'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)'
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
    	  'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)'
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
    	  'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)'
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
    	  'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)'
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