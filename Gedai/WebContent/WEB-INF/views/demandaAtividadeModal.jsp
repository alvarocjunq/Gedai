<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="ui long modal">
	<i class="close icon"></i>
	
	<div class="header" id="header-modal-atividade"></div>
	
	<div class="image content">
		<div class="image">
			<i class="file text outline icon"></i>
		</div>
		<div class="description">
			<p></p>
		</div>
	</div>
		
	<div class="actions">
		
		<div class="ui compact selection dropdown" id="lstListas">
		  <i class="caret up icon"></i>
		  <div class="text">Mover para...</div>
		  <div class="menu"></div>
		</div>

		<div class="ui inverted buttons">
			<div class="ui green button" id="salvar-modal">
				<i class="checkmark icon"></i> Salvar
			</div>
		</div>
	</div>
</div>