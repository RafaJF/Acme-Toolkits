<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.quantity.form.label.toolkit.title" path="toolkit.title" readonly="true"/>
	<acme:input-textbox code="inventor.quantity.form.label.item.name" path="item.name"/>
	<acme:input-integer code="inventor.quantity.form.label.amount" path="amount"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create?masterId=${id}"/>
			
		</jstl:when>
		</jstl:choose>		
		
	
</acme:form>