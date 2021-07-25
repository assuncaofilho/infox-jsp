<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
    
<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  
  <jsp:include page="theme-loader.jsp"></jsp:include>
  
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <jsp:include page="navbar.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                  
                  <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      
                      <jsp:include page="page-header.jsp"></jsp:include>
                      
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                              
                                           <div class="row">
                                            <div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                <div class="card">
                                                    <div class="card-block">
                                                        <h4 class="sub-title">Cad. Cliente</h4>
		                                              
          												 <form class="form-material" action="<%= request.getContextPath() %>/ServletClienteController" method="post" id="formCli" accept-charset="utf-8">
          												 
          												 	<input type="hidden" name="acao" id="acao" value="">
          												 
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${cliente.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID:</label>
                                                            </div>
                                                            
                                                              <div class="form-group form-default form-static-label">
                                                                <input type="text" name="nome" id="nome" class="form-control" required="required" value="${cliente.nome}" >
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome:</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="end" id="end" class="form-control" required="required" autocomplete="off" value="${cliente.end}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Endere�o</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="fone" id="fone" class="form-control" required="required" autocomplete="off" value="${cliente.fone}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Telefone:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" id="email" class="form-control" required="required" autocomplete="off" value="${cliente.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Email:</label>
                                                            </div>
                                                            
                                                           <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparNovoCliente();">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
												            <button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDeleteComAjax();">Excluir</button>
												            <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
                                                        </form> 
                                                   
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                
                                                <span id="msg">${msg}</span>
                                                
                                                
                                          <div style="height: 300px; overflow: scroll;">
											<table class="table" id="clitab">
												<thead>
													<tr>
														<th scope="col">ID_CLI</th>
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${allCli}" var="cli">
													<tr>
													<td><c:out value="${cli.id}"></c:out></td>
													<td><c:out value="${cli.nome}"></c:out></td>
													<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServletClienteController?acao=buscareditar&id=${cli.id}">Detalhes</a></td>
													</tr>
													</c:forEach>


												</tbody>
											</table>
										</div>
                                                
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
   
<jsp:include page="javascripfile.jsp"></jsp:include>

<!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Cliente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    <div class="modal-body">

		<div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="Nome" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
		  <div class="input-group-append">
		    <button class="btn btn-success" type="button" onclick="buscaClienteAjax();">Buscar</button>
		  </div>
	</div>
	<div style="height: 300px; overflow: scroll; ">
		<table class="table" id="tbcliResult">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Nome</th>
		      <th scope="col" id="ver">Ver</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		  
		  </tbody>
		</table>
	</div>
	</div>
	 <span id="msgResult"></span>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
     
    </div>
  </div>
</div>

<script type="text/javascript">

function verEditar(id){
	
	var urlAction = document.getElementById("formCli").action;
	
	window.location.href = urlAction + "?acao=buscareditar&id="+id; // usa method GET para redirecionar! Retorna com a resposta da ServletClienteController
}

function criarDelete(){
	
	if(confirm("Deseja realmente exluir este cliente?")){
		document.getElementById("formCli").method = 'get';
		document.getElementById("acao").value = 'deletar';
		document.getElementById("formCli").submit();
	}
}

function criarDeleteComAjax(){
	
	var idCliente = document.getElementById("id").value;
	
	if(idCliente != null && idCliente != "" && idCliente.trim() != "" ){
	
	if(confirm("Deseja realmente excluir este cliente?")){
		
		var urlAction = document.getElementById("formCli").action;
		var idUser = document.getElementById("id").value;
		
		$.ajax({
			
			method: "get",
			url: urlAction,
			data: "id=" + idUser + "&acao=deletarajax",
			success: function (response) {
				limparForm();
				document.getElementById("msg").textContent = "";
				alert(response);
			}		
		
			}).fail(function(xhr, status, errorThrown){
				alert("Erro ao excluir usu�rio por ID: " + xhr.responseText);
		});
		
		
		}
		
	}else{
		alert("Busque um cliente para ser exclu�do!");
	}
}
	


function limparForm(){
	var elementos = document.getElementById("formCli").elements;
	
	for (p = 0; p < elementos.length; p++){
		elementos[p].value = '';
	}
		
}

function limparNovoCliente(){
	var elementos = document.getElementById("formCli").elements;
	
	for (p = 0; p < elementos.length; p++){
		elementos[p].value = "";
	}
	document.getElementById("msg").textContent = "";
}

function buscaClienteAjax(){
	
	var nomeBusca = document.getElementById("nomeBusca").value;
	
	var urlAction = document.getElementById("formCli").action;
	
	if (nomeBusca != null && nomeBusca != "" && nomeBusca.trim() != ""){
		
			$.ajax({
			
					method: "get",
					url: urlAction,
					data: "nomeBusca=" + nomeBusca + "&acao=buscarajax",
					success: function (response) {
						
					//alert(response);
						
					var json = JSON.parse(response);
					
					//console.info(json);
					
					//alert(json[0].nome);
						
					document.getElementById("msgResult").textContent = ""+json.length+" resultado(s) para esta busca.";
						
						$('#tbcliResult > tbody > tr').remove();
						
						for( var p = 0; p < json.length; p++){
							
							$('#tbcliResult > tbody').append('<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td><td><button type="button" class="btn btn-info" onclick="verEditar('+json[p].id+');">Detalhes</button></td></tr>');
							
						}
						
						
						
					}		
				
					}).fail(function(xhr, status, errorThrown){
						alert("Erro ao buscar usu�rio por nome: " + xhr.responseText);
				});
		
	}
}


</script>


</body>

</html>
    