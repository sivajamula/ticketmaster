package br.com.ticketmaster.testaDao;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.SalaDao;
import br.com.ticketmaster.modelo.Sala;

public class TestaSala {
	public static void main(String[] args) throws SQLException {
	SalaDao dao = new SalaDao(); 
	
	{
		//testa insere
		Sala sala = new Sala(12, 8, 120, "3D", "Cinema", dao.getEndereco(1));		
		dao.adiciona(sala);
		System.out.println("Sala Adicionada");
		
	}	
	
	
	{
		//testa lista
		List<Sala> salas = dao.getLista();
		for (Sala sala : salas) {
			System.out.println("Id: " + sala.getId_sala());
			System.out.println("Descricao: " + sala.getDescricao());
			System.out.println("Tipo: " + sala.getTipo());
			System.out.println("Numero de fileiras: " + sala.getNumero_fileiras());
			System.out.println("Numero de lugares: " + sala.getNumero_lugares());
			System.out.println("Numero : " + sala.getLotacao_max());
			System.out.println("Id do Endereco: " + sala.getEndereco().getId_endereco());
		}
	}
	
	{
		//testa 
		Sala sala = dao.pesquisar(1);
		System.out.println("Id: " + sala.getId_sala());
		System.out.println("Descricao: " + sala.getDescricao());
		System.out.println("Tipo: " + sala.getTipo());
		System.out.println("Numero de fileiras: " + sala.getNumero_fileiras());
		System.out.println("Numero de lugares: " + sala.getNumero_lugares());
		System.out.println("Numero : " + sala.getLotacao_max());
		System.out.println("Id do Endereco: " + sala.getEndereco().getId_endereco());
	}
		
	{
		Sala sala = new Sala();
		sala = dao.pesquisar(2);
		sala.setDescricao("teste");
		dao.altera(sala);
		sala = dao.pesquisar(sala.getId_sala());
		System.out.println("Complemento: " + sala.getDescricao());
	}
	
	{
		Sala sala = dao.pesquisar(3);
		System.out.println("Complemento: " + sala.getId_sala());
		dao.remove(sala);
	}
	
	}
}
