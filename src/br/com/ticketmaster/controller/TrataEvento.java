package br.com.ticketmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ticketmaster.dao.EventoDao;
import br.com.ticketmaster.modelo.Evento;
import br.com.ticketmaster.modelo.Operador;
import br.com.ticketmaster.modelo.Sala;

public class TrataEvento extends Comando {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
			// TODO Auto-generated method stub
		
			int id = Integer.parseInt(request.getParameter("id"));
        	String acao = request.getParameter("acao");
	        String nome_evento = request.getParameter("nome");
	        String categoria = request.getParameter("categoria");
	        
	        HttpSession ses = request.getSession();
	        Operador user = (Operador) ses.getAttribute("identifica"); 
	        	        
	        EventoDao dao = new EventoDao ();

	        if(id == 0){
	        	Evento evento = new Evento ( nome_evento,  categoria,  user );
	            dao.adiciona(evento);
	        	
			}else{
				Evento evento = new Evento (id, nome_evento,  categoria,  user );
				
				if (acao != null && acao.equals("excluir")) {   
					dao.remove(evento);
				}else{
					dao.altera(evento);
					
				}
	        	
	        }
	        
	        response.sendRedirect("tela_evento.jsp?resposta=ok");
	        
		
	}

}
