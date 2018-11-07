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
	<h2>brackets</h2>
	<f:form action="/bjjinfoaustria/displaydivisionincompetition"
		method="get">

		<select name="divisionId" id="divisionId">
			<c:forEach items="${listOfDivisions}" var="division">
				<option name="divisionId" value="${division.division.id}">${division.division.fullNameCategory}</option>
			</c:forEach>
		</select>
		<button type="submit">display</button>
	</f:form>
	<c:forEach items="${division.rounds }" var="round"
		varStatus="roundIndex">
		<div style="display: inline-block;">
			<h3>round ${roundIndex.index }</h3>
			<c:forEach items="${round.fightsInRound }" var="fight"
				varStatus="fightIndex">
				<h3>fight ${fightIndex.count }</h3>
				<c:forEach items="${fight.competitors }" var="competitor"
					varStatus="competitorIndex">
					<td>${competitor.user.firstName }</td>
					<td>${competitor.user.lastName }</td>
					
					<c:if test="${fight.activeButtonToAddWinner && round.activeRound }" >
						<f:form action="/bjjinfoaustria/addwinnertonextround/${competitor.id }/${fightIndex.index }/${roundIndex.index }"
							method="get">
							<button style="display:inline;"  type="submit">add winner</button>
						</f:form>
					</c:if>
					</br>
				</c:forEach>
				
				<div>----------------------</div>
				<div>----------------------</div>
			</c:forEach>
			<c:if test="${round.winners }">
			<h3>winners of the round</h3>
			<div style="display: inline-block;">
					<c:forEach items="${round.fightsInRound }" var="winner" varStatus="winnerIndex">
						 <td>${winnerIndex.count} ${winner.winner.user.firstName }</td>
						<td>${winner.winner.user.lastName }</td>
						<c:if test="${winner.winner!=null }" >
							<f:form action="/bjjinfoaustria/removewinnerfromarray/${winner.winner.id }/${winnerIndex.index }/${roundIndex.index }"
								method="get">
								<button style="display:inline-block;"  type="submit">remove</button>
							</f:form>
						</c:if>
						</br>
						<div>----------------------</div>
					</c:forEach>
								</div>
					<c:if test="${round.submitButtonActive }" >
							<f:form action="/bjjinfoaustria/submitwinnerstonextround/${roundIndex.index }"
								method="post">
								<button style="display:inline-block;"  type="submit">submit</button>
							</f:form>
						</c:if>
			</c:if>
		</div>
	</c:forEach>



	</div>
	<f:form action="/bjjinfoaustria/eventdetails/${event.id }" method="get">
		<button type="submit">back</button>
	</f:form>

</body>
</html>
