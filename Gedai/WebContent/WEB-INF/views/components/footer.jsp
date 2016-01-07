<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="margin-top-50">&nbsp;</div>

<footer id="footer">

<div id="versao">v1.0&nbsp;<c:out value="${ambiente}"/></div>	

	<c:choose>
		<c:when test="${param.salvar eq 'salvar'}">
			<button type="button" class="btn btn-default navbar-btn" id="btnSalvar">Salvar</button>
		</c:when>
		
		<c:otherwise>
			<div style="padding-top: 30px">&nbsp;</div>	
		</c:otherwise>
	</c:choose>
</footer>