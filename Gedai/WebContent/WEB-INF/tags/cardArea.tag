 <%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>

<div class="column">
	<div class="ui fluid card">
	  <div class="card-area" data-idArea="${id}">
	    <h4>${label}</h4>
	  </div>
	</div>
</div>