package br.com.ticketmaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.modelo.Endereco;
import br.com.ticketmaster.modelo.Sala;

public class SalaDao {
	private Connection connection;

	public SalaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Sala sala) {
		String sql = "INSERT INTO Sala(id_sala, numero_fileiras, numero_lugares, lotacao_max, tipo, descricao, id_endereco) VALUES(s_sala.nextval,?,?,?,?,?,?)";
		try {
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setInt(1, sala.getNumero_fileiras());
			stmt.setInt(2, sala.getNumero_lugares());
			stmt.setInt(3, sala.getLotacao_max());
			stmt.setString(4, sala.getTipo());
			stmt.setString(5, sala.getDescricao());
			stmt.setLong(6, sala.getEndereco().getId_endereco());
			// executar
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Sala> getLista() {
		List<Sala> salas = new ArrayList<Sala>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Sala");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Sala sala = new Sala();
				sala.setId_sala(rs.getLong("id_sala"));
				sala.setNumero_fileiras(rs.getInt("numero_fileiras"));
				sala.setNumero_lugares(rs.getInt("numero_lugares"));
				sala.setLotacao_max(rs.getInt("lotacao_max"));
				sala.setTipo(rs.getString("tipo"));
				sala.setDescricao(rs.getString("descricao"));
				// crio uma instancia da operador dao e pesquiso o operador pelo
				// id, retornando um objeto
				EnderecoDao enderecoDao = new EnderecoDao();
				sala.setEndereco(enderecoDao.pesquisar(rs.getLong("id_endereco")));
				salas.add(sala);
			}
			rs.close();
			stmt.close();
			return salas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Sala pesquisar(long id) {
		Sala sala = new Sala();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Sala WHERE id_sala = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			sala.setId_sala(rs.getLong("id_sala"));
			sala.setNumero_fileiras(rs.getInt("numero_fileiras"));
			sala.setNumero_lugares(rs.getInt("numero_lugares"));
			sala.setLotacao_max(rs.getInt("lotacao_max"));
			sala.setTipo(rs.getString("tipo"));
			sala.setDescricao(rs.getString("descricao"));
			// crio uma instancia da operador dao e pesquiso o operador pelo
			// id, retornando um objeto
			EnderecoDao enderecoDao = new EnderecoDao();
			sala.setEndereco(enderecoDao.pesquisar(rs.getLong("id_endereco")));
			rs.close();
			stmt.close();
			return sala;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Endereco getEndereco(long id){
		EnderecoDao daoEnd = new EnderecoDao();
		return  daoEnd.pesquisar(id);
	}

	public void altera(Sala sala) {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("UPDATE Sala set numero_fileiras=?, numero_lugares=?, lotacao_max=?, "
					+ "tipo=?, descricao=?, id_endereco=? WHERE id_sala = ?");
			stmt.setInt(1, sala.getNumero_fileiras());
			stmt.setInt(2, sala.getNumero_lugares());
			stmt.setInt(3, sala.getLotacao_max());
			stmt.setString(4, sala.getTipo());
			stmt.setString(5, sala.getDescricao());
			stmt.setLong(6, sala.getEndereco().getId_endereco());
			stmt.setLong(7, sala.getId_sala());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Sala sala) throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Sala WHERE id_sala = ?");
		stmt.setLong(1, sala.getId_sala());
		stmt.execute();
		stmt.close();
	}
}
