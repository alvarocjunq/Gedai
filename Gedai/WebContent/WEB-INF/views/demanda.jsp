<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demandas</title>
</head>
<body>

	<!-- Header -->
	<div class="ui secondary top fixed pointing menu red" id="header">
		<div class="item item-header-icon">
	    	<img src="resources/img/sabre-de-luz.png">&nbsp;&nbsp;&nbsp;&nbsp;
	    	<h5 class="ui red image header">Gedai - Gerenciador de Demandas e Atividades Internas</h5>
	 	</div>
<!-- 		<a class="active item">Demandas</a>  -->
<!-- 		<a class="item">Amigos </a> -->
		<div class="right menu">
			<a class="ui item" href="logout">Sair</a>
		</div>
	</div>
	
	<!-- Conteudo -->
	<div class="ui segment" id="content">
	<div class="ui three column grid">
		<c:forEach items="${demandas}" var="demanda">
		    <gedai:cardDemanda progress="0" label="${demanda.nome}" />
		</c:forEach>
	</div>
	</div>

	<!-- Footer -->
	<div class="ui bottom fixed menu"></div>
</body>
</html>