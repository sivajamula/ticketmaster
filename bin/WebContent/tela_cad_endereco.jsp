<%-- 
    Document   : tela_cadastro
    Created on : Dec 9, 2015, 4:26:20 AM
    Author     : KOSMOS00
--%>

<%@page import="br.com.ticketmaster.dao.SalaDao"%>
<%@page import="br.com.ticketmaster.modelo.Sala"%>
<%@page import="br.com.ticketmaster.dao.EnderecoDao"%>
<%@page import="br.com.ticketmaster.modelo.Endereco"%>
<%@page import="br.com.ticketmaster.dao.EventoDao"%>
<%@page import="br.com.ticketmaster.modelo.Evento"%>
<%@page import="br.com.ticketmaster.dao.SessaoDao"%>
<%@page import="br.com.ticketmaster.modelo.Sessao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>TicketMaster</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- DateTimePicker CSS -->
    <link href="bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/ticketCSS.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
        <div id="wrapper">
        
            <%@ include file="/menu.jsp" %>
			<%
				int id_endereco = Integer.parseInt(request.getParameter("id_endereco"));
				String acao = request.getParameter("acao");
				String rua = "";
				String bairro = "";
				String complemento = "";
				String UF = "";
				String cidade = "";
				int numero = 0;
				
				if( id_endereco != 0){
					Endereco ender = new Endereco();
					rua = ender.pesquisarEndereco(id_endereco).getRua();
					bairro = ender.pesquisarEndereco(id_endereco).getBairro();
					complemento = ender.pesquisarEndereco(id_endereco).getComplemento();
					UF = ender.pesquisarEndereco(id_endereco).getUF();
					cidade = ender.pesquisarEndereco(id_endereco).getCidade();
					numero = ender.pesquisarEndereco(id_endereco).getNumero();
				}
			%>
			
            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                 <% if (acao != null && acao.equals("excluir")) {  %>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <i class="fa fa-info-circle"></i>  Tem certeza que deseja excluir esse registro?
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <div class="row">
                    
                        <!-- Painel Cadastro Endereço -->
                        <div class="col-md-6">
                          <!-- general form elements -->
                          <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Cadastro de Endereço</h3>
                            </div><!-- /.box-header -->
                            <div class="panel-body">
                                <!-- form start -->
                                <form role="form" action="FrontController" method="post">
                                   <div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="rua">Rua</label>
                                          <input type="text" class="form-control" value="<%= rua %>" name="rua" id="rua" placeholder="Digite a rua">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="numero">Numero</label>
                                          <input type="text" class="form-control" value="<%= numero %>"name="numero" id="numero" placeholder="Digite o numero">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="complemento">Complemento</label>
                                          <input type="text" class="form-control" value="<%= complemento %>" name="complemento" id="complemento" placeholder="Digite o complemento">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="bairro">Bairro</label>
                                          <input type="text" class="form-control"  value="<%= bairro %>" name="bairro" id="bairro" placeholder="Digite o bairro">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="cidade">Cidade</label>
                                          <input type="text" class="form-control"  value="<%= cidade %>" name="cidade" id="cidade" placeholder="Digite a cidade">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="estado">Estado</label>
                                          <input type="text" class="form-control"  value="<%= UF %>"  name="estado" id="estado" placeholder="Digite o estado">
                                        </div>
                                    </div>
                                       
                                       
                                   </div><!-- /.box-body -->
                                  <div class="col-md-12">
                                  	<input type="hidden" name="id_endereco" value="<%= id_endereco %>">
                                  	<input type="hidden" name="acao" value="<%= acao %>">
                                    <input type="hidden" name="servlet" value="cad_endereco">
                                    <% if (acao != null && acao.equals("excluir")) {  %>
					                    <button type="submit" class="btn btn-danger">Excluir</button>
				                    <% }else{ %>
				                    	<button type="submit" class="btn btn-primary">Salvar</button>
                                    <% } %>
                                  </div>
                                </form>
                            </div>
                          </div><!-- /.box -->
                        </div>
                       
                       </div>
            	</div>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="bootstrap/js/jquery-1.11.3.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        
        <!-- Moment JS -->
        <script src="/bootstrap/js/moment-with-locales.js"></script>
        
        <!-- DateTimePicker JavaScript -->
        <script src="/bootstrap/js/bootstrap-datetimepicker.js"></script>

        <!-- Menu Toggle Script -->
        <script>
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });
        </script>

    </body>
</html>


