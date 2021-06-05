<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.sheet.infoDate" path="sheet.infoDate" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.sheet.infoMoney.currency" path="sheet.infoMoney.currency" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.sheet.infoMoney.amount" path="sheet.infoMoney.amount" width="10%"/>
	<acme:list-column code="anonymous.shout.list.label.sheet.infoFlag" path="sheet.infoFlag" width="5%"/>
</acme:list>