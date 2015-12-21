package br.com.ticketmaster.testaDao;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.TicketDao;
import br.com.ticketmaster.modelo.Ticket;

public class TestaTicket {
	public static void main(String[] args) throws SQLException {
		TicketDao dao = new TicketDao(); 
		
		{
			//testa insere
			Ticket ticket = new Ticket(20, dao.getSessao(3), "NORMAL");	
			dao.adiciona(ticket);
			System.out.println("Ticket Adicionado");
			
		}	
		
		{
			//testa lista
			List<Ticket> tickets = dao.getLista();
			for (Ticket ticket : tickets) {
				System.out.println("Id: " + ticket.getId_ticket());
				System.out.println("Preco: " + ticket.getPreco());
				System.out.println("Preco Final: " + ticket.getPreco_final());
				System.out.println("Id Sessao: " + ticket.getSessao().getId_sessao());
				System.out.println("Tipo" + ticket.getTipo().toString());
			}
		}
		/*
		{
			//testa 
			Ticket ticket = dao.pesquisar(1);
			System.out.println("Id: " + ticket.getId_ticket());
			System.out.println("Preco: " + ticket.getPreco());
			System.out.println("Preco Final: " + ticket.getPreco_final());
			System.out.println("Id Sessao: " + ticket.getSessao().getId_sessao());
			System.out.println("Tipo" + ticket.getTipo().toString());
		}
			
		{
			Ticket ticket = new Ticket();
			ticket = dao.pesquisar(1);
			ticket.setPreco(25);
			dao.altera(ticket);
			ticket = dao.pesquisar(ticket.getId_ticket());
			System.out.println("Complemento: " + ticket.getPreco());
		}
		
		{
			Ticket ticket = dao.pesquisar(4);
			System.out.println("Complemento: " + ticket.getId_ticket());
			dao.remove(ticket);
		}
		*/
		}
}
