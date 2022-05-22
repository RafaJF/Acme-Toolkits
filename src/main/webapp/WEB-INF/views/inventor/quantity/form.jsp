<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.quantity.form.label.toolkit.title" path="toolkit.title" readonly="true"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-select path="item.id" code="inventor.quantity.form.label.item.name">
				<jstl:forEach var="item" items="${publishedItems}">
				<acme:input-option  code="${item.name} - ${item.itemType}" value="${item.id}"/>
				</jstl:forEach>
			</acme:input-select>
		
		</jstl:when>

		<jstl:otherwise>
			<acme:input-textbox code="inventor.quantity.form.label.item.name" path="item.name" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.item.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.item.technology" path="item.technology" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.item.description" path="item.description" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.item.retaiLPrice" path="item.retailPrice" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.item.itemType" path="item.itemType" readonly="true"/>
			
		</jstl:otherwise>	
	</jstl:choose>
	<acme:input-integer code="inventor.quantity.form.label.amount" path="amount"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			<acme:submit code="inventor.quantity.form.button.delete" action="/inventor/quantity/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create?id=${id}"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form>