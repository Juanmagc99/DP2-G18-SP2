<%--
- form.jsp
-
- Copyright (c) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" aria-describedby="Table of diferent parametres of the webpage for administrator" id="dashboard">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.num-private-task"/>
		</th>
		<td>
			<acme:print value="${numPrivateTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.num-public-task"/>
		</th>
		<td>
			<acme:print value="${numPublicTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.num-finished-task"/>
		</th>
		<td>
			<acme:print value="${numFinishedTask}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.num-current-task"/>
		</th>
		<td>
			<acme:print value="${numCurrentTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.num-executions"/>
		</th>
		<td>
			<acme:print value="${numExecutions}"/> <acme:message code="administrator.dashboard.form.label.dias"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.num-workloads"/>
		</th>
		<td>
			<acme:print value="${numWorkloads}"/>
		</td>
	</tr>		
</table>


<table class="table table-sm" id="ratio">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratioShoutTrue"/>
		</th>
		<td>
			<acme:print value="${ratioShoutsFlaggedTrue}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratioShoutFalse"/>
		</th>
		<td>
			<acme:print value="${ratioShoutsFlaggedFalse}"/>
		</td>
	</tr>
</table>

<table class="table table-sm" id="ratioYear">

	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.ratioYear"/>
		</th>
		<td>
			<acme:print value="${ratioShoutsYear2020}"/>
		</td>
	</tr>
</table>

<table class="table table-sm" id="averageAndDeviation">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageUSD"/>
		</th>
		<td>
			<acme:print value="${averageSheetGroupByCurrency1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageEUR"/>
		</th>
		<td>
			<acme:print value="${averageSheetGroupByCurrency2}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviatioUSD"/>
		</th>
		<td>
			<acme:print value="${deviationSheetGroupByCurrency1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationEUR"/>
		</th>
		<td>
			<acme:print value="${deviationSheetGroupByCurrency2}"/>
		</td>
	</tr>
</table>

