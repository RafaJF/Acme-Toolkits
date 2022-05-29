<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="inventor.chimpum.list.label.code" path="code" readonly="true"/>
			<acme:input-textbox		code="inventor.chimpum.form.label.item.code"	path="item.code" readonly="true"/>
		
		</jstl:when>
	</jstl:choose>
	
	<acme:input-textbox code="inventor.chimpum.list.label.creationMoment" path="creationMoment" readonly="true"/>	
	<acme:input-textbox code="inventor.chimpum.list.label.tittle" path="tittle"/>
	<acme:input-textarea code="inventor.chimpum.list.label.description" path="description"/>
	<acme:input-textbox code="inventor.chimpum.list.label.period" path="period"/>
	<acme:input-textbox code="inventor.chimpum.list.label.budget" path="budget"/>
	<acme:input-url code="inventor.chimpum.list.label.link" path="link"/>
	
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="inventor.item.form.button.update" action="/inventor/chimpum/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/chimpum/delete"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
		
			<acme:input-select code="inventor.chimpum.list.label.itemsPublished" path="item.code">
				<jstl:forEach items="${itemsByInventorPublished}" var ="item">
					<acme:input-option code="${item.code}" value="${item.code}"/>
				</jstl:forEach>
			</acme:input-select>
		
			<acme:submit code="inventor.item.list.button.create" action="/inventor/chimpum/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>