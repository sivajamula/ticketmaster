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
import br.com.ticketmaster.modelo.Operador;

public class OperadorDao {
	private Connection connection;

	public OperadorDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Operador operador) {
		String sql = "INSERT INTO Operador(id_operador, nome, data_nasc) VALUES(s_operador.nextval,?,to_Date(?))";
		try {
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setString(1, operador.getNome());
			stmt.setDate(2, new Date(operador.getData_nasc().getTimeInMillis()));
			// executar
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Operador> getLista() {
		List<Operador> operadores = new ArrayList<Operador>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Operador");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Operador operador = new Operador();
				operador.setId_operador(rs.getLong("id_operador"));
				operador.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nasc"));
				operador.setData_nasc(data);
				operadores.add(operador);
			}
			rs.close();
			stmt.close();
			return operadores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Operador pesquisar(long id) {
		Operador operador = new Operador();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Operador WHERE id_operador = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
			{
			    System.out.println("No Data Found");
			    return null;
			}
			else{
				//rs.next();
				operador.setId_operador(rs.getLong("id_operador"));
				operador.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nasc"));
				operador.setData_nasc(data);
				rs.close();
				stmt.close();
				return operador;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Operador pesquisar(String nome) {
		Operador operador = new Operador();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("SELECT * FROM Operador WHERE nome = ?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
			{
			    System.out.println("No Data Found");
			    return null;
			}
			else {
				operador.setId_operador(rs.getLong("id_operador"));
				operador.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_nasc"));
				operador.setData_nasc(data);
				rs.close();
				stmt.close();
				return operador;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Operador operador) {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("UPDATE Operador set nome=?, data_nasc=? WHERE id_operador = ?");
			stmt.setString(1, operador.getNome());
			stmt.setDate(2, new Date(operador.getData_nasc().getTimeInMillis()));
			stmt.setLong(3, operador.getId_operador());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Operador operador) throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Operador WHERE id_operador = ?");
		stmt.setLong(1, operador.getId_operador());
		stmt.execute();
		stmt.close();
	}
}
