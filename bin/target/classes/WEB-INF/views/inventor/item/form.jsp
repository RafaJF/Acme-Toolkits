<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textarea code="inventor.item.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.form.label.code" path="code"/>
	<acme:input-money code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-moment code="inventor.item.form.label.description" path="description"/>
	<acme:input-moment code="inventor.item.form.label.retail-price" path="retailPrice"/>
	<acme:input-url code="inventor.item.form.label.info" path="info"/>
</acme:form>