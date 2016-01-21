 <%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="descricao" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="progress" required="true" %>

  <div class="card card-demanda" data-idDemanda="${id}">
    <div class="content">
      <img class="right floated mini ui image mini-image-card" src="resources/img/sabre-de-luz.png">
      <div class="header">
        ${label}
      </div>
<!--       <div class="meta"> -->
<!--         Friends of Veronika -->
<!--       </div> -->
      <div class="description">
        ${descricao}
      </div>
    </div>
    <div class="extra content">
	    <div class="ui progress green card-demanda-pos" id="${id}">
			<div class="bar progress-demanda" style="width: ${progress}%;">
		  		<div class="progress">${progress}%</div>
		  	</div>
		</div>
    </div>
  </div>