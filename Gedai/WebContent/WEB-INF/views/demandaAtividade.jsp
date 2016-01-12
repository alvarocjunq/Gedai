<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gedai"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />

<body>

<c:import url="/WEB-INF/views/components/header.jsp" />
	
<div id="content">
	<div class="ui large horizontal divided list">
		<div class="item">
			<div class="ui card">
				<div class="content lista-atividade">
					<h4>A Fazer</h4>
					<div class="card-atividade" >
						atividade 1
					</div>
					<div class="content footer-atividade">
					  Nova atividade <i class="plus square outline icon"></i>
			    	</div>
		    	</div>
	    	</div>
		</div>
	</div>
		
</div>

</body>
</html>