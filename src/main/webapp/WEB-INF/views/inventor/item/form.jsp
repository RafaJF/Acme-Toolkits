<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textarea code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code"/>

	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="description"/>
	<acme:input-money code="inventor.item.form.label.retail-price" path="retailPrice"/>
	<acme:input-url code="inventor.item.form.label.info" path="info"/>
	<acme:input-select code="inventor.item.form.label.item-type" path="itemType">
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
		<acme:input-option code="TOOL" value="TOOL" selected="${itemType == 'TOOL'}"/>
	</acme:input-select>
	<acme:input-checkbox code="inventor.item.form.label.published" path="published"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
			<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
			<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.item.list.button.create" action="/inventor/item/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form>