<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.configuration.form.label.system-currency" path="systemCurrency"/>
	<acme:input-textarea code="administrator.configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textarea code="administrator.configuration.form.label.strong-spam-terms-en" path="strongSpamTermsEn"/>
	<acme:input-textarea code="administrator.configuration.form.label.strong-spam-terms-es" path="strongSpamTermsEs"/>
	<acme:input-double code="administrator.configuration.form.label.strong-threshhold" path="strongThreshold"/>
	<acme:input-textarea code="administrator.configuration.form.label.weak-spam-terms-en" path="weakSpamTermsEn"/>
	<acme:input-textarea code="administrator.configuration.form.label.weak-spam-terms-es" path="weakSpamTermsEs"/>
	<acme:input-double code="administrator.configuration.form.label.weak-threshhold" path="weakThreshold"/>				

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:submit code="administrator.configuration.form.button.update" action="/administrator/system-configuration/update"/>
		</jstl:when>
	</jstl:choose>

</acme:form>