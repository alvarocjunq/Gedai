<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function(){
	$('.ui.dropdown').dropdown({ allowLabels:true});
	
	$(".item-header-icon").click(function(e){
		e.stopPropagation();
		go("area");
	});
	
	$("#menu-agenda").click(function(e){
		e.stopPropagation();
		go("agenda");
	});
	
	$.ajax({
	    url: "countAtividade",
	    type: 'GET',
	    success: function(contador) {
	    	$(".count-header").text(contador);
	    }
	});
	
});

$(document).on("click", ".item-menu", function(e) {
	e.stopPropagation();
	var idArea = ($.urlParam('idArea') == null ? "": $.urlParam('idArea'));
	go("atividade?idDemanda=".concat($(this).attr("data-id"),
			"&idArea=", idArea));	
});
</script>

	<div class="ui secondary top fixed pointing menu red" id="header">
		<div class="item item-header-icon">
	    	<img src="resources/img/sabre-de-luz.png">&nbsp;&nbsp;&nbsp;&nbsp;
	    	<h5 class="ui red image header">Gedai - Gerenciador de Demandas e Atividades Internas</h5>
	 	</div>
	 	
	 	<c:if test="${param.visual != 'clean'}">
	 		<script>
				$.ajax({
				    url: "obterDemandaPorArea?idArea="+$.urlParam("idArea"),
				    type: 'GET',
				    contentType : "application/json",
				    success: function(lista) {
				    	lista.forEach(function(item){
				    		$("#template-demanda-combo").tmpl(item).appendTo(".menu-demanda");
				    	});
				    }
				});
	 		</script>
			<div class="ui compact menu menu-dropdown">
			  <div class="ui dropdown item">
			    Demandas
			    <i class="dropdown icon"></i>
			    <div class="menu menu-demanda">
					<script id="template-demanda-combo" type="text/x-jquery-tmpl">
			      		<div class="item item-menu" data-id="\${id}">\${nome} \${descricao}</div>
					</script>
			    </div>
			  </div>
			</div>
		</c:if>
<!-- 		<div class="ui compact menu"> -->
	 	<a class="item active" style="margin-left: 10px;" id="menu-agenda">Agenda
	 		<div class="floating ui red label count-header">0</div>
	 	</a>
	 	
<!-- 		</div> -->
		<div class="right menu">
			<a class="ui item" href="logout"><i class="power icon"></i> Sair</a>
		</div>
	</div>