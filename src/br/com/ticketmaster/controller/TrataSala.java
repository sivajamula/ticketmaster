package br.com.ticketmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ticketmaster.dao.SalaDao;
import br.com.ticketmaster.modelo.Endereco;
import br.com.ticketmaster.modelo.Sala;

public class TrataSala extends Comando {
	@Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String acao = request.getParameter("acao");
        int numero_fileiras = Integer.parseInt(request.getParameter("numero_fileiras"));
        int numero_lugares = Integer.parseInt(request.getParameter("numero_lugares"));
        int lotacao_max = Integer.parseInt(request.getParameter("lotacao_max"));
        String tipo = request.getParameter("tipo");
        String descricao = request.getParameter("descricao");
        int endereco = Integer.parseInt(request.getParameter("sala_ender"));
        
        SalaDao dao = new SalaDao();
        
        if(id == 0){
        	Sala sala = new Sala ( numero_fileiras,  numero_lugares,  lotacao_max, tipo,  descricao,  dao.getEndereco(endereco) );
            dao.adiciona(sala);
        	
		}else{
			Sala sala = new Sala (id, numero_fileiras,  numero_lugares,  lotacao_max, tipo,  descricao,  dao.getEndereco(endereco) );
			
			if (acao != null && acao.equals("excluir")) {   
				dao.remove(sala);
			}else{
				dao.altera(sala);
				
			}
        	
        }
        
        response.sendRedirect("tela_sala.jsp?resposta=ok");
        
	}
}