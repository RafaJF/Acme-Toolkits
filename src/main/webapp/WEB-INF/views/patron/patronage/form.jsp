<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:choose>
		<jstl:when test="${published == true}">
			<acme:input-select code="patron.patronage.form.label.status" path="status">
				<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
				<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
				<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${status == 'DENIED'}"/>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>

	
	
		<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
		<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
		<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
		
	<jstl:choose>
		<jstl:when test="${published==true}">
			<acme:input-moment code="patron.patronage.form.label.creation-moment" path="creationMoment"/>
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
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${ inventor.getId() == inventId }"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:input-textbox code="patron.patronage.label.inventor-full-name" path="inventorFullName"/>
			<acme:input-textbox code="patron.patronage.label.inventor-email" path="inventorEmail"/>

		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">

			<acme:input-textbox code="patron.patronage.label.inventor-full-name" path="inventorFullName"/>
			<acme:input-textbox code="patron.patronage.label.inventor-email" path="inventorEmail"/>
			<acme:input-textbox code="patron.patronage.label.inventor-company" path="inventorCompany"/>
			<acme:input-textbox code="patron.patronage.label.inventor-statement" path="inventorStatement"/>
			<acme:input-textbox code="patron.patronage.label.inventor-info" path="inventorInfo"/>
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
