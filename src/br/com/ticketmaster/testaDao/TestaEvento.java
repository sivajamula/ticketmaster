package br.com.ticketmaster.testaDao;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.EventoDao;
import br.com.ticketmaster.modelo.Evento;


public class TestaEvento {
	public static void main(String[] args) throws SQLException {
	EventoDao dao = new EventoDao(); 
	
	{
		//testa insere
		Evento evento = new Evento("Teste", "Testando", dao.getOperador(1));		
		dao.adiciona(evento);
		System.out.println("Evento Adicionado");
		
	}	
	
	
	{
		//testa lista
		List<Evento> eventos = dao.getLista();
		for (Evento evento : eventos) {
			System.out.println("Id: " + evento.getId_evento());
			System.out.println("Nome: " + evento.getNome());
			System.out.println("Categoria: " + evento.getCategoria());
			System.out.println("Id do Endereco: " + evento.getOperador().getId_operador());
		}
	}
	
	{
		//testa 
		Evento evento = dao.pesquisar(1);
		System.out.println("Id: " + evento.getId_evento());
		System.out.println("Nome: " + evento.getNome());
		System.out.println("Categoria: " + evento.getCategoria());
		System.out.println("Id do Endereco: " + evento.getOperador().getId_operador());
	}
		
	{
		Evento evento = new Evento();
		evento = dao.pesquisar(1);
		evento.setCategoria("Texto");
		dao.altera(evento);
		evento = dao.pesquisar(evento.getId_evento());
		System.out.println("Categoria: " + evento.getCategoria());
	}
	
	{
		Evento evento = dao.pesquisar(3);
		System.out.println("Categoria: " + evento.getCategoria());
		dao.remove(evento);
	}
	
	}
}
