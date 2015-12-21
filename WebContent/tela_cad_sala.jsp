<%-- 
    Document   : tela_cadastro
    Created on : Dec 9, 2015, 4:26:20 AM
    Author     : KOSMOS00
--%>

<%@page import="br.com.ticketmaster.dao.SalaDao"%>
<%@page import="br.com.ticketmaster.modelo.Sala"%>
<%@page import="br.com.ticketmaster.dao.EnderecoDao"%>
<%@page import="br.com.ticketmaster.modelo.Endereco"%>
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
				 int id = Integer.parseInt(request.getParameter("id"));
				 String acao = request.getParameter("acao");
				 int numero_fileiras = 0;
				 int numero_lugares = 0;
				 int lotacao_max = 0;
				 String tipo = "";
				 String descricao = "";
				 long id_ender = 0;
				 
				if( id != 0 ){
					Endereco ender = new Endereco();
					Sala sala = new Sala();
					numero_fileiras = sala.pesquisarSala(id).getNumero_fileiras();
					numero_lugares = sala.pesquisarSala(id).getNumero_lugares();
					lotacao_max = sala.pesquisarSala(id).getNumero_fileiras();
					tipo = sala.pesquisarSala(id).getTipo();
					descricao = sala.pesquisarSala(id).getDescricao();
					id_ender = sala.pesquisarSala(id).getEndereco().getId_endereco();
					
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
						<!-- Painel Cadastro Sala -->
                        <div class="col-md-6">
                          <!-- general form elements -->
                          <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Cadastro de Sala</h3>
                            </div><!-- /.box-header -->
                            <div class="panel-body">
                                <!-- form start -->
                                <form role="form" action="FrontController" method="post">
                                   <div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="numero_fileiras">Numero Fileiras</label>
                                          <input type="text" class="form-control" value="<%= numero_fileiras %>" name="numero_fileiras" id="numero_fileiras" placeholder="Digite a fileira">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="numero_lugares">Numero Lugares</label>
                                          <input type="text" class="form-control" value="<%= numero_lugares %>" name="numero_lugares" id="numero_lugares" placeholder="Digite os lugares">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="lotacao_max">lotacao_max</label>
                                          <input type="text" class="form-control" value="<%= lotacao_max %>" name="lotacao_max" id="lotacao_max" placeholder="Digite a lotação máxima">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="tipo">tipo</label>
                                          <input type="text" class="form-control" value="<%= tipo %>" name="tipo" id="tipo" placeholder="Digite o tipo">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="descricao">descricao</label>
                                          <input type="text" class="form-control" value="<%= descricao %>" name="descricao" id="descricao" placeholder="Digite a descricao">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="sala_ender">Lista de Endereços</label>
                                          <select class="form-control" id="sala_ender" name="sala_ender">
                                              <option value="0">Nenhum endereço selecionado</option>
                                              <%
                                              	List<Endereco> list = new EnderecoDao().getLista(); 
                                                for(Endereco det: list){
                                                	if(id_ender == det.getId_endereco()){
                                                		out.print("<option value="+  det.getId_endereco() +" selected >");
                                                        	out.print( det.getRua());
                                                    	out.print("</option>");
                                                	}else{
	                                                	out.print("<option value="+  det.getId_endereco() +">");
	                                                        out.print( det.getRua());
	                                                    out.print("</option>");
                                                	}
                                                }
                                                %>
                                          </select>
                                        </div>
                                    </div>
                                       
                                       
                                   </div><!-- /.box-body -->
                                  <div class="col-md-12">
                                    <input type="hidden" name="id" value="<%= id %>">
                                  	<input type="hidden" name="acao" value="<%= acao %>">
                                    <input type="hidden" name="servlet" value="cad_sala">
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
                        <!-- /.Fim Painel Cadastro Sala -->
                            
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
