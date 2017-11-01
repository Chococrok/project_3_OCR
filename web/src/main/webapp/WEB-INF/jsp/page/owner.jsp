<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes topos</title>
<%@ include file="/WEB-INF/jsp/style/global-style.jsp"%>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h2>${ sessionScope.owner.fullName }</h2>
		</div>
		<div class="card-content">
			<table>
				<tr>
					<td>Email:</td>
					<td>
						<form id="formEmail" action="owner" method="post">
							<input type="email" id="inputEmail"
								value="<c:out value="${ sessionScope.owner.email }"/>" disabled />
							<input type="hidden" id="hiddenEmail" name="email" />
						</form>
					</td>
					<td>
						<button form="formEmail" type="submit" id="buttonEmail"
							name="action" value="email"
							onClick="return editPersonalData('Email')">editer</button>
					</td>
				</tr>
				<tr>
					<td>Téléphone:</td>
					<td>
						<form id="formPhone" action="owner" method="post">
							<input type="text" id="inputPhone"
								value="<c:out value="${ sessionScope.owner.phoneNumber }"/>" disabled />
							<input type="hidden" id="hiddenPhone" name="phone" />
						</form>
					</td>
					<td>
						<button form="formPhone" type="submit" id="buttonPhone"
							name="action" value="phone"
							onClick="return editPersonalData('Phone')">editer</button>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<div class="card">
		<div class="card-header">
			<h2>Mes topos:</h2>
		</div>
		<div class="card-content list">
			<c:forEach items="${ requestScope.topos }" var="topo">
				<div class="list-item-column">
					<h4>${ topo.name }</h4>
					<form class="editable" action="owner#input${ topo.id }"
						method="post">
						<div>
							<label for="${ topo.id }">Ce topo est disponible:</label>
							<input type="checkbox" id="input${ topo.id }"
								${ topo.available ? 'checked' : '' } disabled />
							<input type="hidden" id="available${ topo.id }" name="available" />
							<input type="hidden" id="id${ topo.id }" name="id"
								value="${ topo.id }" />
						</div>
						<button type="submit" id="button${ topo.id }" name="action"
							value="availability"
							onClick="return editAvailability(${ topo.id })">editer</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/javascript/edit.jsp"%>
</body>
</html>