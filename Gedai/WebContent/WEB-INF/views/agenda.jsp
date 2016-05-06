<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<body>

<c:import url="/WEB-INF/views/components/header.jsp">
	<c:param name="visual" value="clean" />
</c:import>
	
	
<input type="hidden" id="atividade-fazendo" value="${usuario.idAtividadeFazendo}" />
<input type="hidden" value="" id="idAtividade"/>
<div id="content" class="conteudo" style="padding-top: 40px !important; margin: 0 !important;">
	<div class="ui middle aligned divided list" id="agenda-left">
	  	<c:forEach items="${atividades}" var="ativ">
			<div class="item item-lista ${ativ.demandaZebrada}" data-idAtividade="${ativ.id}" 
										 data-nomeAtividade="${ativ.nome}" 
										 data-idDemandaLista="${ativ.idDemandaLista}"
										 data-atividadeContinua="${ativ.atividadeContinua}">
				<div class="right floated content">
					<button class="ui icon button iniciar-button">
					  <i class="play icon"></i>
					</button>
					<button class="ui icon button pausar-button">
					  <i class="pause icon"></i>
					</button>
				</div>
				<div class="content middle-atividade">
					<b>${ativ.demanda}</b> - <span class="nome-atividade">${ativ.nome}</span>
				</div>
			</div>
		</c:forEach>
		<hr class="line-separator">
		<hr class="line-separator">
	  	<c:forEach items="${atividadesContinuas}" var="ativ">
			<div class="item item-lista ${ativ.demandaZebrada}" data-idAtividade="${ativ.id}" 
										 data-nomeAtividade="${ativ.nome}" 
										 data-idDemandaLista="${ativ.idDemandaLista}"
										 data-atividadeContinua="${ativ.atividadeContinua}">
				<div class="right floated content">
					<button class="ui icon button iniciar-button">
					  <i class="play icon"></i>
					</button>
					<button class="ui icon button pausar-button">
					  <i class="pause icon"></i>
					</button>
				</div>
				<div class="content middle-atividade">
					<b>${ativ.demanda}</b> - <span class="nome-atividade">${ativ.nome}</span>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="agenda-right">
		<h3>Atividade atual</h3>
		<div class="text-right">
			<button class="ui red button" id="finalizar-atividade">Finalizar</button>
		</div>
		<h5 class="text-center" id="nome-fazendo">${fazendo.nome}</h5>
		<p class="textarea-fazendo" id="descricao">${fazendo.descricao}</p>
	</div>
</div>
<c:import url="demandaAtividadeModal.jsp" />
</body>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#dataInicio").mask("99/99/9999",{placeholder:"dd/mm/aaaa"});
	$("#dataFim").mask("99/99/9999",{placeholder:"dd/mm/aaaa"});
	
	if($("#atividade-fazendo").val()){
		openDetail();
		setLineActive();
	}else
		closeDetail();
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
    		var atividade = $(".item[data-idAtividade= "+idAtividade+"]");
    		atividade.find(".nome-atividade").text(_nome);
	    }
	});
});


function onclickAtividade(escopo){
var id = $(escopo).attr("data-idAtividade");
var nomeLista = $(escopo).closest(".lista-atividade").find("h4").text();

$("#lstListas").css("display", "none");
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
					    	 	}
					    	 	
					    		if(data.dataFinalizacao){
					    			eventos += getEvento("Status 'FEITO' em ".concat(customFormat(data.dataFinalizacao, "#DD#/#MM#/#YYYY#")));
					    		}
					    		
						    	$("#informacoes-modal").append(eventos);
						    	
						    }
						});
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

function openDetail(){
	$("#agenda-left").css("width","70%");
	$("#agenda-right").css("display","block");
}

function setLineActive(){
	$(".item").removeClass("atividade-fazendo");
	$(".item .iniciar-button").attr("disabled", false);
	$(".item[data-idAtividade="+$("#atividade-fazendo").val()+"]").addClass("atividade-fazendo");
	$(".item[data-idAtividade="+$("#atividade-fazendo").val()+"] .iniciar-button").attr("disabled", true);
}

function closeDetail(){
	$("#agenda-left").css("width","100%");
	$("#agenda-right").css("display","none");
}

function getOptionComboUsuario(item){
	return "<option value='".concat(item.id , "'>", item.nome , "</option>");
}

function getEvento(texto){
	return "".concat("<div class='event'> <div class='content'> <div class='summary'>",
				       texto, 
				    "</div></div></div>");
}

$(document).on("click", ".item.item-lista", function(e) {
	e.stopPropagation();
	onclickAtividade($(this));
});

$(document).on("click", ".iniciar-button", function(e) {
	e.stopPropagation();
	var idAtividade = $(this).closest(".item").attr("data-idAtividade");
	$("#nome-fazendo").removeClass("animated").removeClass("swing");
	$(this).parent().find(".stop-button").attr("disabled", false);
	
	$("#atividade-fazendo").val(idAtividade);
	setLineActive();
	
	$.ajax({
	    url: "iniciarAtividade?idAtividade="+idAtividade,
	    type: 'GET',
	    contentType : "application/json",
	    success: function(atividade) {
	    	if($("#nome-fazendo").text() === ""){
	    		openDetail();
	    	}
	    	
	    	$("#nome-fazendo").addClass("animated swing");
	    	
	    	$("#nome-fazendo").text(atividade.nome);
	    	$("#descricao").html(atividade.descricao);
	    }
	});
});

$(document).on("click", ".pausar-button", function(e) {
	e.stopPropagation();
	var idAtividade = $(this).closest(".item").attr("data-idAtividade");
	$("#nome-fazendo").text("");
	$(".item").removeClass("atividade-fazendo");
	$(".item .iniciar-button").attr("disabled", false);
	$(this).parent().find(".stop-button").attr("disabled", true);
	$.ajax({
	    url: "pausarAtividade?idAtividade="+idAtividade,
	    type: 'GET',
	    contentType : "application/json",
	    success: function(retorno) {
	    	closeDetail();
	    }
	});
});

$(document).on("click", "#finalizar-atividade", function(e) {
	e.stopPropagation();
	var idAtividade = $("#atividade-fazendo").val();
	var item = $(".item[data-idAtividade="+idAtividade+"]");
	
	if(item.attr("data-atividadeContinua") === "true")
		finalizarAtividadeContinua(idAtividade);	
	else
		finalizarAtividade(idAtividade);
});

function finalizarAtividadeContinua(idAtividade){
	var item = $(".item[data-idAtividade="+idAtividade+"]");
	
	var nomeAtividade = item.attr("data-nomeAtividade");
	var idDemandaLista = item.attr("data-idDemandaLista");
	
	
	$("#nome-fazendo").text("");
	$(".item").removeClass("atividade-fazendo");
	$(".item .iniciar-button").attr("disabled", false);
	
	var atividade = {id : idAtividade, 
			 		 nome: nomeAtividade,
			 		 idDemandaLista: idDemandaLista};
	
	
	$.ajax({
	    url: "pararAtividade",
	    type: 'POST',
	    contentType : "application/json",
	    data: JSON.stringify(atividade),
	    success: function(idAtividade) {
	    	item.attr("data-idAtividade", idAtividade);
	    	alertify.success("Atividade finalizada.");
	    	closeDetail();
	    }
	});
}

function finalizarAtividade(idAtividade){
	$.ajax({
	    url: "finalizarAtividade?idAtividade="+idAtividade,
	    type: 'GET',
	    contentType : "application/json",
	    success: function(retorno) {
	    	closeDetail();
	    	$("#nome-fazendo").text("");
	    	$(".item[data-idAtividade="+idAtividade+"]").remove();
	    	alertify.success("Atividade finalizada.");
	    }
	});
}

</script>
</html>