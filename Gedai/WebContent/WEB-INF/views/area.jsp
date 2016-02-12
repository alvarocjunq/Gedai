<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<body>

<c:import url="/WEB-INF/views/components/header.jsp" />
	
<div id="content" class="conteudo">
	<c:forEach items="${areas}" var="area">
		<div class="area hidden" 
			 data-idArea="${area.id}"
			 data-nome="${area.nome}" 
			 data-nivel="${area.nivel}" 
			 data-coluna="${area.coluna}">
		</div>
	</c:forEach>
	
	<div class="ui six column centered grid grid-areas"></div>
</div>

</body>

<script type="text/javascript">
$(document).ready(function(){
	
	var lstArea = [];
	var controle = true;
	var controleColuna2 = true;
	var controleColuna3 = true;
	var cont=0;
	var div = "";
	$(".area").each(function(){
		lstArea.push({nome	: $(this).attr("data-nome"),
					  nivel	: $(this).attr("data-nivel"),
					  coluna: $(this).attr("data-coluna"),
					  idArea: $(this).attr("data-idArea")});
	});
	for(var i=0, lenLista = lstArea.length;i < lenLista;i++){
		div = "";
		if(lstArea[i].nivel == '1'){
			div = div.concat("<div class='six column centered row nivel' data-nivel='", lstArea[i].nivel, "'>", 
							 "<div class='column card-area area-nivel-",lstArea[i].nivel,"' data-idArea='",lstArea[i].idArea,"'>", lstArea[i].nome, "</div>",
							 "</div>");
			$(".grid-areas").append(div);
			continue;
		}
		
		if(lstArea[i].nivel == '2'){
			if(controle) {
				div = div.concat("<div class='six column centered row nivel' data-nivel='", lstArea[i].nivel, "'>", 
								 "<div class='column card-area area-nivel-",lstArea[i].nivel,"' data-idArea='",lstArea[i].idArea,"'>", lstArea[i].nome, "</div>",
								 "</div>");
				$(".grid-areas").append(div);
				controle = false;
			}else{
				div = div.concat("<div class='column card-area area-nivel-",lstArea[i].nivel,"' data-idArea='",lstArea[i].idArea,"'>", lstArea[i].nome, "</div>");
				$(".nivel[data-nivel="+lstArea[i].nivel+"]").append(div);
			}
			continue;
		}
		
		if(lstArea[i].nivel == '3'){
			cont+=1;
			if(lstArea[i].coluna == '1'){
				div = div.concat("<div class='six column centered row nivel' data-nivel='", lstArea[i].nivel, "'>", 
								 "<div class='column card-area area-nivel-",lstArea[i].nivel,"' data-coluna='",lstArea[i].coluna,"' data-idArea='",lstArea[i].idArea,"'>", lstArea[i].nome, "</div>",
								 "<div class='column card-area visibility-false area-nivel-",lstArea[i].nivel,"' data-coluna='2", cont,"'></div>",
								 "<div class='column card-area visibility-false area-nivel-",lstArea[i].nivel,"' data-coluna='3", cont,"'></div>",
								 "</div>");
			}
			$(".grid-areas").append(div);

			if(lstArea[i].coluna == '2'){
				if(controleColuna2) cont=1;
				var obj = $(".nivel[data-nivel="+lstArea[i].nivel+"] .column[data-coluna=2"+cont+"]");
				
				if(obj.length > 0){
					obj.text(lstArea[i].nome);
					obj.attr("data-idArea", lstArea[i].idArea);
					obj.removeClass("visibility-false");
				}else{
					div = div.concat("<div class='six column centered row nivel' data-nivel='", lstArea[i].nivel, "'>", 
							 "<div class='column card-area visibility-false area-nivel-",lstArea[i].nivel,"' data-coluna='1'></div>",
							 "<div class='column card-area visibility-true area-nivel-",lstArea[i].nivel,"' data-coluna='2", cont,"' data-idArea='",lstArea[i].idArea,"'>", lstArea[i].nome ,"</div>",
							 "<div class='column card-area visibility-false area-nivel-",lstArea[i].nivel,"' data-coluna='3", cont,"'></div>", 
							 "</div>");
					$(".grid-areas").append(div);
				}
					
				controleColuna2 = false;
				continue;
			}
			
			if(lstArea[i].coluna == '3'){
				if(controleColuna3) cont=1;
				var obj = $(".nivel[data-nivel="+lstArea[i].nivel+"] .column[data-coluna=3"+cont+"]");
				if(obj.length > 0){
					obj.text(lstArea[i].nome);
					obj.attr("data-idArea", lstArea[i].idArea);
					obj.removeClass("visibility-false");
				}else{
					div = div.concat("<div class='six column centered row nivel' data-nivel='", lstArea[i].nivel, "'>", 
							 "<div class='column' data-coluna='1'></div>",
							 "<div class='column' data-coluna='2", cont,"'></div>",
							 "<div class='column' visibility-true data-coluna='3", cont,"' data-idArea='",lstArea[i].idArea,"'>", lstArea[i].nome ,"</div>", 
							 "</div>");
					$(".grid-areas").append(div);
				}
				controleColuna3 = false;
				continue;
			}
			
		}
	}
	
	$(".card-area").click(function(e){
		e.stopPropagation();
		go("demanda?idArea=".concat($(this).attr("data-idArea")));
	});
});
</script>
</html>