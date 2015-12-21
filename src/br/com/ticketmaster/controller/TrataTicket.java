package br.com.ticketmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ticketmaster.dao.TicketDao;
import br.com.ticketmaster.modelo.Ticket;


public class TrataTicket extends Comando {
	@Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String acao = request.getParameter("acao");
        float preco = Float.parseFloat(request.getParameter("preco"));
        long id_sessao = 2;
        try{
        	if(request.getParameter("id_sessao") != null && !request.getParameter("id_sessao").isEmpty()){
        		id_sessao = Long.parseLong(request.getParameter("id_sessao"));
        	}
        } catch(NumberFormatException e) {
        	id_sessao = 2;
        }
        String tipo = String.valueOf(request.getParameter("tipo"));
        
        TicketDao dao = new TicketDao();
        
        if(id == 0){
        	Ticket ticket = new Ticket (preco, dao.getSessao(id_sessao), tipo);
            dao.adiciona(ticket);
          
		}else{
			Ticket ticket = new Ticket (id);
			if (acao != null && acao.equals("excluir")) {   
				dao.remove(ticket);
			}else{
				dao.altera(ticket);
				
			}
        	
        }
        
        response.sendRedirect("tela_ticket.jsp?resposta=ok");
        
	}
}