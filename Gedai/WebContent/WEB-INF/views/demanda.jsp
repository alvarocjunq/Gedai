<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	$(".card-demanda").click(function(e){
		e.stopPropagation();
		
		go("atividade?idDemanda=".concat($(this).attr("data-idDemanda")));
	});
});
</script>

<body>

<c:import url="/WEB-INF/views/components/header.jsp" />
	
<div id="content" class="conteudo">
	<div class="ui three column grid">
		<c:forEach items="${demandas}" var="demanda">
		    <gedai:cardDemanda progress="0" label="${demanda.nome}" id="${demanda.id}" />
		</c:forEach>
	</div>
</div>

</body>
</html>