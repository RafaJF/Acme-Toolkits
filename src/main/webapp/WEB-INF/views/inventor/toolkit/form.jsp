<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.form.label.description" path="description"/>
	<acme:input-textbox code="inventor.toolkit.form.label.assambly-notes" path="assamblyNotes"/>
	<acme:input-url code="inventor.toolkit.form.label.url" path="url"/>
	<acme:input-money code="inventor.toolkit.form.label.total-price" path="totalPrice" readonly="true"/>
	<acme:input-checkbox code="inventor.toolkit.form.label.published" path="published"/>
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/item/list-toolkit-items?id=${id}"/>
	<%-- 	<jstl:forEach items ="${items}" var="item">
			<acme:input-checkbox code="${item.name}" path="${item.name}"/>
			</jstl:forEach> --%>
	<jstl:choose>	 
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
		
			
		</jstl:when>	
		<jstl:when test="${acme:anyOf(command,'show,update,delete,publish') && published == false}">
		<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
		
		<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
		</jstl:when>	
	</jstl:choose>
	<jstl:choose>
	<jstl:when test="${published ==true }">
		<acme:submit code="inventor.toolkit.form.button.unpublish" action="/inventor/toolkit/publish"/>
	</jstl:when>
	<jstl:when test="${published ==false }">
		<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
		</jstl:when></jstl:choose>
	<%-- <acme:button code="inventor.toolkit.form.button.quantities" action="/inventor/quantity/list?id=${id}"/>
	<acme:button code="inventor.toolkit.form.button.quantities.create" action="/inventor/quantity/create?id=${id}"/> --%>
</acme:form> 