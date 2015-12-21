package br.com.ticketmaster.modelo;

public class Ticket {

	private long id_ticket;
	private float preco;
	private float preco_final;
	private Sessao sessao;
	// nao botei uma forma de salvar o 
	// tipo enum no banco de dados, temos que ver isso 
	//(que formato daria para salvar e tals, tenho que entender como vai funcionar o enum antes)
	private String tipo;
	
	public Ticket(){	
	}
	
	public Ticket(float preco, Sessao sessao, String tipo){
		this.setId_ticket(id_ticket);
		this.setPreco(preco);
		this.setSessao(sessao);
		this.setTipo(tipo);
		this.setPreco_final(this.calculaDesconto());
	}
	
	public Ticket(long id_ticket){
		this.setId_ticket(id_ticket);
		this.setPreco(preco);
		this.setSessao(sessao);
		this.setTipo(tipo);
	}
	
	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public float getPreco_final() {
		return preco_final;
	}

	public void setPreco_final(float preco_final) {
		this.preco_final = preco_final;
	}

	public long getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(long id_ticket) {
		this.id_ticket = id_ticket;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public float calculaDesconto(){
		float valor =  0;
		if(this.tipo.equals("ESTUDANTE")){
			valor = 5;
		} else if(this.tipo.equals("NORMAL")){
			valor = 10;
		} else if(this.tipo.equals("QUARTA")){
			valor = 7;
		} else if(this.tipo.equals("CRIANCA")){
			valor = 5;
		} else if(this.tipo.equals("DEFICIENTE")){
			valor = 6;
		}
		return this.preco*valor/10;
	}

}
