 <%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="progress" required="true" %>

<div class="column">
	<div class="ui fluid card">
	  <div class="card-demanda" data-idDemanda="${id}">
	    <h4>${label}</h4>
	  </div>
	  <div class="content">
	    <div class="ui progress green card-demanda-pos" id="${id}">
			<div class="bar progress-demanda" style="width: ${progress}%;">
		  		<div class="progress">${progress}%</div>
		  	</div>
		</div>
       </div>
	</div>
</div>