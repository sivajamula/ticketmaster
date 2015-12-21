package br.com.ticketmaster.modelo;

import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.EnderecoDao;

public class Endereco {

	private long id_endereco;
	private String bairro;
	private String complemento;
	private String UF;
	private String cidade;
	private int numero;
	private String rua;

	public Endereco(){
		
	}
	
	public Endereco(long id_endereco, String bairro, String complemento, 
			String UF, String cidade, int numero, String rua){
		this.setId_endereco(id_endereco);
		this.setBairro(bairro);
		this.setComplemento(complemento);
		this.setUF(UF);
		this.setCidade(cidade);
		this.setNumero(numero);
		this.setRua(rua);
	}
	
	public Endereco(String bairro, String complemento, 
			String UF, String cidade, int numero, String rua){
		this.setBairro(bairro);
		this.setComplemento(complemento);
		this.setUF(UF);
		this.setCidade(cidade);
		this.setNumero(numero);
		this.setRua(rua);
	}
	
	public long getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(long id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public Endereco pesquisarEndereco(long cod){
		EnderecoDao dao = new EnderecoDao();
		return dao.pesquisar(cod);
	}
	
	public void excluirEndereco(Endereco endereco) throws SQLException{
		EnderecoDao dao = new EnderecoDao();
		dao.remove(endereco);		
	}
	
	public List<Endereco> listaEnderecos(){
		EnderecoDao dao = new EnderecoDao();
		return dao.getLista();
	}

}
