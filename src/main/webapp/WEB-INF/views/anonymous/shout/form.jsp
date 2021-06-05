<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info"/>
	
	<!-- <acme:form-moment code="anonymous.user-account.label.infoDate" path="infoDate"/> -->
	<acme:form-textbox code="anonymous.shout.form.label.infoDate" path="sheet.infoDate"/>
	
	<!-- <acme:form-moment code="anonymous.user-account.label.infoStamp" path="sheet.infoStamp"/> -->
	<acme:form-textbox code="anonymous.shout.form.label.infoMoney" path="sheet.infoMoney"/>
	<acme:form-checkbox code="anonymous.shout.form.label.infoFlag" path="sheet.infoFlag"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>

</acme:form>