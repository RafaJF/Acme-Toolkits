<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>	

	

		<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>

	<jstl:choose>
    	<jstl:when test="${command != 'create'}">
			<acme:input-textarea code="patron.patronage.form.label.status" path="status" readonly="true"/>
		</jstl:when>
	</jstl:choose>

	<acme:input-textarea code="patron.patronage.form.label.legal-stuff"
		path="legalStuff" />
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	
	<jstl:choose>
			<jstl:when test="${command == 'show' && newBudget.getCurrency()!=budget.getCurrency()}">
	            <acme:input-money code="inventor.patronage.form.label.budget-conversion" path="newBudget" readonly="true"/>
	    	</jstl:when>
    </jstl:choose>
    
    <jstl:choose>
    	<jstl:when test="${command != 'create'}">
			<acme:input-moment code="patron.patronage.form.label.creation-moment" path="creationMoment" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	
	<acme:input-moment code="patron.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="patron.patronage.form.label.more-info" path="moreInfo"/>
	
	 <hr>
	 
		<h2> Inventor </h2>
		
		<jstl:choose>
	
		<jstl:when test="${command == 'create'}">
			 <acme:input-select code="patron.patronage.form.label.inventor" path="inventorId">
	   			<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" />
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">

			<acme:input-textbox code="patron.patronage.label.inventor-full-name" path="inventorFullName" readonly="true"/>
			<acme:input-textbox code="patron.patronage.label.inventor-email" path="inventorEmail" readonly="true"/>
			<acme:input-textbox code="patron.patronage.label.inventor-company" path="inventorCompany" readonly="true"/>
			<acme:input-textbox code="patron.patronage.label.inventor-statement" path="inventorStatement" readonly="true"/>
		</jstl:when>
		
	</jstl:choose>
	
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command,'show, update, delete, publish') && published == false}"> 
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		
		<jstl:when test="${command=='create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>
		
	</jstl:choose>	
		
	
		
	 
</acme:form>
