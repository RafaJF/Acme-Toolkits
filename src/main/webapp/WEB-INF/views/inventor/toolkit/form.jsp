<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="inventor.toolkit.form.label.assambly-notes" path="assamblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.url" path="url"/>
	<acme:input-money code="inventor.item.list.label.total-price" path="totalPrice"/>
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/item/list-toolkit-items?id=${id}"/>
</acme:form> 