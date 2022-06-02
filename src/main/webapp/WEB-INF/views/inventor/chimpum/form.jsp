<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
	<jstl:when test="${command != 'create' }">
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code" readonly="true"/>
	</jstl:when>
	</jstl:choose>
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-moment code="inventor.chimpum.form.label.creation-moment" path="creationMoment" readonly="true"/>
	<acme:input-textarea code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-moment code="inventor.chimpum.form.label.start-date" path="startDate"/>
	<acme:input-moment code="inventor.chimpum.form.label.end-date" path="endDate"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-textbox code="inventor.chimpum.form.label.link" path="link"/>
	<acme:input-select code="inventor.chimpum.form.label.item.select" path="item">
	<jstl:choose>
	<jstl:when test="${command !='create' }">
	<acme:input-option  code="${itemSelected.name} - ${itemSelected.itemType}" value="${itemSelected.id}"/>
	</jstl:when>
	</jstl:choose>
	<jstl:forEach var="i" items="${items}">
	<acme:input-option  code="${i.name} - ${i.itemType}" value="${i.id}"/>
	</jstl:forEach>
	</acme:input-select>
		


	<jstl:choose>
	<jstl:when test="${command == 'create' }">
	<acme:submit code="inventor.chimpum.form.create" action="/inventor/chimpum/create"/> 
	</jstl:when>
	<jstl:when test="${acme:anyOf(command,'show,update,delete,publish')}">
	<acme:submit code="inventor.chimpum.form.delete" action="/inventor/chimpum/delete"/> 
	<acme:submit code="inventor.chimpum.form.update" action="/inventor/chimpum/update"/> 
	</jstl:when>
	</jstl:choose>
		
</acme:form>