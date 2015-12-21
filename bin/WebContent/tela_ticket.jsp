<%-- 
    Document   : tela_cadastro
    Created on : Dec 9, 2015, 4:26:20 AM
    Author     : KOSMOS00
--%>
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

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                 <% String resposta = request.getParameter( "resposta" );
                        if (resposta != null && resposta.equals("ok")) {                       
                     %>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <i class="fa fa-info-circle"></i>  Sucesso no Cadastro!
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <div class="row">
	                    <div class="col-lg-6">
	                    <h3>Tickets</h3>
	                    <!-- general form elements -->
                          <div class="panel panel-primary">
                            <div class="panel-heading">
                                <a href="tela_cad_ticket.jsp?id=0"><button class="btn btn-default">Vender Ticket</button></a>
                            </div><!-- /.box-header -->
                            <div class="panel-body">
                                <!-- table start -->
		                        <div class="table-responsive">
 								<table class="table table-hover">
		                                <thead>
		                                    <tr>
		                                        <th>Evento</th>
		                                        <th>Preço</th>
		                                        <th>Tipo</th>
		                                        <th>Alteração</th>
		                                        <th>Exclusão</th>
		                                        
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                        <%
		                                        	
	                                                List<Ticket> list = new TicketDao().getLista();
		                                        	for(Ticket det: list){
	                                                	out.print("<tr>");
		                                                    out.print("<td>"+  det.getSessao().getData() +"</td>");
		                                                    out.print("<td>"+  det.getPreco() +"</td>");
		                                                    out.print("<td>" + det.getTipo() + "</td>");
		                                                    out.print("<td><a href='tela_cad_ticket.jsp?acao=alterar&id="+  det.getId_ticket()+"'><button class='btn btn-warning'>Alterar</button></a></td>");
		                                                    out.print("<td><a href='tela_cad_ticket.jsp?acao=excluir&id="+  det.getId_ticket()+"'><button class='btn btn-danger'>Excluir</button></a></td>");
		                                                    out.print("</form>");
	                                                    out.print("</tr>");
	                                                    
	                                                }
	                                             %>
		                                </tbody>
		                            </table>
		                        </div>
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


