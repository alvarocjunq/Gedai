$.ajaxSetup({ cache: false });

function isContentRequired(escopo){
	escopo = (escopo == undefined ? "" : escopo + " ");
		
	clearRequired(escopo);
	var isValid = true;
	
	$(escopo + ".req > input").each(function(){
		if(!$(this).val()){
			isValid = false;
			setRequired($(this));
		}
	});
	
	$(".req > textarea").each(function(){
		if(!$(this).val()){
			isValid = false;
			setRequired($(this));
		}
	});
	
	$(".req > select").each(function(){
		if($(this).val() === "999"){
			isValid = false;
			setRequired($(this));
		}
	});
	
	return isValid;
}

function clearAll(container){
	$(container+" input:text").each(function(){
		$(this).val("");
	});
	
	$(container + "select").each(function(){
		$(this).children("option:selected").removeAttr("selected").end()
			   .children("option:first").attr("selected", "selected");
	});
}

function getOptionCombo(value, text){
	return "<option value='".concat(value, "'>", text, "</option>");
}


function loading(ligado){
	if(ligado){
		$("body").append("<div class='ui loader large' id='loader'></div>");
		$(".conteudo").addClass("ui blurring segment dimmable dimmed");
		$(".conteudo").append("<div class='ui simple dimmer' id='dimmerLoader'></div>");
		$("#loader").addClass("active");
	}
	else{
		$("#loader").remove();
		$("#dimmerLoader").remove();
		$("#conteudo").dimmer('hide');
	}
}

function loadingInline(id, ligado){
	if(ligado){
		$("#".concat(id)).removeClass("hidden");
		$("#".concat(id)).addClass("show");
	}else{
		$("#".concat(id)).removeClass("show");
		$("#".concat(id)).addClass("hidden");
	}
}

function go(value){
	loading(true);
	if(value.indexOf("#") > -1)
		$(value).submit();
	else
		window.location = value;
}

function isEmailValid(email){
	var validar = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!validar.test(email))
		return false;
	return true;
}

function getDateValid(value){
	var arr = value.split("/");
	if(arr.length == 3){
		return arr[1] + "/" + arr[0] + "/" + arr[2];
	}
	return null;
}

function getValidYear(value){
	var arr = value.split("/");
	if(arr.length == 3){
		var ano = parseInt(arr[2]);	
		if(ano <= 99){
			if(ano >= 80)
				return arr[0] + "/" + arr[1] + "/19" + ano;
			return arr[0] + "/" + arr[1] + "/20" + ano;
		}
	}
	return value;
}

function enableSort(){
	var sort = document.getElementsByClassName("sort");
	for(var i=0, len=sort.length; i<len; i++){
		sort[i].innerHTML = sort[i].innerText +  "<span class='glyphicon glyphicon-arrow-down icon-sort' aria-hidden='true'></span>";
	}
}

function loadTable(tbody, lista, fLine){
	var lines = "";
	
    lista.forEach(function(item){
    	lines += fLine(item);
    });
    $("#"+tbody).html(lines);
}

function removeSortClicked(){
	var sort = document.getElementsByClassName("sort");
	for(var i=0, len=sort.length; i<len; i++){
		sort[i].classList.remove("clicked");
	}
}

/**
 * Funcao para ordenar as linhas de uma tabela
 * 
 * @param clicked - ID do item clicado
 * @param lista - Lista a ser ornenada
 * @param lst - ID da tbody que vai carregar a lista
 * @param fLine - Nome da função a ser excutada para obter as linhas
 * 
 */
function orderBy(clicked, lista, lst, fLine){
	removeSortClicked();
	
	if($("#"+clicked).hasClass("sort")){
		$(lst).html("");
		$("#"+clicked).addClass("clicked");
	}	
	
	var nomeCampo = $("#"+clicked).attr("id");
	nomeCampo = nomeCampo.substring(5, nomeCampo.length);
	
	lista.sort(function(a, b) { 
	        if (b[nomeCampo] > a[nomeCampo]) return -1; 
			if (b[nomeCampo] < a[nomeCampo]) return 1; 
			return 0; 
	});
	
	loadTable(lst, lista, fLine);
}

function onClickLineModal(tbody, id){
	$(tbody.concat(" tr")).each(function(){
		$(this).removeClass('redLine');
		if($(this).children().html() === id){
			$(this).addClass('redLine');
			return;
		}
	});
}


function hasInformation(escopo){
	var hasValue = false;
	$(escopo + " input[type=text]").each(function(){
		if($(this).val() !== "")
			hasValue = true;
    });
	return hasValue;
}


function guid() {
    function s4() {
	    return Math.floor((1 + Math.random()) * 0x10000)
	      .toString(16)
	      .substring(1);
	}
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
	    s4() + '-' + s4() + s4() + s4();
}

$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null)
       return null;
    return results[1] || 0;
}

