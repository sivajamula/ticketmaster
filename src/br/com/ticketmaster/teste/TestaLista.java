package br.com.ticketmaster.teste;

import java.util.List;

import br.com.ticketmaster.dao.ContatoDao;
import br.com.ticketmaster.dao.OperadorDao;
import br.com.ticketmaster.modelo.Contato;
import br.com.ticketmaster.modelo.Operador;

public class TestaLista {

	public static void main(String[] args) {
		OperadorDao dao = new OperadorDao();
		List<Operador> operadores = dao.getLista();
		for (Operador operador : operadores) {
			System.out.println("Id: " + operador.getId_operador());
			System.out.println("Nome: " + operador.getNome());
			System.out.println("Data nascimento: " + operador.getData_nasc().getTime() + "\n\n");
		}

		//Contato contato = dao.pesquisar(2);
		//System.out.println("Id: " + contato.getId());
	    //System.out.println("Nome: " + contato.getNome());
		//System.out.println("Email: " + contato.getEmail());
		//System.out.println("Endereco: " + contato.getEndereco());
		//System.out.println("Data nascimento: " + contato.getDataNascimento().getTime() + "\n\n");
	}

}
