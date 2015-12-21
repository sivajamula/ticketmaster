package br.com.ticketmaster.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.modelo.Evento;
import br.com.ticketmaster.modelo.Sala;
import br.com.ticketmaster.modelo.Sessao;

public class SessaoDao {
	private Connection connection;

	public SessaoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Sessao sessao) {
		String sql = "INSERT INTO Sessao(id_sessao, data, duracao, hora_inicio, ingressos_reservados, id_evento, id_sala) VALUES(s_sessao.nextval,?,?,?,?,?,?)";
		try {
			
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setDate(1, new Date(sessao.getData().getTime()));
			stmt.setDate(2, new Date(sessao.getDuracao().getTime()));
			stmt.setDate(3, new Date(sessao.getHora_inicio().getTime()));
			stmt.setInt(4, sessao.getIngressos_reservados());
			stmt.setLong(5, sessao.getEvento().getId_evento());
			stmt.setLong(6, sessao.getSala().getId_sala());
			// executar
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Sessao> getLista() {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Sessao");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Sessao sessao = new Sessao();
				sessao.setId_sessao(rs.getLong("id_sessao"));
				sessao.setData(rs.getDate("data"));	
				sessao.setDuracao(rs.getDate("duracao"));
				sessao.setHora_inicio(rs.getDate("hora_inicio"));
				sessao.setIngressos_reservados(rs.getInt("ingressos_reservados"));
				// crio uma instancia da operador dao e pesquiso o operador pelo
				// id, retornando um objeto
				EventoDao eventoDao = new EventoDao();
				sessao.setEvento(eventoDao.pesquisar(rs.getLong("id_evento")));
				SalaDao salaDao = new SalaDao();
				sessao.setSala(salaDao.pesquisar(rs.getLong("id_sala")));
				sessoes.add(sessao);
				
			}
			rs.close();
			stmt.close();
			return sessoes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Evento getEvento(long id){
		EventoDao daoEve = new EventoDao();
		return  daoEve.pesquisar(id);
	}
	
	public Sala getSala(long id){
		SalaDao daoSal = new SalaDao();
		return  daoSal.pesquisar(id);
	}

	public Sessao pesquisar(long id) {
		Sessao sessao = new Sessao();
		PreparedStatement stmt;
		try {
			
			stmt = this.connection.prepareStatement("SELECT * FROM Sessao WHERE id_sessao=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			sessao.setId_sessao(rs.getLong("id_sessao"));
			sessao.setData(rs.getDate("data"));	
			sessao.setDuracao(rs.getDate("duracao"));
			sessao.setHora_inicio(rs.getDate("hora_inicio"));
			sessao.setIngressos_reservados(rs.getInt("ingressos_reservados"));
			// crio uma instancia da operador dao e pesquiso o operador pelo
			// id, retornando um objeto
			EventoDao eventoDao = new EventoDao();
			sessao.setEvento(eventoDao.pesquisar(rs.getLong("id_evento")));
			SalaDao salaDao = new SalaDao();
			sessao.setSala(salaDao.pesquisar(rs.getLong("id_sala")));
			rs.close();
			stmt.close();
			return sessao;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Sessao sessao) {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("UPDATE Sessao set data=?, duracao=?, hora_inicio=?, ingressos_reservados=?,"
					+ "id_evento=?, id_sala=? WHERE id_sessao = ?");
	
			stmt.setString(1, "s_sessao.nextval");
			stmt.setDate(1, new Date(sessao.getData().getTime()));
			stmt.setDate(2, new Date(sessao.getDuracao().getTime()));
			stmt.setDate(3, new Date(sessao.getHora_inicio().getTime()));
			stmt.setInt(4, sessao.getIngressos_reservados());
			stmt.setLong(5, sessao.getEvento().getId_evento());
			stmt.setLong(6, sessao.getSala().getId_sala());
			stmt.setLong(7, sessao.getId_sessao());
			stmt.executeQuery();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Sessao sessao) throws SQLException {
		
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Sessao WHERE id_sessao = ?");
		stmt.setLong(1, sessao.getId_sessao());
		stmt.execute();
		stmt.close();
		
	}
}
