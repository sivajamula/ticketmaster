<!-- Sidebar -->
    <div id="sidebar-wrapper">
       <ul class="sidebar-nav">
           <li class="sidebar-brand">
               <a href="#">
                   Ticket Master
               </a>
               
           </li>
           <li>
           	 <a>Bem vindo, ${sessionScope.nome}</a> 
           	</li>
           <li>
               <a href="javascript:;" data-toggle="collapse" data-target="#operador"> Tela Operador </a>
               <ul id="operador" class="collapse">
                   <li>
		               <a href="tela_ticket.jsp">Ver Tickets</a>
		           </li>
		           
               </ul>
           </li>
           <li>
               <a href="javascript:;" data-toggle="collapse" data-target="#demo"> Tela Cadastro </a>
               <ul id="demo" class="collapse">
                   
		           <li>
		               <a href="tela_endereco.jsp">Cadastro Endereço</a>
		           </li>
		           <li>
		               <a href="tela_sala.jsp">Cadastro Sala</a>
		           </li>
		           <li>
		               <a href="tela_evento.jsp">Cadastro Evento</a>
		           </li>
		           <li>
		               <a href="tela_sessao.jsp">Cadastro Sessão</a>
		           </li>
               </ul>
           </li>
           <li>
           		<form role="form" action="FrontController" method="post">
	           		<input type="hidden" name="servlet" value="deslogar">
	           		<button type="submit" class="btn btn-default">Deslogar</button>
           		</form>
           </li>
           
           
       </ul>
   	</div>
   
<!-- /#sidebar-wrapper -->