package br.com.ticketmaster.testaDao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.ticketmaster.dao.OperadorDao;
import br.com.ticketmaster.modelo.Operador;

public class TestaOperador {

	
	public static void main(String[] args) throws SQLException {
		
		OperadorDao dao = new OperadorDao();
		
		{
			//testa insere
			Operador operador = new Operador();
			operador.setNome("teste");
			operador.setData_nasc(Calendar.getInstance());
		
			dao.adiciona(operador);
			System.out.println("Contato Adicionado");
			
		}	
		
		{
			//testa lista
			List<Operador> operadores = dao.getLista();
			for (Operador operador : operadores) {
				System.out.println("Id: " + operador.getId_operador());
				System.out.println("Nome: " + operador.getNome());
				System.out.println("Data nascimento: " + operador.getData_nasc().getTime() + "\n\n");
			}
			
		}
		
		{
			//testa 
			Operador operador = dao.pesquisar(1);
			System.out.println("Id: " + operador.getId_operador());
			System.out.println("Nome: " + operador.getNome());
			System.out.println("Data nascimento: " + operador.getData_nasc().getTime() + "\n\n");
		
		}
				
		{
			Operador operador = dao.pesquisar("teste");
			System.out.println("Id: " + operador.getId_operador());
			System.out.println("Nome: " + operador.getNome());
			System.out.println("Data nascimento: " + operador.getData_nasc().getTime() + "\n\n");
		}
		
		{
			Operador operador = dao.pesquisar("teste");
			operador.setNome("testando");
			dao.altera(operador);
			operador = dao.pesquisar(operador.getId_operador());
			System.out.println("Nome: " + operador.getNome());
		}
		
		{
			dao.remove(dao.pesquisar("testando"));
		}
		
	}	
	
}
