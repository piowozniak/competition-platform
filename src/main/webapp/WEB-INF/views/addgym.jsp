<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>BJJ INFO AUSTRIA</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
</style>
</head>
<body>
	<h2>add</h2>

	<!--  dodawanie gym  -->
	<f:form action="${contextPath }/add" method="post" modelAttribute="gym">
		<div>
			Id:
			<f:input path="id" disabled="true" />
		</div>
		<div>
			Name:
			<f:input path="name" />
		</div>
		<div>
			City:
			<f:select path="city.id" items="${cities}" itemLabel="name"
				itemValue="id" />
			<div></div>
			<div></div>
			Address:
			<f:input path="address" />
			<div></div>
			Phone number:
			<f:input path="phoneNumber" />
			<div>
				<div>
					Head coach:
					<f:input path="headCoach" />
				</div>
				<div>
					<f:hidden path="id" />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
				<button type="submit">submit</button>
	</f:form>
	<f:form action="${contextPath }/search" method="get">
		

		<button type="submit">back</button>
	</f:form>

</body>
</html>
