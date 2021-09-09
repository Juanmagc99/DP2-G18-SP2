<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	
	<!-- <acme:form-moment code="anonymous.user-account.label.xomen" path="xomen"/> -->
	<acme:form-textbox code="anonymous.shout.form.label.xomen" path="extrasheet.xomen"/>
	<acme:form-moment code="anonymous.shout.form.label.deadline" path="extrasheet.deadline"/>
	<!-- <acme:form-moment code="anonymous.user-account.label.deadline" path="extrasheet.deadline"/> -->
	<acme:form-money code="anonymous.shout.form.label.budget" path="extrasheet.budget"/>
	<acme:form-checkbox code="anonymous.shout.form.label.important" path="extrasheet.important"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>

</acme:form>