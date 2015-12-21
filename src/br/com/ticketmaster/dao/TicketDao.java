package br.com.ticketmaster.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ticketmaster.jdbc.ConnectionFactory;
import br.com.ticketmaster.modelo.Sessao;
import br.com.ticketmaster.modelo.Ticket;

public class TicketDao {
	private Connection connection;

	public TicketDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Ticket ticket) {
		String sql = "INSERT INTO Ticket(id_ticket, preco, preco_final, id_sessao, TIPO) VALUES(s_ticket.nextval,?,?,?,?)";
		try {
			
			// prepare statement prepara a string
			PreparedStatement stmt = connection.prepareStatement(sql);
			// seta valores
			stmt.setFloat(1, ticket.getPreco());
			stmt.setFloat(2, ticket.getPreco_final());
			stmt.setLong(3, ticket.getSessao().getId_sessao());
			stmt.setString(4, ticket.getTipo().toString());
			// executar
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Ticket> getLista() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Ticket");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Ticket ticket = new Ticket();
				ticket.setId_ticket(rs.getLong("id_ticket"));
				ticket.setPreco(rs.getFloat("preco"));
				ticket.setPreco_final(rs.getFloat("preco_final"));
				SessaoDao sessaoDao = new SessaoDao();
				ticket.setSessao(sessaoDao.pesquisar(rs.getLong("id_sessao")));
				ticket.setTipo(rs.getString("TIPO"));
				tickets.add(ticket);
				
			}
			rs.close();
			stmt.close();
			return tickets;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Ticket pesquisar(long id) {
		Ticket ticket = new Ticket();
		PreparedStatement stmt;
		try {
			
			stmt = this.connection.prepareStatement("SELECT * FROM Ticket WHERE id_ticket=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			ticket.setId_ticket(rs.getLong("id_ticket"));
			ticket.setPreco(rs.getFloat("preco"));
			ticket.setPreco_final(rs.getFloat("preco_final"));
			SessaoDao sessaoDao = new SessaoDao();
			ticket.setSessao(sessaoDao.pesquisar(rs.getLong("id_sessao")));
			ticket.setTipo(rs.getString("TIPO"));
			rs.close();
			stmt.close();
			return ticket;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Sessao getSessao(long id){
		SessaoDao daoSes = new SessaoDao();
		return daoSes.pesquisar(id);
	}
	
	//Testar SE FUNCIONA =D
	/*public TipoEnum getTipo(String tipo){
		System.out.println(TipoEnum.valueOf(tipo) + "TipoEnum Tipo");
		return TipoEnum.valueOf(tipo);
	}*/

	public void altera(Ticket ticket) {
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement("UPDATE Ticket set preco=?, preco_final=?, id_sessao=?, TIPO=? WHERE id_ticket = ?");
	
			stmt.setFloat(1, ticket.getPreco());
			stmt.setFloat(2, ticket.getPreco_final());
			stmt.setLong(3, ticket.getSessao().getId_sessao());
			stmt.setLong(4, ticket.getId_ticket());
			stmt.setString(5, ticket.getTipo().toString());
			
			stmt.executeQuery();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao dar update");
			throw new RuntimeException(e);
		}
	}

	public void remove(Ticket ticket) throws SQLException {
		
		PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Ticket WHERE id_ticket = ?");
		stmt.setLong(1, ticket.getId_ticket());
		stmt.execute();
		stmt.close();
		
	}
}
