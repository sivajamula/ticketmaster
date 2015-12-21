<%-- 
    Document   : tela_cadastro
    Created on : Dec 9, 2015, 4:26:20 AM
    Author     : KOSMOS00
--%>

<%@page import="br.com.ticketmaster.dao.SessaoDao"%>
<%@page import="br.com.ticketmaster.modelo.Sessao"%>
<%@page import="br.com.ticketmaster.dao.TicketDao"%>
<%@page import="br.com.ticketmaster.modelo.Ticket"%>
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
				  float preco = 0;
				  float preco_final = 0;
				  long id_sessao = 0;
				if( id != 0 ){
					Ticket ticket = new Ticket();
					
					//pesquisar ticket não existe
					//id_sessao = ticket.pesquisarTicket(id).getSessao().getId_sessao();
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
                                <h3 class="panel-title">Vender Ticket</h3>
                            </div><!-- /.box-header -->
                            <div class="panel-body">
                                <!-- form start -->
                                <form role="form" action="FrontController" method="post">
                                   <div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                          <label for="preco">Preço</label>
                                          <input type="text" class="form-control" value="<%= preco %>" name="preco" id="preco" placeholder="Digite o preço">
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="sessao">Lista de Sessões</label>
                                          <select class="form-control" id="sessao" name="sessao">
                                              <option value="0">Nenhum endereço selecionado</option>
                                              <%
                                              	List<Sessao> list = new SessaoDao().getLista(); 
                                                for(Sessao det: list){
                                                	if(id_sessao == det.getId_sessao()){
                                                		out.print("<option value="+  det.getId_sessao() +" selected >");
                                                        	out.print( det.getEvento().getNome());
                                                    	out.print("</option>");
                                                	}else{
	                                                	out.print("<option value="+  det.getId_sessao() +">");
	                                                        out.print( det.getEvento().getNome());
	                                                    out.print("</option>");
                                                	}
                                                }
                                                %>
                                          </select>
                                        </div>
                                    </div>
                                       
                                       
                                    <div class="col-md-6">
                                        <div class="form-group">
                                          <label for="tipo">Tipos</label>
                                          <select class="form-control" id="tipo" name="tipo">
                                              <option value="0">Nenhum tipo selecionado</option>
                                              <%
                                              	String tipos[] = { "ESTUDANTE", "DEFICIENTE", "CRIANCA", "QUARTA", "NORMAL" };
                                        		
                                        		for (String tp: tipos){
                                        			//if(tp.equals(ticket.getTipo())){
                                        			//}
                                        			out.print("<option value="+ tp +">");
                                        			out.print("</option>");
                                        		}
                                              	
                                                %>
                                          </select>
                                        </div>
                                    </div>
                                       
                                   </div><!-- /.box-body -->
                                  <div class="col-md-12">
                                    <input type="hidden" name="id" value="<%= id %>">
                                  	<input type="hidden" name="acao" value="<%= acao %>">
                                    <input type="hidden" name="servlet" value="cad_ticket">
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
                        <!-- /.Fim Painel Cadastro Ticket -->
                            
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
