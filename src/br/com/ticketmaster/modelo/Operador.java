package br.com.ticketmaster.modelo;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import br.com.ticketmaster.dao.OperadorDao;

public class Operador {
	private long id_operador;
	private String nome;
	private Calendar data_nasc;
	
	public Operador() {	
	}
	
	/* Construtor que recebe o nome e a data */
	public Operador(String nome, Date data){
		
		this.setNome(nome);
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(data.getTime());
		this.setData_nasc(date);
		
	}
	
	public Operador(int id_operador, String nome, Date data) {
		this.setId_operador(id_operador);
		this.setNome(nome);
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(data.getTime());
		this.setData_nasc(date);
	}

	public long getId_operador() {
		return id_operador;
	}

	public void setId_operador(long id_operador) {
		this.id_operador = id_operador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Calendar data_nasc) {
		this.data_nasc = data_nasc;
	}
	
	/*implementar funcao para logar */
	public void logar(String nome){//boolean logar(){
		if (validaNome(nome)){
			System.out.println("Logado");
		}
		else {
			System.out.println("Usuario invalido");
		}
	}
	
	/*implementar funcao para pesquisar usuario pelo nome */
	public Operador pesquisarUsuario(String nome){
		OperadorDao dao = new OperadorDao();
		return dao.pesquisar(nome);
	}
	
	/*valida se o usuario existe ou nao*/
	public boolean validaNome (String nome){//boolean validaNome(String nome){
		OperadorDao dao = new OperadorDao();
		if(dao.pesquisar(nome)!=null){
			return true;
		}
		else return false;
	}
	
	/*Função que consulta o banco e retorna lista de operadores. */
	public List<Operador> listarUsuarios(){
		OperadorDao dao = new OperadorDao();
		return dao.getLista();
	};
	
	/* Função que altera usuario no banco */
	public void editarUsuario (Operador operador){
		
		OperadorDao dao = new OperadorDao();
		dao.altera(operador);
		
	}
	
	/* Função que adiciona operador ao banco */
	public void cadastraUsuario (Operador operador){
		
		OperadorDao dao = new OperadorDao();
		dao.adiciona(operador);
		
	}

}
