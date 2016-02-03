<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function(){
	$('.ui.dropdown').dropdown();
	
	$(".item-menu").click(function(e){
		e.stopPropagation();
		var idArea = ($.urlParam('idArea') == null ? "": $.urlParam('idArea'));
		go("atividade?idDemanda=".concat($(this).attr("data-id"),
				"&idArea=", idArea));	
	});
	
	$(".item-header-icon").click(function(e){
		e.stopPropagation();
		go("area");
	});
	
});
</script>

	<div class="ui secondary top fixed pointing menu red" id="header">
		<div class="item item-header-icon">
	    	<img src="resources/img/sabre-de-luz.png">&nbsp;&nbsp;&nbsp;&nbsp;
	    	<h5 class="ui red image header">Gedai - Gerenciador de Demandas e Atividades Internas</h5>
	 	</div>
	 	
		<div class="ui compact  menu menu-dropdown">
		  <div class="ui dropdown item">
		    Demandas
		    <i class="dropdown icon"></i>
		    <div class="menu">
	    		<c:forEach items="${demandasAll}" var="demanda" >
		      		<div class="item item-menu" data-id="${demanda.id}">${demanda.nome} ${demanda.descricao}</div>
	      		</c:forEach>
		    </div>
		  </div>
		</div>
	 	
		<div class="right menu">
			<a class="ui item" href="logout">Sair</a>
		</div>
	</div>