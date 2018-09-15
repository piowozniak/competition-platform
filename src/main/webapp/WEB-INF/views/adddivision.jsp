<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

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
	<h2>join the event</h2>

	<!--  dodawanie do eventu  -->
	<f:form action="/bjjinfoaustria/adddivision" method="post" modelAttribute="division">
		<div>
			Id:
			<f:input path="id" disabled="true"/>
		</div>
		<div>
			Belt Category:
			<f:select  path="beltCategory" items="${ beltCategories}" />
		</div>
		<div>
			Weight Category:
			<f:select path="weightCategory" items="${ weightCategories}" />
		</div>
		<div>
			Competition:
			<f:input disabled="true" path="competition.id" item="${competition }" itemLabel="nameOfEvent" itemValue="id"/>
		</div>
		
		
		<div>
			<f:hidden path="id" />
			
		</div>
		<div>
			<f:hidden path="competition.id" />
			
		</div>
		<button type="submit">submit</button>
	</f:form>
	<f:form action="/bjjinfoaustria/createcompetition" method="get">
		<button type="submit">back</button>
	</f:form>

</body>
</html>