package br.com.ticketmaster.testaDao;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.SessaoDao;
import br.com.ticketmaster.modelo.Sessao;

public class TestaSessao {
	public static void main(String[] args) throws SQLException {
		SessaoDao dao = new SessaoDao(); 

	
	{
		//testa insere
		Date date = new Date();
		Sessao sessao = new Sessao(date,date,date, 10, dao.getEvento(1), dao.getSala(4));		
		dao.adiciona(sessao);
		System.out.println("Sessao Adicionado");
		
	}	
	
	{
		//testa lista
		List<Sessao> sessoes = dao.getLista();
		for (Sessao sessao : sessoes) {
			System.out.println("Id: " + sessao.getId_sessao());
			System.out.println("Data: " + sessao.getData());
			System.out.println("Duracao: " + sessao.getDuracao());
			System.out.println("Hora Inicio: " + sessao.getHora_inicio());
			System.out.println("Ingressos reservados: " + sessao.getIngressos_reservados());
			System.out.println("Id evento: " + sessao.getEvento().getId_evento());
			System.out.println("Id sala: " + sessao.getSala().getId_sala());
		}
	}
	
	{
		//testa pesquisar
		Sessao sessao = dao.pesquisar(2);
		System.out.println("Id: " + sessao.getId_sessao());
		System.out.println("Data: " + sessao.getData());
		System.out.println("Duracao: " + sessao.getDuracao());
		System.out.println("Hora Inicio: " + sessao.getHora_inicio());
		System.out.println("Ingressos reservados: " + sessao.getIngressos_reservados());
		System.out.println("Id evento: " + sessao.getEvento().getId_evento());
		System.out.println("Id sala: " + sessao.getSala().getId_sala());
	}
		
	{
		//testa update
		Sessao sessao = new Sessao();
		sessao = dao.pesquisar(3); 
		sessao.setIngressos_reservados(5);
		dao.altera(sessao);
		sessao = dao.pesquisar(sessao.getId_sessao());
		System.out.println("Categoria: " + sessao.getIngressos_reservados());
	}
	
	{
		//testa delete
		Sessao sessao = dao.pesquisar(1);
		System.out.println("Categoria: " + sessao.getIngressos_reservados());
		dao.remove(sessao);
	}
	
	}
}
