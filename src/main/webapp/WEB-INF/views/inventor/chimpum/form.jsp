<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
	<jstl:when test="${command == 'create' }">
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
	</jstl:when>
	<jstl:otherwise>
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code" readonly="true"/>
	</jstl:otherwise>
	</jstl:choose>
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.chimpum.form.label.creation-moment" path="creationMoment" readonly="true"/>
	<acme:input-textarea code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-textbox code="inventor.chimpum.form.label.start-date" path="startDate"/>
	<acme:input-textbox code="inventor.chimpum.form.label.end-date" path="endDate"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-textbox code="inventor.chimpum.form.label.link" path="link"/>
	<acme:input-select path="itemName" code="inventor.chimpum.form.label.item.select">
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command,'show,update,delete')}">
	<acme:input-option code="${selectedName} - ${selectedType}"  value="${selectedId}"/>
	</jstl:when>
	</jstl:choose>
	
	<jstl:forEach var="item" items="${items}">
	<acme:input-option  code="${item.name} - ${item.itemType}" value="${item.id}"/>
	</jstl:forEach>
	</acme:input-select>
		


	<jstl:choose>
	<jstl:when test="${command == 'create' }">
	<acme:submit code="inventor.chimpum.form.create" action="/inventor/chimpum/create/"/> 
	</jstl:when>
	<jstl:when test="${acme:anyOf(command,'show,update,delete,publish')}">
	<acme:submit code="inventor.chimpum.form.delete" action="/inventor/chimpum/delete/"/> 
	<acme:submit code="inventor.chimpum.form.update" action="/inventor/chimpum/update/"/> 
	</jstl:when>
	</jstl:choose>
		
</acme:form>