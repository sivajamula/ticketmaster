package br.com.ticketmaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.modelo.Evento;
import br.com.ticketmaster.modelo.Operador;

public class EventoDao {
	private Connection connection;

	public EventoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Evento evento) {
		String sql = "INSERT INTO Evento(id_evento, categoria, nome, id_operador) VALUES(s_evento.nextval,?,?,?)";
		try {
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setString(1, evento.getCategoria());
			stmt.setString(2, evento.getNome());
			stmt.setLong(3, evento.getOperador().getId_operador());
			// executar
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Evento> getLista() {
		List<Evento> eventos = new ArrayList<Evento>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Evento");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setId_evento(rs.getLong("id_evento"));
				evento.setCategoria(rs.getString("categoria"));
				evento.setNome(rs.getString("nome"));
				// crio uma instancia da operador dao e pesquiso o operador pelo
				// id, retornando um objeto
				OperadorDao operadorDao = new OperadorDao();
				evento.setOperador(operadorDao.pesquisar(rs.getLong("id_operador")));
				eventos.add(evento);
			}
			rs.close();
			stmt.close();
			return eventos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Evento pesquisar(long id) {
		Evento evento = new Evento();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Evento WHERE id_evento = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			evento.setId_evento(rs.getLong("id_evento"));
			evento.setCategoria(rs.getString("categoria"));
			evento.setNome(rs.getString("nome"));
			OperadorDao operadorDao = new OperadorDao();
			evento.setOperador(operadorDao.pesquisar(rs.getLong("id_operador")));
			rs.close();
			stmt.close();
			return evento;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Operador getOperador(long id){
		OperadorDao daoOpe = new OperadorDao();
		return  daoOpe.pesquisar(id);
	}

	
	public Evento pesquisar(String nome) {
		Evento evento = new Evento();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Evento WHERE nome = ?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
			{
				return null;
			}
			else{
				//rs.next();
				evento.setId_evento(rs.getLong("id_evento"));
				evento.setCategoria(rs.getString("categoria"));
				evento.setNome(rs.getString("nome"));
				OperadorDao operadorDao = new OperadorDao();
				evento.setOperador(operadorDao.pesquisar(rs.getLong("id_operador")));
				rs.close();
				stmt.close();
				return evento;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Evento evento) {
		PreparedStatement stmt;
		try {
			stmt = this.connection
					.prepareStatement("UPDATE Evento set categoria=?, nome=?, id_operador=? WHERE id_evento = ?");
			stmt.setString(1, evento.getCategoria());
			stmt.setString(2, evento.getNome());
			stmt.setLong(3, evento.getOperador().getId_operador());
			stmt.setLong(4, evento.getId_evento());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Evento evento) throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Evento WHERE id_evento = ?");
		stmt.setLong(1, evento.getId_evento());
		stmt.execute();
		stmt.close();
	}
}
