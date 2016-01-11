<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<c:import url="/WEB-INF/views/components/imports.jsp" />
 <style type="text/css">
    body > .grid {
      height: 100%;
    }
    img.image{float: left !important;}
    .column {
      max-width: 450px;,
    }
  </style>
<body>
<script type="text/javascript">
$(document).ready(function(){
	
	if($("#codMsgem").val() === "-1")
		alertify.error($("#mensagemRetorno").val());
	
    $('.ui.form').form({
        fields: {
        	usuario: {
            identifier : 'usuario',
            rules: [{
                type   : 'empty',
                prompt : 'Informe o usuário'
            }]},
            senha: {
            identifier : 'senha',
            rules: [{
                type   : 'empty',
                prompt : 'Informe a senha'
            }]}
        }
      });
	
	$("#btnLogin").click(function(e){
		e.stopPropagation();
		if(hasInformation("#conteudo")){
			go("#frmLogin");
		}
	});
	
	$(".campo-login").keypress(function(e){
	    if(e.which == 13) {
	    	if(hasInformation("#conteudo")){
	    		go("#frmLogin");
	    	}
	    }
	});
});

</script>

<input type="hidden"  id="codMsgem" value="${codMsgem}"/>
<input type="hidden"  id="mensagemRetorno" value="${mensagemRetorno}"/>
<div class="ui middle aligned center aligned grid" id="conteudo">
  <div class="column row padding-top-150">
  <div class="row">
    <h4 class="ui red image header">
      <img src="resources/img/sabre-de-luz.png" class="image">
<!--       <div class="content"> -->
        Gedai - Gerenciador de Demandas e Atividades Internas
<!--       </div> -->
    </h4>
    <form class="ui large form" method="post" id="frmLogin" action="efetuarLogin">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="usuario" placeholder="Usuário">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="senha" placeholder="Senha">
          </div>
        </div>
        <div class="ui fluid large red button" id="btnLogin">Login</div>
      </div>

      <div class="ui error message"></div>

    </form>

    <div class="ui message">
      N&atilde;o possui usu&aacute;rio? <a href="mailto:p000ajunqueira@prservicos.com.br">Solicite</a>
    </div>
  </div>
  </div> 
</div>
</body>
</html>
