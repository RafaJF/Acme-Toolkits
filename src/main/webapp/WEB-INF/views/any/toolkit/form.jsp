

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<acme:form>
	<acme:input-textbox code="any.toolkit.form.label.code" path="code"/>	
	<acme:input-textarea code="any.toolkit.form.label.title" path="title"/>
	<acme:input-textarea code="any.toolkit.form.label.description" path="description"/>
	<acme:input-textarea code="any.toolkit.form.label.assambly-notes" path="assamblyNotes"/>
	<acme:input-url code="any.toolkit.form.label.url" path="url"/>
	<acme:input-money code="any.toolkit.form.label.total-price" path="totalPrice"/>
	
	<acme:button code="any.toolkit.form.button.items" action="/any/item/list-toolkit-items?id=${id}"/>
</acme:form>

