package br.com.ticketmaster.testaDao;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.EnderecoDao;
import br.com.ticketmaster.modelo.Endereco;

public class TestaEndereco {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		EnderecoDao dao = new EnderecoDao();
		
		{
			//testa insere
			Endereco endereco = new Endereco("Praia de Belas", "", "RS", "Porto Alegre", 456, "Borges de Medeiros");		
			dao.adiciona(endereco);
			System.out.println("Endereço Adicionado");
			
		}	
		
		{
			//testa lista
			List<Endereco> enderecos = dao.getLista();
			for (Endereco endereco : enderecos) {
				System.out.println("Id: " + endereco.getId_endereco());
				System.out.println("Rua: " + endereco.getRua());
				System.out.println("Numero: " + endereco.getNumero());
				System.out.println("Bairro: " + endereco.getBairro());
				System.out.println("Cidade: " + endereco.getCidade());
				System.out.println("Complemento: " + endereco.getComplemento());
				System.out.println("Rua: " + endereco.getUF());
			}
		}
		
		{
			//testa 
			Endereco endereco = dao.pesquisar(1);
			System.out.println("Id: " + endereco.getId_endereco());
			System.out.println("Rua: " + endereco.getRua());
			System.out.println("Numero: " + endereco.getNumero());
			System.out.println("Bairro: " + endereco.getBairro());
			System.out.println("Cidade: " + endereco.getCidade());
			System.out.println("Complemento: " + endereco.getComplemento());
			System.out.println("Rua: " + endereco.getUF());
		}
				
		{
			Endereco endereco = new Endereco();
			endereco = dao.pesquisar(11);
			endereco.setComplemento("alterando");
			dao.altera(endereco);
			endereco = dao.pesquisar(endereco.getId_endereco());
			System.out.println("Complemento: " + endereco.getComplemento());
		}
		
		{
			Endereco endereco = dao.pesquisar(4);
			dao.remove(endereco);
		}
		
	}

}
