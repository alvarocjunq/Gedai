<div class="ui long modal" id="atividadeModal">
	<i class="close icon"></i>
	<div class="header header-modal" >
			<div class="field">
				<input type="text" autofocus="autofocus" id="header-modal-atividade"/> 
			</div>
	</div>
	
	<div class="content">
		<form class="ui form">
			<div class="ui four column grid">
				<div class="column field">
					<label class="theme-default" for="dataInicio">Data In&iacute;cio Previsto</label>
					<input type="text" id="dataInicio" value="" />
				</div>
				<div class="column field">
					<label class="theme-default" for="dataFim">Data Fim Previsto</label>
					<input type="text" id="dataFim" value="" />
				</div>
				
				<div class="column field"></div>
				
				<div class="column field">
					<div class="ui toggle checkbox">
						<input type="checkbox" name="atividadeContinuaValue" id="atividadeContinua" value="" />
						<label class="theme-default">Atividade Cont&iacute;nua?</label>
					</div>
				</div>
			</div>
		</form>
	
		<div class="description">
			<div class="field">
				<label class="theme-default">Descri&ccedil;&atilde;o</label>
			</div>
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
		<button class="ui red button" id="excluir-modal">Excluir</button>
		<div class="ui buttons">
			<div class="ui green button" id="salvar-modal">
				<i class="checkmark icon"></i> Salvar
			</div>
		</div>
	</div>
</div>
