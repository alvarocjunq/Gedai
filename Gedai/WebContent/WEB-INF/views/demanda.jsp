<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

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
		
		go("atividade?idDemanda=".concat($(this).attr("data-idDemanda"), 
					 "&nome=", $(this).find("h4").text()));
	});
});
</script>

<body>

<c:import url="/WEB-INF/views/components/header.jsp" />
	
<div id="content" class="conteudo">
	<div class="ui three column grid">
		<c:forEach items="${demandas}" var="demanda">
		
			<div class="status-demanda" id="${demanda.id}">
				<c:forEach items="${demanda.lstProgressoRacional}" var="status">
					<div>
						<label data-nome>${status.nome}</label> 
						<label data-qtd>${status.qtd}</label>
					</div>
				</c:forEach>
			</div>
			
		    <gedai:cardDemanda progress="${demanda.progresso}" label="${demanda.nome}" id="${demanda.id}" />
		</c:forEach>
	</div>
</div>

</body>
</html>