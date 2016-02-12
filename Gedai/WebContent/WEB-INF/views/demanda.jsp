<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<body>

<c:import url="/WEB-INF/views/components/header.jsp" />
	
<input type="hidden" value="${idArea}" id="idArea" />
<div id="content" class="conteudo">
	<div class="ui three column grid">
		<div class="ui link cards">
			<c:forEach items="${demandas}" var="demanda">
			
				<div class="status-demanda" id="${demanda.id}">
					<c:forEach items="${demanda.lstProgressoRacional}" var="status">
						<div>
							<label data-nome>${status.nome}</label> 
							<label data-qtd>${status.qtd}</label>
						</div>
					</c:forEach>
				</div>
		     <gedai:cardDemanda progress="${demanda.progresso}" 
		    					label="${demanda.nome}" 
		    					id="${demanda.id}" 
		    					descricao="${demanda.descricao}" />
			</c:forEach>
			<script id="template-novaDemanda" type="text/x-jquery-tmpl">
				<div class="card card-demanda" onclick="onclickDemanda(this);" data-idDemanda="\${id}">
			    	<div class="content">
			      		<img class="right floated mini ui image mini-image-card" src="resources/img/sabre-de-luz.png">
			      		<div class="header">
			      	  		\${nome}
			      		</div>
			      		<div class="description">
			     			\${descricao}
			      		</div>
			    	</div>
			    	<div class="extra content">
				    	<div class="ui progress green card-demanda-pos" id="\${id}">
							<div class="bar progress-demanda" style="width: 0%;">
					  			<div class="progress">0%</div>
					  		</div>
						</div>
			    	</div>
			  </div>
			</script>
			<div id="novaDemanda"></div>
		</div>
		<div class="ui cards">
				<div class="card">
			    	<div class="content">
			    		<div class="header">Criar nova demanda</div>
			    		<div class="description"></div>
			    		<div class="ui bottom attached button card-novaDemanda">
			    			<i class="add icon "></i>
			    		</div>
			    	</div>
			    </div>
			</div>
		
	</div>
	
</div>
	<c:import url="demandaInserirModal.jsp" />
</body>

<script type="text/javascript">
$(document).ready(function(){
	$('.card-demanda-pos')
		.popup({position : 'right center',html: '*', 
				onShow : function(item){
					var popup = this;
					var texto = "";
					texto = texto.concat("<div class='ui two column grid'>");
					
					$(".status-demanda[id="+ $(item).attr("id") +"] div").each(function(){
						texto = texto.concat("<div class='row'>");
						
						texto = texto.concat("<div class='column bold'>", 
												$(this).find("label[data-nome]").text(),
											  "</div>");
						
						texto = texto.concat("<div class='column'>", 
											 	$(this).find("label[data-qtd]").text(),
											 "</div>");
						
						texto = texto.concat("</div>");
					});
					
					texto = texto.concat("</div>");
					popup.html(texto);
				}});
	
	$(".card-demanda").click(function(e){
		e.stopPropagation();
		onclickDemanda($(this));
	});
	
	
	$(".card-novaDemanda").click(function(e){
		clearAll(".ui.grid.grid-modal");
		$(".small.modal").modal('show');
		
	});
	
	$("#salvar-demanda").click(function(e){
		var demanda = {nome: 		$("#nome").val(),
					   descricao: 	$("#descricao").val(),
					   idArea:		$("#idArea").val(),
					   uuid:		guid()};
		$.ajax({
		    url: "inserirDemanda",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(demanda),
		    success: function(json) {
		    	$("#template-novaDemanda").tmpl(json).appendTo(".ui.link.cards");
		    }
		
		});
	});
});

function onclickDemanda(escopo){
	go("atividade?idDemanda=".concat($(escopo).attr("data-idDemanda"), "&idArea=", $("#idArea").val()));
}
</script>
</html>