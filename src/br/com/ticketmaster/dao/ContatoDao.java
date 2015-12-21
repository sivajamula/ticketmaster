package br.com.ticketmaster.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.modelo.Contato;

public class ContatoDao {
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "INSERT INTO Contatos(nome, endereco, email, dataNascimento) VALUES(?,?,?,?)";
		try {
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEndereco());
			stmt.setString(3, contato.getEmail());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			// executar
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {
		List<Contato> contatos = new ArrayList<Contato>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Contatos;");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Contato pesquisar(long id) {
		Contato contato = new Contato();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Contatos WHERE id = ?;");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("dataNascimento"));
			contato.setDataNascimento(data);
			rs.close();
			stmt.close();
			return contato;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Contato contato) {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(
					"UPDATE Contatos set nome=?, email =?, endereco=?, dataNascimento=? WHERE id = ?;");
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Contatos WHERE id = ?;");
		stmt.setLong(1, contato.getId());
		stmt.execute();
		stmt.close();
	}
}
