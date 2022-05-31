<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-educielan" action="https://www.youtube.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-rafjimfer" action="https://www.twitch.tv/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-jesrivgal" action="https://play.pokemonshowdown.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-albsalcab" action="https://play.hbomax.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-dantorval" action="https://ev.us.es/webapps/blackboard/execute/modulepage/view?course_id=_52178_1&cmp_tab_id=_189480_1&mode=view"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-fraviltor" action="https://www.netflix.com/es/"/>


			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list-user-accounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.anonymous.all-tools-published" action="/any/item/list-published-tools"/>
			<acme:menu-suboption code="master.menu.anonymous.all-components-published" action="/any/item/list-published-components"/>
      		<acme:menu-suboption code="master.menu.anonymous.list-chirp" action="/any/chirp/list"/>
      		<acme:menu-suboption code="master.menu.anonymous.toolkit.list" action="/any/toolkit/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.list-user-accounts" action="/any/user-account/list"/>			
			<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
      		<acme:menu-suboption code="master.menu.authenticated.list-chirp" action="/any/chirp/list"/>
      		<acme:menu-suboption code="master.menu.anonymous.all-tools-published" action="/any/item/list-published-tools"/>
			<acme:menu-suboption code="master.menu.anonymous.all-components-published" action="/any/item/list-published-components"/>
			<acme:menu-suboption code="master.menu.authenticated.announcement" action="/authenticated/announcement/list"/>
      		<acme:menu-suboption code="master.menu.authenticated.toolkit.list" action="/any/toolkit/list"/>

		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>	
			<acme:menu-suboption code="master.menu.administrator.administrator-dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	

		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
		<acme:menu-suboption code="master.menu.inventor.patronage-report" action="/inventor/patronage-report/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronage.list" action="/inventor/patronage/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.item.list" action="/inventor/item/list-my-components"/>
      		<acme:menu-suboption code="master.menu.inventor.item.list-my-tools" action="/inventor/item/list-my-tools"/>
      		<acme:menu-suboption code="master.menu.inventor.item.list-mine" action="/inventor/toolkit/list-mine"/>
      		<acme:menu-suboption code="master.menu.inventor.chimpum.list" action="/inventor/chimpum/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronages" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.patron.patronage-reports" action="/patron/patronage-report/list"/>
      <acme:menu-suboption code="master.menu.patron.patron-dashboard" action="/patron/patron-dashboard/show"/>

		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-inventor" action="/authenticated/inventor/create" access="!hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.inventor" action="/authenticated/inventor/update" access="hasRole('Inventor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

