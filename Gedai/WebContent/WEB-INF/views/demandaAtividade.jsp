<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	$('.ui.dropdown').dropdown();
	$('.dropdown').dropdown({direction: 'upward'});
	
	$(".card-atividade").click(function(e){
		e.stopPropagation();
		var id = $(this).attr("id");
		$("#idAtividade").val(id);
		
		$('.ui.basic.modal')
				.modal({
				    onShow: function(){
						$.ajax({
						    url: "obterAtividade?idAtividade="+id,
						    type: 'GET',
						    contentType : "application/json",
						    success: function(data) {
						    	$("#header-modal-atividade").text(data.nome);
						    	$(".description p").text(data.descricao);
						    }
						});
				    	
						$.ajax({
						    url: "obterListas?idDemanda="+$("#idDemanda").val(),
						    type: 'GET',
						    contentType : "application/json",
						    success: function(lista) {
						    	$("#lstListas .menu .item").removeClass(".active");
						    	$("#lstListas .menu .item").removeClass(".selected");
						    	$("#lstListas .text").text("Mover para...");
						    	loadTable("lstListas .menu", lista, getLineListas)
						    }
						});
				    	
				    },
				    onHidden: function(){
				    	$("#header-modal-atividade").text("");
				    	
				    	if($(".description p"))
					    	$(".description p").text("");
				    		
				    	if($(".description textarea"))
				    		$(".description textarea").replaceWith("<p></p>");
				    	
				    }
			  	})
			  	.modal('show');
	});
	
	$(".nova-atividade").click(function(e){
		e.stopPropagation();
		var id = guid();
		var card = "";
		card = card.concat("<div class='card-atividade'><textarea id='", id, "'></textarea></div>"); 
		$(this).parent().parent().find(".atividades").append(card);
		$("#"+id).focus();	
		$(this).parent().find(".salvar-atividade").css("visibility", "visible");
		$(this).parent().find(".cancelar-atividade").css("visibility", "visible");
	});
	
	$(".cancelar-atividade").click(function(e){
		e.stopPropagation();
		var nomeAtividade = "";
		var demandaLista = $(this).parent().parent();
		
		demandaLista.find(".atividades .card-atividade").each(function(){
			nomeAtividade = $(this).find("textarea").val();
			if(nomeAtividade != undefined){
				$(this).remove();
			}
		})
		$(this).css("visibility", "hidden");
		$(this).parent().find(".salvar-atividade").css("visibility", "hidden");
	});
	
	$(".salvar-atividade").click(function(e){
		e.stopPropagation();
		
		var botaoSalvar = $(this);
		var demandaLista = botaoSalvar.parent().parent();
		var lstAtividades = demandaLista.find(".atividades");
		var idLista = demandaLista.attr("id");
		
		var atividades = [];
		var nomeAtividade = "";
		
		lstAtividades.find(".card-atividade").each(function(){
			nomeAtividade = $(this).find("textarea").val();
			if(nomeAtividade){
				atividades.push({	idDemandaLista : idLista, 
								 	nome : nomeAtividade
								});
			}
		})
		
		$.ajax({
		    url: "inserirAtividades",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(atividades),
		    success: function() {
		    	
				lstAtividades.find(".card-atividade").each(function(){
					var textArea = $(this).find("textarea");
					nomeAtividade = textArea.val();
					
					if(nomeAtividade == "")
						$(this).remove();
					
					if(nomeAtividade !== "")
						textArea.replaceWith("<label>" + nomeAtividade + "</label>");
					
				});
		    	
		    	botaoSalvar.css("visibility", "hidden");
		    	botaoSalvar.parent().find(".cancelar-atividade").css("visibility", "hidden");
		    }
		});
	});
	
	$(".description").click(function(e){
		e.stopPropagation();
		var p = $(this).find("p");
		var texto = p.text();
		p.replaceWith("<textarea>"+texto+"</textarea>");
		
	});
	
	$("#salvar-modal").click(function(){
		var _nome = $("#header-modal-atividade").text();
    	var _descricao = ($(".description textarea").val() ? $(".description textarea").val() : $(".description p").text());
    	var _idDemandaLista = $("#lstListas .menu .selected").attr("data-id");
    	var idAtividade = $("#idAtividade").val();
    	
		var atividade = {id : idAtividade, 
						 nome: _nome,
						 descricao: _descricao,
						 idDemandaLista: _idDemandaLista};
		
		$.ajax({
		    url: "atualizarAtividade",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(atividade),
		    success: function() {
				$(".ui.basic.modal").modal("hide");
				
		    	
		    	if(_idDemandaLista){
		    		var atividade = $(".card-atividade[data-idAtividade= "+idAtividade+"]");
		    		$(".lista-atividade[id="+_idDemandaLista+"] .atividades").append(atividade);
		    	}
		    }
		});
	});
	
});

function getLineListas(item){
	var linha="";
	linha = linha.concat("<div class='item' data-id='", item.id,"'>", item.nome ,"</div>")
	return linha;
}

</script>

<body>

<c:import url="/WEB-INF/views/components/header.jsp" />
<input type="hidden" value="${idDemanda}" id="idDemanda"/>
<input type="hidden" value="" id="idAtividade"/>
<div id="content">
	<div class="ui large horizontal list">
	
	<c:forEach items="${listas}" var="lista" >
		<div class="item">
			<div class="ui card">
				<c:set var="contador" value="0"/>
				
				<div class="content lista-atividade" id="${lista.id}">
					<h4>${lista.nome}</h4>
					<div class="atividades">
					
						<c:forEach items="${lista.lstAtividades}" var="atividade" varStatus="i">
							<c:set var="contador" value="${i.count}"/>
							<div class="card-atividade" id="${atividade.id}" data-idAtividade="${atividade.id}" draggable="true">
								<label>
									${atividade.nome}
								</label>
							</div>
						</c:forEach>
						
					</div>
					<div class="content footer-atividade">
					  	<label class="salvar-atividade">Salvar</label>
					  	<label class="cancelar-atividade"><i class="remove large icon"></i></label>
					  	<label class="nova-atividade">Nova atividade <i class="plus square outline icon"></i></label> 
			    	</div>
		    	</div>
		    	<div class="floating ui green label">${contador}</div>
	    	</div>
		</div>
	</c:forEach>
	</div>
		
</div>
<c:import url="demandaAtividadeModal.jsp" />
</body>

<script>
function handleDragStart(e) {
	this.style.opacity = '0.4';  // this / e.target is the source node.
}

function handleDragOver(e) {
  if (e.preventDefault) {
    e.preventDefault(); // Necessary. Allows us to drop.
  }
  e.dataTransfer.dropEffect = 'copy';  // See the section on the DataTransfer object.
  e.dataTransfer.setData('Text', this.id); // required otherwise doesn't work
  return false;
}

function handleDragEnd(e) {
  // this/e.target is the source node.
  $(".lista-atividade").removeClass("over");
  this.style.opacity = '1.0';
}

function handleDragEnter(e) {
	$(this).closest(".lista-atividade").addClass("over"); // this / e.target is the current hover target.
}

function handleDragLeave(e) {
	$(this).closest(".lista-atividade").removeClass("over");// this / e.target is previous target element.
}
function handleDrop(e) {
  // this / e.target is current target element.

  if (e.stopPropagation) {
    e.stopPropagation(); // stops the browser from redirecting.
  }

  // See the section on the DataTransfer object.

  return false;
}
	

var cards = document.querySelectorAll(".atividades .card-atividade");
[].forEach.call(cards, function(card) {
	card.addEventListener('dragenter', handleDragEnter, false);
	card.addEventListener('dragleave', handleDragLeave, false);
	card.addEventListener('dragstart', handleDragStart, false);
	card.addEventListener('dragover', handleDragOver, false);
	card.addEventListener('dragend', handleDragEnd, false);
	card.addEventListener('drop', handleDrop, false);
});

</script>
</html>