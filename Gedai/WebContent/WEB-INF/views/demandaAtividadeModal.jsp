<div class="ui long modal">

	<i class="close icon"></i>
	<div class="header header-modal" >
		<div class="field">
			<input type="text" autofocus="autofocus" id="header-modal-atividade"/> 
		</div>
	</div>
	
	<div class="content">
		<div class="description">
			<p></p>
		</div>
		
		<div class="field">
			<label class="theme-default">Associar usu&aacute;rios</label>
			<select class="ui fluid search dropdown" multiple="" id="lstUsuarios">
			  <option value="">Usu&aacute;rios</option>
			</select>
			<div class="ui small feed" id="informacoes-modal">
				<h4 class="ui header theme-default">Informa&ccedil;&otilde;es</h4>
			</div>
		</div>
	</div>
		
	<div class="actions">
		<div class="ui compact selection dropdown up" id="lstListas">
		  <i class="caret up icon"></i>
		  <div class="text">Mover para...</div>
		  <div class="menu"></div>
		</div>
		<button class="ui red button" id="excluir-modal">Excluir</button>
		<div class="ui buttons">
			<div class="ui green button" id="salvar-modal">
				<i class="checkmark icon"></i> Salvar
			</div>
		</div>
	</div>
</div>
