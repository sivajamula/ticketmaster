package br.com.ticketmaster.modelo;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.ticketmaster.dao.SessaoDao;

public class Sessao {

	private long id_sessao;
	private Date data;
	private Date duracao;
	private Date hora_inicio;
	private int ingressos_reservados;
	private Evento evento;
	private Sala sala;
	private SessaoDao dao = new SessaoDao();
	
	public Sessao (){
		
	}
	
	public Sessao(long id_sessao, Date data, Date duracao, Date hora_inicio,
			int ingressos_reservados, Evento evento, Sala sala){
		this.setId_sessao(id_sessao);
		this.data = data;
		this.setDuracao(duracao);
		this.setHora_inicio(hora_inicio);
		this.setIngressos_reservados(ingressos_reservados);
		this.setEvento(evento);
		this.setSala(sala);
	}
	
	public Sessao(Date data, Date duracao, Date hora_inicio,
			int ingressos_reservados, Evento evento, Sala sala){
		this.setId_sessao(id_sessao);
		this.data = data;
		this.setDuracao(duracao);
		this.setHora_inicio(hora_inicio);
		this.setIngressos_reservados(ingressos_reservados);
		this.setEvento(evento);
		this.setSala(sala);
	}

	public long getId_sessao() {
		return id_sessao;
	}

	public void setId_sessao(long id_sessao) {
		this.id_sessao = id_sessao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDuracao() {
		return duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public Date getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public int getIngressos_reservados() {
		return ingressos_reservados;
	}

	public void setIngressos_reservados(int ingressos_reservados) {
		this.ingressos_reservados = ingressos_reservados;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
    //??
	public void reservarIngressos(){
		
	}
	
	public Sessao pesquisarSessao(long cod){
		return dao.pesquisar(cod);
	}
	
	public void excluirSessao(long cod) throws SQLException{
		dao.remove(dao.pesquisar(cod));
	}
	
	public List<Sessao> listarSessoes(){
		return dao.getLista();
	}
	
	public void cancelarVenda(){
		
	}
	
	public void escolherEvento(Evento evento){
		
	}

}
