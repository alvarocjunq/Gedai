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
	
	
	$(".nova-atividade").click(function(e){
		e.stopPropagation();
		var id = guid();
		var card = "";
		card = card.concat("<div class='card-atividade'><textarea maxlength='100' id='", id, "'></textarea></div>"); 
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
		var newTextArea;
		var uuid ="";
		var contListaAnterior;
		var contListaPosterior;
		var atividade;
		lstAtividades.find(".card-atividade").each(function(){
			newTextArea = $(this).find("textarea");
			nomeAtividade = newTextArea.val();
			uuid = newTextArea.attr('id');
			
			if(nomeAtividade){
				atividades.push({	idDemandaLista : idLista, 
								 	nome : nomeAtividade,
								 	uuid:  uuid
								});
			}
		})
		
		$.ajax({
		    url: "inserirAtividades",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(atividades),
		    success: function(lista) {
				lstAtividades.find(".card-atividade").each(function(){
					var _this = $(this);
					var textArea = _this.find("textarea");
					nomeAtividade = textArea.val();
					if(nomeAtividade == "")
						_this.remove();
					if(nomeAtividade !== ""){
						lista.forEach(function(item){
							if(textArea.attr('id')===item.uuid){
								_this.attr("id",item.id);
								_this.attr("data-idAtividade",item.id);
								_this.attr("onclick",'onclickAtividade(this)');
							}
							
				    	});	
						textArea.replaceWith("<label>" + nomeAtividade + "</label>");
					}
			    	botaoSalvar.css("visibility", "hidden");
			    	botaoSalvar.parent().find(".cancelar-atividade").css("visibility", "hidden");
			    	
				});
				if(idLista){
					atividade = $(".card-atividade[data-idAtividade= "+idLista+"]");
		    		contListaPosterior = parseInt($(".contador[data-idDemandaLista="+idLista+"]").text()) +1;
		    		$(".contador[data-idDemandaLista="+idLista+"]").text(contListaPosterior);
				}
			}
		});
	});
	
	
	$(".description").click(function(e){
		e.stopPropagation();
		var p = $(this).find("p");
		var texto = p.html();
		texto = texto.replace(/<br>/g, '\n');
		p.replaceWith("<textarea>"+texto+"</textarea>");
		$(this).find("textarea").focus();
	});
	
	$("#salvar-modal").click(function(){
		
		var demandaLista = $("#lstListas .menu .selected");
		var _nome = $("#header-modal-atividade").text();
    	var _descricao = ($(".description textarea").val() ? $(".description textarea").val() : $(".description p").text());
    	var _idDemandaLista = demandaLista.attr("data-id");
    	var _textDemandaLista = demandaLista.text();
    	var idAtividade = $("#idAtividade").val();
    	
		var atividade = {id : idAtividade, 
						 nome: _nome,
						 descricao: _descricao,
						 idDemandaLista: _idDemandaLista,
						 nomeDemandaLista: _textDemandaLista};
		
		$.ajax({
		    url: "atualizarAtividade",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(atividade),
		    success: function() {
				$(".ui.modal").modal("hide");
		    	
		    	if(_idDemandaLista){
		    		var atividade = $(".card-atividade[data-idAtividade= "+idAtividade+"]");
		    		var contListaAnterior = parseInt(atividade.closest(".ui.card").find(".floating.ui.label").text()) - 1;
		    		var contListaPosterior = parseInt($(".contador[data-idDemandaLista="+_idDemandaLista+"]").text()) +1;
		    		
		    		atividade.closest(".ui.card").find(".floating.ui.label").text(contListaAnterior);
		    		$(".contador[data-idDemandaLista="+_idDemandaLista+"]").text(contListaPosterior);
		    		
		    		$(".lista-atividade[id="+_idDemandaLista+"] .atividades").append(atividade);
		    	}
		    }
		});
	});
	
});

function onclickAtividade(escopo){
	var id = $(escopo).attr("id");
	$("#idAtividade").val(id);
	$('.ui.long.modal')
			.modal({
			    onShow: function(){
					$.ajax({
					    url: "obterAtividade?idAtividade="+id,
					    type: 'GET',
					    contentType : "application/json",
					    success: function(data) {
					    	$("#header-modal-atividade").text(data.nome);
					    	$(".description p").html(data.descricao);
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
}
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
<div id="content" class="conteudo">

	<h3 class="ui header header-atividades">${demanda.nome} <small>${demanda.descricao}</small></h3>
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
							<div class="card-atividade" onclick="onclickAtividade(this);" id="${atividade.id}" data-idAtividade="${atividade.id}" draggable="true">
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
		    	<div class="floating ui green label contador" data-idDemandaLista="${lista.id}">${contador}</div>
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