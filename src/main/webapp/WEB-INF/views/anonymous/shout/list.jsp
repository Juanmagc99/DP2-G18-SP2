<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.extrasheet.xomen" path="extrasheet.xomen" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.extrasheet.budget.currency" path="extrasheet.budget.currency" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.extrasheet.budget.amount" path="extrasheet.budget.amount" width="5%"/>
	<acme:list-column code="anonymous.shout.list.label.extrasheet.important" path="extrasheet.important" width="5%"/>
	<acme:list-column code="anonymous.shout.list.label.extrasheet.deadline" path="extrasheet.deadline" width="5%"/>
	
</acme:list>