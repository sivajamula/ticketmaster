package br.com.ticketmaster.modelo;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.EventoDao;

public class Evento {

	private long id_evento;
	private String categoria;
	private String nome;
	private Operador operador;

	public Evento(){
	}
	
	public Evento(String nome, String categoria){
		this.setNome(nome);
		this.setCategoria(categoria);
	}
	
	public Evento(String nome, String categoria, Operador operador){
		this.setNome(nome);
		this.setCategoria(categoria);
		this.setOperador(operador);
	}
	
	public Evento(long id_evento, String nome, String categoria, Operador operador){
		this.setNome(nome);
		this.setCategoria(categoria);
		this.setOperador(operador);
	}
	
	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public long getId_evento() {
		return id_evento;
	}

	public void setId_evento(long id_evento) {
		this.id_evento = id_evento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Funcao para pesquisar evento
	public Evento pesquisarEvento(long cod){
		EventoDao dao = new EventoDao();
		return dao.pesquisar(cod);
	}
	
	//Funcao para validar nome evento
	public boolean validaEvento(String nome){
		EventoDao dao = new EventoDao();
		if(dao.pesquisar(nome)!=null){
			return true;
		}
		else return false;
	}
	
	//funcao para excluir evento
	public void excluirEvento(long cod) throws SQLException{
		EventoDao dao = new EventoDao();
		dao.remove(pesquisarEvento(cod));
	}
	
	//funcao para listar os eventos
	public List<Evento> listarEventos(){
		EventoDao dao = new EventoDao();
		return dao.getLista();
	}
	
	//Faltou funcao vender ticket do diagrama que nao sei oque é

}
