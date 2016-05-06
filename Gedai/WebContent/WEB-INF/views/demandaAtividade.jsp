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
<input type="hidden" value="" id="idTarefa"/>

<div id="content" class="conteudo">
	
	<h3 class="ui header header-atividades">${demanda.nome} <small>${demanda.descricao}</small> <i class="file excel outline icon"></i>
	<i class="file pdf outline icon" style="margin: auto;"></i></h3>
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
						<c:if test="${lista.nome eq 'Fazer'}">
						  	<label class="salvar-atividade">Salvar</label>
						  	<label class="cancelar-atividade"><i class="remove large icon"></i></label>
						  	<label class="nova-atividade">Nova atividade <i class="plus square outline icon"></i></label>
						</c:if> 
			    	</div>
		    	</div>
		    	<div class="floating ui green label contador" data-idDemandaLista="${lista.id}">${contador}</div>
	    	</div>
		</div>
	</c:forEach>
	</div>
		
</div>
<script id="template-nova-tarefa" type="text/x-jquery-tmpl">
	<a class="ui black medium circular label contador-task">\${contador}</a>
	<a class="ui tiny red circular label remove-icon"><i class="minus icon icon-task"></i></a> 
	<a class="ui tiny green circular label add-icon"><i class="plus icon icon-task"></i></a>
	<br /><br />
	<label>\${nome}</label>
</script>

<c:import url="demandaAtividadeModal.jsp" />
<c:import url="associarUsuarioModal.jsp" />
<c:import url="visualizarListaAtividadesModal.jsp" />
</body>

<script type="text/javascript">
$(document).ready(function(){
	$('.selection.dropdown.up').dropdown({direction: 'upward'});
	
	$("#dataInicio").mask("99/99/9999",{placeholder:"dd/mm/aaaa"});
	$("#dataFim").mask("99/99/9999",{placeholder:"dd/mm/aaaa"});
	
	$("#dataIni").mask("99/99/9999",{placeholder:"dd/mm/aaaa"});
	$("#dataFinazalicao").mask("99/99/9999",{placeholder:"dd/mm/aaaa"});
	
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
	
	$('.file.excel.outline.icon').click(function(e){
		e.stopPropagation();
		var idDemanda = $.urlParam("idDemanda");
		
	    $.fileDownload("exportExcelAtividade?idDemanda=".concat(idDemanda), {
	        successCallback: function (url) {},
	        failCallback: function (responseHtml, url) {}
	    });
	    return false;

	});
	$('.file.pdf.outline.icon').click(function(e){
		e.stopPropagation();
		var idDemanda = $.urlParam("idDemanda");
		$("#reportModal")
		.modal({
		    onShow: function(){
		    	$("#frame-report").attr("src","visualizarAtividades?idDemanda=".concat(idDemanda));
		 	},
			 onHidden: function(){
				 $("#frame-report").attr("src","");
			 }
		}).modal('show');
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
								textArea.replaceWith($("#template-nova-tarefa").tmpl({nome: item.nome, contador: 0}));
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
		var nomeLista = lstAtividades.parent().find("h4").text();
		
		lstAtividades.find(".card-atividade").each(function(){
			newTextArea = $(this).find("textarea");
			nomeAtividade = newTextArea.val();
			uuid = newTextArea.attr('id');
			
			if(nomeAtividade){
				atividades.push({	idDemandaLista : idLista, 
								 	nome : nomeAtividade,
								 	uuid:  uuid,
								 	nomeDemandaLista: nomeLista
								});
			}
			
			if(nomeAtividade != undefined && nomeAtividade === "")
				$(this).remove();
		});
		
		if(atividades.length == 0){
	    	botaoSalvar.css("visibility", "hidden");
	    	botaoSalvar.parent().find(".cancelar-atividade").css("visibility", "hidden");
			return;
		}
		
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
		    		contListaPosterior = parseInt($(".contador[data-idDemandaLista="+idLista+"]").text()) + lista.length;
		    		$(".contador[data-idDemandaLista="+idLista+"]").text(contListaPosterior);
				}
			}
		});
	});
	

	$("#excluir-modal").click(function(e){
		e.stopPropagation();
		var _this = $(this);
		if(_this.attr("class").indexOf("loading") > -1) 
			return;		
		
		_this.addClass("loading");
    	var idAtividade = $("#idAtividade").val();
    	$.ajax({
		    url: "excluirAtividade?idAtividade=" + idAtividade, 
		    type: 'GET',
		    contentType : "application/json",
		    success: function() {
		    	_this.removeClass("loading");
		    	$(".ui.modal").modal("hide");
		    	removeContador(idAtividade);
		    	$(".card-atividade[data-idAtividade="+idAtividade+"]").remove();
		    	alertify.success("Atividade excluida com sucesso.");
		    }
		});
	});
	
	$("#salvar-modal").click(function(){
		var _nome = $("#header-modal-atividade").val();
    	var _descricao = ($(".description textarea").val() ? $(".description textarea").val() : $(".description p").text());
    	var idAtividade = $("#idAtividade").val();
    	var dataInicio = isCorrectDate("#dataInicio")? new Date(getDateValid($("#dataInicio").val())):$("#dataInicio").val();
    	var dataFim = isCorrectDate("#dataFim")? new Date(getDateValid($("#dataFim").val())):$("#dataFim").val();
    	var arrUsuariosAssociados = [];
		if(_nome === ""){
			alertify.error("Informe o nome da atividade para continuar");
			return;
		}
    	
		//Armazenar os usuarios associados
		$("#lstUsuarios").parent().find("a").each(function(){
			arrUsuariosAssociados.push({usuario : {id : $(this).attr("data-value")}, 
										idDemandaListaAtividade: idAtividade
									   });
		});
		
		var atividade = {id : idAtividade, 
						 nome: _nome,
						 descricao: _descricao,
						 dataInicioPrevisto: dataInicio,
						 dataFimPrevisto: dataFim,
						 lstAtividadeUsuario : arrUsuariosAssociados,
						 atividadeContinua: $("#atividadeContinua").val()};
		
		$.ajax({
		    url: "atualizarAtividade",
		    type: 'POST',
		    contentType : "application/json",
		    data: JSON.stringify(atividade),
		    success: function() {
				$(".ui.modal").modal("hide");
				alertify.success("Atividade salva com sucesso.");
	    		var atividade = $(".card-atividade[data-idAtividade= "+idAtividade+"]");
	    		atividade.find("label").text(_nome);
		    }
		});
	});
	
});

$("#salvar-modal-tarefa").click(function(){
	var idTarefa = $("#idTarefa").val();
	var nomeTarefa = $("#header-modal-tarefa").val();
	var tarefa = {id			  : idTarefa,
				 nome			  : nomeTarefa};
	
	$.ajax({
	    url: "updateTarefa",
	    type: 'POST',
	    contentType : "application/json",
	    data: JSON.stringify(tarefa),
	    success: function() {
	    	alertify.success("Tarefa salva com sucesso.");
	    	$("#associarUsuariotarefa").modal("hide");
	    }
	});
	
	
});




$(document).on("click", ".task > label", function(e){
	var idTarefa = $(this).closest(".task").attr("data-idTarefa")
	onclickTarefa(idTarefa);
});

$(document).on("click", ".remove-icon", function(e) {
	e.stopPropagation();
	var objCont = $(this).parent().find(".contador-task");
	var contador = $(this).parent().find(".contador-task").text();
	
	if(contador === '0') return;
	
	var idDemandaTarefa = objCont.closest(".task").attr("data-idTarefa");
	onClickUpdateContador(idDemandaTarefa, contador, "remove", objCont);
});


$(document).on("click", ".add-icon", function(e) {
	e.stopPropagation();
	var objCont = $(this).parent().find(".contador-task");
	var contador = objCont.text();
	var idDemandaTarefa = objCont.closest(".task").attr("data-idTarefa");
	onClickUpdateContador(idDemandaTarefa, contador, "add",objCont);
});

function onClickUpdateContador(idTarefa, contador, tipo,objCont){
	
	$.ajax({
	    url: "updateContador",
	    type: 'POST',
	    contentType : "application/json",
	    data: JSON.stringify({idTarefa: idTarefa, contador: contador, tipoOperacao : tipo}),
	    success: function(contAtual) {
	    	objCont.text(contAtual);
	    }
	});
}

$(document).on("keydown", "textarea", function() {
    while ($(this).prop("scrollHeight") > $(this).prop("offsetHeight")){
    	var rows = $(this).prop("rows");
    	rows += 1;
    	$(this).prop("rows", rows);	
    }
});

function getEvento(texto){
	return "".concat("<div class='event'> <div class='content'> <div class='summary'>",
				       texto, 
				    "</div></div></div>");
}

function removeContador(idAtividade){
	var atividade = $(".card-atividade[data-idAtividade= "+idAtividade+"]");
	var contListaAnterior = parseInt(atividade.closest(".ui.card").find(".floating.ui.label").text()) - 1;
	atividade.closest(".ui.card").find(".floating.ui.label").text(contListaAnterior);
}

function onclickAtividade(escopo){
	var id = $(escopo).attr("id");
	var nomeLista = $(escopo).closest(".lista-atividade").find("h4").text();
	
	if(nomeLista === "Fazer")
		$("#excluir-modal").css("visibility", "visible");
	else
		$("#excluir-modal").css("visibility", "hidden");
		
	$("#idAtividade").val(id);
	$("#atividadeModal")
			.modal({
			    onShow: function(){
			    	
					$.ajax({
					    url: "obterUsuarios",
					    type: 'GET',
					    contentType : "application/json",
					    success: function(lista) {
					    	loadTable("lstUsuarios", lista, getOptionComboUsuario);
					    	
					    	$.ajax({
							    url: "obterAtividade?idAtividade="+id,
							    type: 'GET',
							    contentType : "application/json",
							    success: function(data) {
							    	var selecionados = [];
							    	data.lstAtividadeUsuario.forEach(function(item){
							    		selecionados.push(""+item.usuario.id);
							    	});
							    	$("#lstUsuarios").dropdown('set selected',selecionados);
							    	
							    	if(selecionados.length == 0){
							    		$("#lstUsuarios").dropdown('set placeholder text',"Usuários");
							    	}
							    	$("#atividadeContinua").val(data.atividadeContinua);
							    	$("#atividadeContinua").prop("checked", data.atividadeContinua);
							    	
							    	$("#header-modal-atividade").val(data.nome);
							    	
							    	if(data.descricao)
							    		$(".description p").html(data.descricao);
							    	else{
							    		$(".description p").css("border", "1px solid");
							    		$(".description p").css("border-color", "rgb(169, 169, 169)");
							    		$(".description p").css("padding","15px");
							    	}
							    	
							    	if(data.dataInicioPrevisto)
							    		$("#dataInicio").val(customFormat(data.dataInicioPrevisto, "#DD#/#MM#/#YYYY#"));
							    	
						    		if(data.dataFimPrevisto)
						    			$("#dataFim").val(customFormat(data.dataFimPrevisto,"#DD#/#MM#/#YYYY#"));
						    		
							    	var eventos = "";
							    	
						    		eventos += getEvento("Status 'FAZER' em ".concat(customFormat(data.dataInclusao, "#DD#/#MM#/#YYYY#"), 
						    											   " por ", data.usuarioLogado.nome));
									
						    	 	if(data.dataInicio){
						    			eventos += getEvento("Status 'FAZENDO' em ".concat(customFormat(data.dataInicio, "#DD#/#MM#/#YYYY#")));
						    			$("#dataIni").val(customFormat(data.dataInicio, "#DD#/#MM#/#YYYY#"));
						    	 	}
						    		if(data.dataFinalizacao){
						    			eventos += getEvento("Status 'FEITO' em ".concat(customFormat(data.dataFinalizacao, "#DD#/#MM#/#YYYY#")));
						    			$("#dataFinazalicao").val(customFormat(data.dataFinalizacao, "#DD#/#MM#/#YYYY#"));
						    		}
						    		
							    	$("#informacoes-modal").append(eventos);
							    	
							    }
							});
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
					    	loadTable("lstListas .menu", lista, getLineListas);
					    }
					});
					
			    },
			    onHidden: function(){
			    	$("#lstUsuarios").dropdown('clear');
			    	
			    	if($(".description p")){
			    		$(".description p").text("");
			    	}
			    	$(".description p").css("border-color", "rgb(169, 169, 169)");
			    	
			    	if($(".description textarea"))
			    		$(".description textarea").replaceWith("<p></p>");
			    	
			    	clearAll("#atividadeModal");
			    	
			    	$("#informacoes-modal .event").remove();
			    }
		  	})
		  	.modal('show');
	
}

function onclickTarefa(id){
	$("#idTarefa").val(id);
	$("#associarUsuariotarefa")
	.modal({
	    onShow: function(){
	    	$.ajax({
			    url: "obterTarefa?idTarefa="+id,
			    type: 'GET',
			    contentType : "application/json",
			    success: function(data) {
			    	$("#header-modal-tarefa").val(data.nome);
			    }
	    	});
	 	},
		 onHidden: function(){
	    	$("#header-modal-tarefa").text("");
		 }
	}).modal('show');
}

function getLineListas(item){
	var linha="";
	linha = linha.concat("<div class='item' data-id='", item.id,"'>", item.nome ,"</div>")
	return linha;
}

function getOptionComboUsuario(item){
	return "<option value='".concat(item.id , "'>", item.nome , "</option>");
}

</script>
</html>