<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.item.code" path="item.code"/>
	<acme:list-column code="inventor.quantity.list.label.item.name" path="item.name"/>
	<acme:list-column code="inventor.quantity.list.label.item.itemType" path="item.itemType"/>
	<acme:list-column code="inventor.quantity.list.label.item.retailPrice" path="item.retailPrice"/>
	<acme:list-column code="inventor.quantity.list.label.item.amount" path="amount"/>
	
	
</acme:list>
<jstl:if test= "${isPublished == false }">
	<acme:button code="inventor.quantity.list.button.create" action="/inventor/quantity/create?id=${toolkitId}"/>
</jstl:if>	
