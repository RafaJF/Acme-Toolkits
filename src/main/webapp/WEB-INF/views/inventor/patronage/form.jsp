<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code" readonly="true"/>
	<acme:input-textarea code="inventor.patronage.form.label.legal-stuff" path="legalStuff" readonly="true"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'show'&& newBudget.getCurrency()!=budget.getCurrency()}">
            <acme:input-money code="inventor.patronage.form.label.budget-conversion" path="newBudget" readonly="true"/>
    	</jstl:when>
    </jstl:choose>
	
	<acme:input-moment code="inventor.patronage.form.label.creation-moment" path="creationMoment" readonly="true"/>
	<acme:input-moment code="inventor.patronage.form.label.start-date" path="startDate" readonly="true"/>
	<acme:input-moment code="inventor.patronage.form.label.end-date" path="endDate" readonly="true"/>
	<acme:input-url code="inventor.patronage.form.label.more-info" path="moreInfo" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron" path="company" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update') && status =='PROPOSED'}">
			<acme:submit code="inventor.patronage.form.button.updateStatus" action="/inventor/patronage/update-status"/>
			
		</jstl:when>
		
		</jstl:choose>
			<acme:button code="inventor.patronage.form.button.patronageReport" action="/inventor/patronage-report/list-patronage?patronageId=${patronageId}"/>
		

			
		
</acme:form>