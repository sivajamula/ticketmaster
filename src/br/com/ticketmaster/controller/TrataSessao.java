package br.com.ticketmaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ticketmaster.dao.SessaoDao;
import br.com.ticketmaster.modelo.Evento;
import br.com.ticketmaster.modelo.Sala;
import br.com.ticketmaster.modelo.Sessao;


public class TrataSessao extends Comando {
	@Override
    public void executar(HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, SQLException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Tem que arrumar pra pegar a data e tratar ela
        //Date data_sessao = request.getParameter("data");
        //Date duracao = request.getParameter("duracao");
        //Date hora_inicio = request.getParameter("hora_inicio");
        
        int id = Integer.parseInt(request.getParameter("id"));
        String acao = request.getParameter("acao");
        
        Date date = new Date();
        Date data_sessao = date;
        Date duracao = date;
        Date hora_inicio = date;
        int ingressos_reservados = Integer.parseInt(request.getParameter("ingressos_reservados"));
        int cod_evento =  Integer.parseInt(request.getParameter("evento"));
        int cod_sala = Integer.parseInt(request.getParameter("sala"));
        Evento event = new Evento().pesquisarEvento(cod_evento);
        Sala sala = new Sala().pesquisarSala(cod_sala);
        
        SessaoDao dao = new SessaoDao();
        
        if(id == 0){
        	Sessao sessao = new Sessao ( data_sessao,  duracao,  hora_inicio, ingressos_reservados,  event,  sala );
            dao.adiciona(sessao);
        	
		}else{
			Sessao sessao = new Sessao (id, data_sessao,  duracao,  hora_inicio, ingressos_reservados,  event,  sala );
			
			if (acao != null && acao.equals("excluir")) {   
				dao.remove(sessao);
			}else{
				dao.altera(sessao);
				
			}
        	
        }
        
        response.sendRedirect("tela_sessao.jsp?resposta=ok");
	}
}