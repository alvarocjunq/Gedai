<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />



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

	<div class="item">
		<div class="ui card">
			<div class="content lista-daily-task" id="">
				<h4>Tarefas Di&aacute;rias</h4>
				<div class="lista-task">
					<c:forEach items="${tarefas}" var="tarefa">
						<div class="task" data-idTarefa="${tarefa.id}">		
							<a class="ui black medium circular label contador-task">${tarefa.contador}</a>
							<a class="ui tiny red circular label remove-icon"><i class="minus icon icon-task"></i></a> 
							<a class="ui tiny green circular label add-icon"><i class="plus icon icon-task"></i></a>
							<br /><br />
							<label>${tarefa.nome}</label>
						</div>
					</c:forEach>
				</div>
				<div class="content footer-tarefa">
				  	<label class="salvar-tarefa">Salvar</label>
				  	<label class="cancelar-tarefa"><i class="remove large icon"></i></label>
				  	<label class="nova-tarefa">Nova tarefa <i class="plus square outline icon"></i></label> 
		    	</div>
		    	
	    	</div>
    	</div>
	</div>
	
	</div>
		
</div>
<script id="template-nova-tarefa" type="text/x-jquery-tmpl">
	<a class="ui black medium circular label float-right cursor-default">\${contador}</a>
	<a class="ui tiny red circular label"><i class="minus icon icon-task"></i></a> 
	<a class="ui tiny green circular label"><i class="plus icon icon-task"></i></a>
	<br /><br />
	<label>\${nome}</label>
</script>

<c:import url="demandaAtividadeModal.jsp" />
</body>

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
	
	$(".nova-tarefa").click(function(e){
		e.stopPropagation();
		var id = guid();
		var card = "";
		card = card.concat("<div class='task'><textarea maxlength='100' id='", id, "'></textarea></div>"); 
		$(this).parent().parent().find(".lista-task").append(card);
		$("#"+id).focus();	
		$(this).parent().find(".salvar-tarefa").css("visibility", "visible");
		$(this).parent().find(".cancelar-tarefa").css("visibility", "visible");
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
	
	$(".cancelar-tarefa").click(function(e){
		e.stopPropagation();
		var nomeTarefa = "";
		var tarefaLista = $(this).parent().parent();
		
		tarefaLista.find(".lista-task .task").each(function(){
			nomeTarefa = $(this).find("textarea").val();
			if(nomeTarefa != undefined){
				$(this).remove();
			}
		})
		$(this).css("visibility", "hidden");
		$(this).parent().find(".salvar-tarefa").css("visibility", "hidden");
	});
	
	$(".salvar-tarefa").click(function(e){
		e.stopPropagation();
		var botaoSalvar = $(this);
		var demandaTarefa = botaoSalvar.parent().parent();
		var lstTarefa = demandaTarefa.find(".lista-task");
		var tarefas = [];
		var nomeTarefa = "";
		var newTextArea;
		var idDemanda = $.urlParam("idDemanda");
		
		lstTarefa.find(".task").each(function(){
			newTextArea = $(this).find("textarea");
			nomeTarefa = newTextArea.val();
			uuid = newTextArea.attr('id');
			
			if(nomeTarefa){
				tarefas.push({	idDemanda : idDemanda, 
							 	nome : nomeTarefa,
							 	uuid:  uuid
								});
			}
		});
		
		$.ajax({
		    url: "inserirTarefas",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(tarefas),
		    success: function(lista) {
		    	lstTarefa.find(".task").each(function(){
					var _this = $(this);
					var textArea = _this.find("textarea");
					nomeTarefa = textArea.val();
					if(nomeTarefa == "")
						_this.remove();
					
					if(nomeTarefa !== ""){
						lista.forEach(function(item){
							if(textArea.attr('id')===item.uuid){
								_this.attr("id",item.id);
								_this.attr("data-idTarefa",item.id);
								textArea.replaceWith($("#template-nova-tarefa").tmpl({nome: item.nome, contador: item.contador}));
							}
				    	});	
					}
			    	botaoSalvar.css("visibility", "hidden");
			    	botaoSalvar.parent().find(".cancelar-tarefa").css("visibility", "hidden");
			    	
				});
			}
		});
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

$(document).on("click", ".remove-icon", function(e) {
	e.stopPropagation();
	var objCont = $(this).parent().find(".contador-task");
	var contador = $(this).parent().find(".contador-task").text();
	
	if(contador === '0') return;
	
	contador = parseInt(contador) - 1;
	objCont.text(contador);
	var idDemandaTarefa = objCont.closest(".task").attr("data-idTarefa");
	onClickUpdateContador(idDemandaTarefa, contador);
});


$(document).on("click", ".add-icon", function(e) {
	e.stopPropagation();
	var objCont = $(this).parent().find(".contador-task");
	var contador = objCont.text();
	contador = parseInt(contador) + 1;
	objCont.text(contador);
	var idDemandaTarefa = objCont.closest(".task").attr("data-idTarefa");
	onClickUpdateContador(idDemandaTarefa, contador);
});

function onClickUpdateContador(idDemandaTarefa, contador){
	$.ajax({
	    url: "updateContador",
	    type: 'POST',
	    contentType : "application/json",
	    data: JSON.stringify({id: idDemandaTarefa, contador: contador}),
	    success: function() {}
	});
}

$(document).on("keydown", "textarea", function() {
    while ($(this).prop("scrollHeight") > $(this).prop("offsetHeight")){
    	var rows = $(this).prop("rows");
    	rows += 1;
    	$(this).prop("rows", rows);	
    }
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
</html>