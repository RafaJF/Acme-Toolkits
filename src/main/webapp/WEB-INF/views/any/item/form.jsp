<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">


	<acme:input-textbox code="any.item.form.label.name" path="name"/>	
	<acme:input-textbox code="any.item.form.label.code" path="code"/>
	<acme:input-textbox code="any.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="any.item.form.label.description" path="description"/>
	<acme:input-money code="any.item.form.label.retailPrice" path="retailPrice"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'show'&& newRetailPrice.getCurrency()!=retailPrice.getCurrency()}">
            <acme:input-money code="inventor.item.form.label.retail-price-conversion" path="newRetailPrice"/>
    	</jstl:when>
    </jstl:choose>
    
	<acme:input-url code="any.item.form.label.info" path="info"/>
	
	
	<acme:input-select code="any.item.form.label.itemType" path="itemType">
		<acme:input-option code="TOOL" value="TOOL" selected="${itemType == 'TOOL'}"/>
		<acme:input-option code="COMPONENT" value="COMPONENT" selected="${itemType == 'COMPONENT'}"/>
	</acme:input-select>
	<acme:input-textbox code="any.item.form.label.published" path="published"/>
	
	
	
	
</acme:form>
