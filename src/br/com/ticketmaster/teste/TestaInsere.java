package br.com.ticketmaster.teste;

import java.util.Calendar;

import br.com.ticketmaster.dao.ContatoDao;
import br.com.ticketmaster.dao.OperadorDao;
import br.com.ticketmaster.modelo.Contato;
import br.com.ticketmaster.modelo.Operador;

public class TestaInsere {

	public static void main(String[] args) {
		Operador operador = new Operador();
		operador.setNome("Fernanda");
		operador.setData_nasc(Calendar.getInstance());
		OperadorDao dao = new OperadorDao();
		dao.adiciona(operador);
		System.out.println("Contato Adicionado");
	}
}
