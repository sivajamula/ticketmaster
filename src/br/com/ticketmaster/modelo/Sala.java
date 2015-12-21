package br.com.ticketmaster.modelo;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.SalaDao;

public class Sala {

	private long id_sala;
	private int numero_fileiras;
	private int numero_lugares;
	private int lotacao_max;
	private String tipo;
	private String descricao;
	private Endereco endereco;
	private SalaDao dao = new SalaDao();

	public Sala(){
		
	}
	
	public Sala(long id_sala, int numero_fileiras, int numero_lugares, int lotacao_max,	String tipo, String descricao,Endereco endereco){
		this.setId_sala(id_sala);
		this.setNumero_fileiras(numero_fileiras);
		this.setNumero_lugares(numero_lugares);
		this.setLotacao_max(lotacao_max);
		this.setTipo(tipo);
		this.setDescricao(descricao);
		this.setEndereco(endereco);
	}
	
	public Sala(int numero_fileiras, int numero_lugares, int lotacao_max, String tipo, String descricao,Endereco endereco){
		this.setNumero_fileiras(numero_fileiras);
		this.setNumero_lugares(numero_lugares);
		this.setLotacao_max(lotacao_max);
		this.setTipo(tipo);
		this.setDescricao(descricao);
		this.setEndereco(endereco);
	}
	
	public long getId_sala() {
		return id_sala;
	}

	public void setId_sala(long id_sala) {
		this.id_sala = id_sala;
	}

	public int getNumero_fileiras() {
		return numero_fileiras;
	}

	public void setNumero_fileiras(int numero_fileiras) {
		this.numero_fileiras = numero_fileiras;
	}

	public int getNumero_lugares() {
		return numero_lugares;
	}

	public void setNumero_lugares(int numero_lugares) {
		this.numero_lugares = numero_lugares;
	}

	public int getLotacao_max() {
		return lotacao_max;
	}

	public void setLotacao_max(int lotacao_max) {
		this.lotacao_max = lotacao_max;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Sala pesquisarSala(long cod){
		return dao.pesquisar(cod);
	}
	
	public void excluirSala(long cod) throws SQLException{
		dao.remove(pesquisarSala(cod));
	}
	
	public List<Sala> listarSala(){
		return dao.getLista();
	}

}
