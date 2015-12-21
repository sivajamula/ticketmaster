package br.com.ticketmaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.modelo.Endereco;

public class EnderecoDao {
	private Connection connection;

	public EnderecoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Endereco endereco) {
		String sql = "INSERT INTO Endereco(id_endereco, bairro, complemento, uf, cidade, numero, rua) VALUES(s_endereco.nextval,?,?,?,?,?,?)";
		try {
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setString(1, endereco.getBairro());
			stmt.setString(2, endereco.getComplemento());
			stmt.setString(3, endereco.getUF());
			stmt.setString(4, endereco.getCidade());
			stmt.setInt(5, endereco.getNumero());
			stmt.setString(6, endereco.getRua());
			// executar
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Endereco> getLista() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Endereco");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId_endereco(rs.getLong("id_endereco"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setUF(rs.getString("uf"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setRua(rs.getString("rua"));
				enderecos.add(endereco);
			}
			rs.close();
			stmt.close();
			return enderecos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Endereco pesquisar(long id) {
		Endereco endereco = new Endereco();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Endereco WHERE id_endereco = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			endereco.setId_endereco(rs.getLong("id_endereco"));
			endereco.setBairro(rs.getString("bairro"));
			endereco.setComplemento(rs.getString("complemento"));
			endereco.setUF(rs.getString("uf"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setNumero(rs.getInt("numero"));
			endereco.setRua(rs.getString("rua"));
			rs.close();
			stmt.close();
			return endereco;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Endereco endereco) {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(
					"UPDATE Endereco set bairro=?, complemento=?, uf=?, cidade=?, numero=?, rua=? WHERE id_endereco = ?");
			stmt.setString(1, endereco.getBairro());
			stmt.setString(2, endereco.getComplemento());
			stmt.setString(3, endereco.getUF());
			stmt.setString(4, endereco.getCidade());
			stmt.setInt(5, endereco.getNumero());
			stmt.setString(6, endereco.getRua());
			stmt.setLong(7, endereco.getId_endereco());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Endereco endereco) throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Endereco WHERE id_endereco = ?");
		stmt.setLong(1, endereco.getId_endereco());
		stmt.execute();
		stmt.close();
	}
}
