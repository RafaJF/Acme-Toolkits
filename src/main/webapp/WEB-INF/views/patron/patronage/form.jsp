<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<h1> Patronage </h1>
	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creation-moment" path="creationMoment"/>
	<acme:input-moment code="patron.patronage.form.label.start-date" path="startDate"/>
	<acme:input-moment code="patron.patronage.form.label.end-date" path="endDate"/>
	<acme:input-url code="patron.patronage.form.label.more-info" path="moreInfo"/>
	<acme:input-textbox code="patron.patronage.form.label.patron.company" path="patron.company"/>

 <hr>
	<h2> Inventor </h2>
	
	<acme:input-textbox code="patron.patronage.label.inventor-full-name" path="inventorFullName"/>
	<acme:input-textbox code="patron.patronage.label.inventor-email" path="inventorEmail"/>
	<acme:input-textbox code="patron.patronage.label.inventor-company" path="inventorCompany"/>
	<acme:input-textbox code="patron.patronage.label.inventor-statement" path="inventorStatement"/>
	<acme:input-textbox code="patron.patronage.label.inventor-info" path="inventorInfo"/> 
	</acme:form>
