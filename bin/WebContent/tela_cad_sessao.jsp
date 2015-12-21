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
<%@page import="java.util.Date"%>
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
				
				Date date = new Date();
				Date data = date;
				Date duracao = date;
				Date hora_inicio = date;
				int ingressos_reservados = 0;
				long id_evento = 0;
				long id_sala = 0;
				 
				if( id != 0 ){
					Sessao sessao = new Sessao();
					data = sessao.pesquisarSessao(id).getData();
					duracao = sessao.pesquisarSessao(id).getDuracao();
					hora_inicio = sessao.pesquisarSessao(id).getHora_inicio();
					ingressos_reservados = sessao.pesquisarSessao(id).getIngressos_reservados();
					id_evento = sessao.pesquisarSessao(id).getEvento().getId_evento();
					id_sala = sessao.pesquisarSessao(id).getSala().getId_sala();
					
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
                    
                        
                         <!-- Painel Cadastro Sessao -->
                        <div class="col-md-6">
                          <!-- general form elements -->
                          <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">Cadastro de Sessao</h3>
                            </div><!-- /.box-header -->
                            <div class="panel-body">
                                <!-- form start -->
                                <form role="form" action="FrontController" method="post">
                                   <div>
	                                    <div class='col-sm-6'>
								            <div class="form-group">
								            	<label for="data_hora">Data e Hora de Início</label>
								                <div class='input-group date' id='datetimepicker1'>
								                    <input type='text' name="data_hora" class="form-control" />
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
								            </div>
								        </div>
								        <script type="text/javascript">
								            $(function () {
								                $('#datetimepicker1').datetimepicker();
								            });
								        </script>
	                                    <div class="col-md-12">
	                                        <div class="form-group">
	                                          <label for="duracao">Duração</label>
	                                          <input type="text" class="form-control" value="<%= duracao %>" name="duracao" id="duracao" placeholder="Digite a Duração do Evento">
	                                        </div>
	                                    </div>
	                                    <div class="col-md-12">
	                                        <div class="form-group">
	                                          <label for="ingressos_reservados">ingressos_reservados</label>
	                                          <input type="text" class="form-control" value="<%= ingressos_reservados %>" name="ingressos_reservados" id="ingressos_reservados" placeholder="Digite ingressos_reservados">
	                                        </div>
	                                    </div>
	                                    <div class="col-md-12">
	                                    	<div class="form-group">
		                                    	<label for="evento">Evento</label>
		                                          <select class="form-control" id="evento" name="evento">
		                                              <option value="0">Nenhum evento selecionado</option>
		                                              <%
		                                              	List<Evento> list = new EventoDao().getLista(); 
		                                                for(Evento det: list){
		                                                	if(id_evento == det.getId_evento()){
		                                                		out.print("<option value="+  det.getId_evento() +" selected >");
		                                                        	out.print( det.getNome());
		                                                    	out.print("</option>");
		                                                	}else{
			                                                	out.print("<option value="+  det.getId_evento() +">");
			                                                        out.print( det.getNome());
			                                                    out.print("</option>");
		                                                	}
		                                                }
		                                                %>
		                                          </select>
	                                          </div>
                                         </div>
                                         <div class="col-md-12">
                                         	<div class="form-group">
                                    		  <label for="sala">Sala</label>
	                                          <select class="form-control" id="sala_ender" name="sala_ender">
                                              <option value="0">Nenhuma sala selecionado</option>
                                              <%
                                              	List<Sala> list2 = new SalaDao().getLista(); 
                                                for(Sala det: list2){
                                                	if(id_sala == det.getId_sala()){
                                                		out.print("<option value="+  det.getId_sala() +" selected >");
                                                        	out.print( det.getDescricao());
                                                    	out.print("</option>");
                                                	}else{
	                                                	out.print("<option value="+  det.getId_sala() +">");
	                                                        out.print( det.getDescricao());
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
                                    <input type="hidden" name="servlet" value="cad_sessao">
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
                        <!-- /.Painel Cadastro Sessao -->

    
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
